package com.revature.services;

import com.revature.DAOs.AuthDAO;
import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthDAO authDAO;

    @Autowired
    public AuthService(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    //method that takes in a username/password and returns the matching User if found
    public OutgoingUserDTO login(LoginDTO loginDTO){
        //TODO: Validate the loginDTO fields
        //Use the DAO to find a User in the DB with info from the DTO
        User user = authDAO.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        //If no User is found, throw an exception
        if(user == null){
            //TODO: We could have made a custom "LoginFailedException"
            throw new IllegalArgumentException("No user found with those credentials");
        }

        //Return an OutgoingUserDTO to the Controller
        return new OutgoingUserDTO(user.getUserId(), user.getUsername(), user.getRole(), user.getTeam());
    }
}
