package com.example.TP.service;

import com.example.TP.dto.UserDto;
import com.example.TP.model.User;

public interface UserService {

    User save (UserDto userDto);
}
