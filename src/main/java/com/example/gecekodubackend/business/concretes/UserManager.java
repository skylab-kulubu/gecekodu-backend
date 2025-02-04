package com.example.gecekodubackend.business.concretes;

import com.example.gecekodubackend.business.abstracts.EventService;
import com.example.gecekodubackend.business.abstracts.UserService;
import com.example.gecekodubackend.business.constants.UserMessages;
import com.example.gecekodubackend.core.dtos.CreateUserDto;
import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.core.utilities.results.*;
import com.example.gecekodubackend.core.dataAccess.UserDao;
import com.example.gecekodubackend.core.entities.*;
import com.example.gecekodubackend.entity.concretes.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;
    private final EventService eventService;

    private final BCryptPasswordEncoder passwordEncoder;
    private final EventManager eventManager;

    @Autowired
    public UserManager(UserDao userDao, BCryptPasswordEncoder passwordEncoder, EventService eventService, EventManager eventManager) {
        super();
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.eventService = eventService;
        this.eventManager = eventManager;
    }

    @Override
    public DataResult<List<GetUserDto>> getAllUsers() {
        List<User> users = userDao.findAll();

        if(users.isEmpty()){
            return new ErrorDataResult<>(UserMessages.usersNotFound);
        }

        List<GetUserDto> userDtoList = new ArrayList<>();
        for(User user : users){
            GetUserDto userDto = new GetUserDto();
            BeanUtils.copyProperties(user, userDto);
            userDtoList.add(userDto);
        }

        return new SuccessDataResult<>(userDtoList, UserMessages.usersBroughtSuccessfully);
    }

    @Override
    public DataResult<GetUserDto> getUserById(int id) {
        var result = userDao.findById(id);

        if(result.isEmpty()){
            return new ErrorDataResult<>(UserMessages.userNotFound);
        }

        GetUserDto userDto = new GetUserDto();
        BeanUtils.copyProperties(result.get(), userDto);
        return new SuccessDataResult<>(userDto, UserMessages.userBroughtSuccessfully);
    }

    @Override
    public Result deleteUser(int id) {
        var result = checkIfUserExists(id);

        if(!result.isSuccess()){
            return new ErrorResult(UserMessages.userNotFound);
        }

        userDao.deleteById(id);
        return new SuccessResult(UserMessages.userDeletedSuccessfully);
    }

    @Override
    public DataResult<GetUserDto> getUserByEmail(String email) {
        var result = userDao.getUserByEmail(email);

        if(result == null){
            return new ErrorDataResult<>(UserMessages.userNotFound);
        }

        GetUserDto userDto = new GetUserDto();
        BeanUtils.copyProperties(result, userDto);
        return new SuccessDataResult<>(userDto, UserMessages.userBroughtSuccessfully);
    }

    @Override
    public DataResult<User> getUserEntityByEmail(String email) {
        var result = userDao.getUserByEmail(email);

        if(result == null){
            return new ErrorDataResult<>(UserMessages.userNotFound);
        }

        return new SuccessDataResult<>(result, UserMessages.userBroughtSuccessfully);
    }

    @Override
    public DataResult<User> getUserEntityById(int id) {
        var result = userDao.findById(id);

        if(result.isEmpty()){
            return new ErrorDataResult<>(UserMessages.userNotFound);
        }

        return new SuccessDataResult<>(result.get(), UserMessages.userBroughtSuccessfully);
    }

    @Override
    public Result addUserToEvent(int userId, int eventId) {
        var userResult = getUserEntityById(userId);
        // against to solid? needs some refactor
        var eventResult = eventService.getEventEntityById(eventId);

        var userExists = checkIfUserExists(userId);

        var eventExists = eventManager.checkIfEventExists(eventId);

        if(userResult == null || eventResult == null || !userExists.isSuccess() || !eventExists.isSuccess()){
            return new ErrorResult(UserMessages.userCouldNotAddedToEvent);
        }

        userResult.getData().getEvents().add(eventResult.getData());
        userDao.save(userResult.getData());

        return new SuccessResult(UserMessages.userAddedToEventSuccessfully);
    }

    @Override
    public Result addModerator(int id) {

        /*
        var result = userDao.findById(id);

        if(result.isEmpty()){
            return new ErrorResult(UserMessages.userNotFound);
        }

        result.get().setAuthorities(Role.ROLE_MODERATOR);
        userDao.save(result.get());

        */
        return new SuccessResult(UserMessages.moderatorAddedSuccessfully);
    }

    @Override
    public Result removeModerator(int id) {
        return null;
    }

    @Override
    public Result updateUser(CreateUserDto userDto, int id) {
        var result = userDao.findById(id);

        if(result.isEmpty()){
            return new ErrorResult(UserMessages.userNotFound);
        }

        if(userDao.existsByEmail(userDto.getEmail())){
            return new ErrorResult(UserMessages.emailAlreadyExists);
        }

        if(userDto.getPassword().isEmpty() || userDto.getEmail().isEmpty() || userDto.getFirstName().isEmpty() || userDto.getLastName().isEmpty()){
            return new ErrorResult(UserMessages.userCouldNotBeUpdated);
        }

        var userToUpdate = userDao.findById(id).get();
        userToUpdate.setFirstName(userDto.getFirstName());
        userToUpdate.setLastName(userDto.getLastName());
        userToUpdate.setEmail(userDto.getEmail());

        userDao.save(userToUpdate);
        return new SuccessResult(UserMessages.userUpdatedSuccessfully);
    }

    @Override
    public Result addUser(CreateUserDto createUserDto) {

        if(userDao.existsByEmail(createUserDto.getEmail())){
            return new ErrorResult(UserMessages.emailAlreadyExists);
        }

        if(createUserDto.getFirstName().isEmpty() || createUserDto.getLastName().isEmpty() || createUserDto.getEmail().isEmpty() || createUserDto.getPassword().isEmpty()){
            return new ErrorResult(UserMessages.userCouldNotBeAdded);
        }

        User user = User.builder()
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .authorities(Set.of(Role.ROLE_USER))
                .build();

        userDao.save(user);
        return new SuccessResult(UserMessages.userAddedSuccessfully);
    }

    public Result checkIfUserExists(int id){
        var result = getUserById(id);

        if(result.isSuccess()){
            return new SuccessResult(UserMessages.userAlreadyExists);
        }

        return new ErrorResult(UserMessages.userNotFound);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserEntityByEmail(username).getData();
    }
}
