package com.revature.controllers;

import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<OutgoingUserDTO> login(@RequestBody LoginDTO loginDTO, HttpSession session){
        //NOTE: we have an HTTP session coming in via parameters
        //This is a different session than the static one we declared above
        //We're going to use it to help initialize our static session

        //Send the loginDTO to the service
        OutgoingUserDTO user = authService.login(loginDTO);
        System.out.println("User " + user.getUsername() + " logged in!");
        //If we get here, login was successful and session was created
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        System.out.println("User " + user.getUsername() + " logged in!");
        /* Why store all this info in a session?
        * It lets is store User info that we can use to:
            -Check if they're logged in
            -Check their role is appropriate for the functionality they want to access
            -Personalize the app
            -Simplify our URLs!
                ex. use the stored userID in "findXByUserId" methods instead of passing it in the path variable*/

        return ResponseEntity.ok(user);
    }
}
