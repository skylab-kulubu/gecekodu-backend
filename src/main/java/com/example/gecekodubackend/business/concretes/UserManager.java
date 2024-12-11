package com.example.gecekodubackend.business.concretes;

import com.example.gecekodubackend.business.abstracts.UserService;
import com.example.gecekodubackend.business.constants.UserMessages;
import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.core.utilities.results.*;
import com.example.gecekodubackend.core.dataAccess.UserDao;
import com.example.gecekodubackend.core.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao){
        super();
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<GetUserDto>> getAllUsers() {
        List<GetUserDto> userDtoList = new ArrayList<>();
        List<User> users = userDao.findAll();

        if (users.isEmpty()){
            return new ErrorDataResult<>(UserMessages.usersNotFound);
        }

        // against to solid? needs some refactor
        for(User user : userDtoList){
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

        return new SuccessDataResult<>(result.get(),UserMessages.userBroughtSuccessfully);
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
    public DataResult<User> getUserByEmail(String email) {
        return null;
    }

    @Override
    public Result updateUser(GetUserDto userDto, int id) {
        var result = checkIfUserExists(id);

        if(result == null){
            return new ErrorResult(UserMessages.userNotFound);
        }

        DataResult<GetUserDto> userToUpdate = getUserById(id);
        userToUpdate.getData().setFirstName(userDto.getFirstName());
        userToUpdate.getData().setLastName(userDto.getLastName());
        userToUpdate.getData().setEmail(userDto.getEmail());
        userDao.save(userToUpdate);
        return new SuccessResult(UserMessages.userUpdatedSuccessfully);
    }

    @Override
    public Result addUser(User user) {
        return null;
    }

    public Result checkIfUserExists(int id){
        var result = getUserById(id);

        if(result.isSuccess()){
            return new SuccessResult(UserMessages.userAlreadyExists);
        }

        return new ErrorResult(UserMessages.userNotFound);
    }
}
