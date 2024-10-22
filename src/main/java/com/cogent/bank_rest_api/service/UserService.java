package com.cogent.bank_rest_api.service;

import com.cogent.bank_rest_api.Entity.User;
import com.cogent.bank_rest_api.payload.LoginDTO;
import com.cogent.bank_rest_api.payload.RegisterDTO;

public interface UserService {
    User registerUser(User newUser);
//    String login(LoginDTO loginDTO);
//    String register(RegisterDTO registerDTO);
}
