package com.example.gecekodubackend.business.concretes;

import com.example.gecekodubackend.business.abstracts.EventService;
import com.example.gecekodubackend.business.abstracts.UserService;
import com.example.gecekodubackend.business.constants.UserMessages;
import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.core.utilities.results.*;
import com.example.gecekodubackend.core.dataAccess.UserDao;
import com.example.gecekodubackend.core.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager implements UserService {


    private final UserDao userDao;
    private EventService eventService;


    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(UserDao userDao, BCryptPasswordEncoder passwordEncoder, EventService eventService) {
        super();
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.eventService = eventService;
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

        if(result.isSuccess()){
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
        var eventResult = eventService.getEventById(eventId);

        userResult.getData().getEvents().add(eventResult.getData());
        userDao.save(userResult.getData());

        return new SuccessResult(UserMessages.userAddedToEventSuccessfully);
    }

    @Override
    public Result updateUser(GetUserDto userDto, int id) {
        var result = checkIfUserExists(id);

        if(result == null){
            return new ErrorResult(UserMessages.userNotFound);
        }

        var userToUpdate = userDao.findById(id).get();
        userToUpdate.setFirstName(userDto.getFirstName());
        userToUpdate.setLastName(userDto.getLastName());
        userToUpdate.setEmail(userDto.getEmail());

        userDao.save(userToUpdate);
        return new SuccessResult(UserMessages.userUpdatedSuccessfully);
    }

    @Override
    public Result addUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(userDao.existsByEmail(user.getEmail())){
            return new ErrorResult(UserMessages.emailAlreadyExists);
        }


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
        var user = getUserEntityByEmail(username).getData();
        return user;
    }
}
