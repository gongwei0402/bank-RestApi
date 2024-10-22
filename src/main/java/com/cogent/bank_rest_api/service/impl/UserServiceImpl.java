package com.cogent.bank_rest_api.service.impl;

import com.cogent.bank_rest_api.Entity.User;
import com.cogent.bank_rest_api.payload.LoginDTO;
import com.cogent.bank_rest_api.payload.RegisterDTO;
import com.cogent.bank_rest_api.repository.UserRepository;
import com.cogent.bank_rest_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(User newUser) {
        return userRepository.save(newUser);
    }

//    @Override
//    public String login(LoginDTO loginDTO) {
//        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginDTO.getUsername(), loginDTO.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "user login successfully!";
//    }
//
//    @Override
//    public String register(RegisterDTO registerDTO) {
//        if(userRepository.existsByUsername(registerDTO.getUsername())){
//            return "User name is already taken!";
//        }
//
//        return "";
//    }
}
