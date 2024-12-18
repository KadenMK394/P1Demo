package com.revature.services;

import com.revature.DAOs.TeamDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.Team;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //make this class a bean
public class UserService {

    //constructor inject the UserDAO and the TeamDAO
    private final UserDAO userDAO;
    private final TeamDAO teamDAO;

    @Autowired
    public UserService(UserDAO userDAO, TeamDAO teamDAO) {
        this.userDAO = userDAO;
        this.teamDAO = teamDAO;
    }

    //insert a new User into the DB
    public User insertUser(IncomingUserDTO userDTO){

        //TODO: make sure the userDTO fields are present and valid
        //TODO: make sure the incoming username is unique

        //We need to turn the userDTO into a User (DAO takes a User object)
        //userId will get autogenerated, so 0 will get overwritten
        //username, password, and role come from the DTO
        //team will be set using the teamId from the DTO (find it by ID)
        User user = new User(0,
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getRole(),
                null);

        //using the TeamDAO to get a team by ID
        //findById() returns an optional,
            //which we can use to avoid nulls and help with error handling
        Optional<Team> team = teamDAO.findById(userDTO.getTeamId());

        //if the team is not present, throw an exception with message "team not found"
        if(team.isEmpty()){
            throw new IllegalArgumentException(
                    "No Team found with ID " + userDTO.getTeamId());
        } else {
            //if the team is present, set the user's team to the found team
            //.get() is the method we use to extract values from Optionals
            user.setTeam(team.get());

            //finally, we can send the User to the DAO
            return userDAO.save(user);
        }
    }

    //update user password
    public User updateUserPassword(int userId, String newPassword){

        //TODO: error handling to make sure new password is present/valid
        //TODO: make sure the new password isn't the old password

        //get the user by ID

        //orElseThrow is a quicker way to handle Optionals
        //If the optional is empty, throw an exception
        User user = userDAO.findById(userId).orElseThrow(() -> {
            return new IllegalArgumentException("No User found with ID " + userId);
        });
        //If we reach this point, we know the user exists. Update the password!
        user.setPassword(newPassword);
        //updates in Spring Data are just save() like with inserts
        return userDAO.save(user);

        //Why doesn't this create a duplicate user? How does it know it's an update?
    }

    //get all Users
    public List<OutgoingUserDTO> getAllUsers(){
        //Problem: findAll() returns List<User>. We need List<OutgoingUserDTO>
        //Solution (one of many): Loop through the User List and make a new List of DTOs

        //Empty List<OutgoingUserDTO> to be filled below
        List<OutgoingUserDTO> outgoingUsers = new ArrayList<OutgoingUserDTO>();
        //Get all Users from DB
        List<User> users = userDAO.findAll();
        //Loop through the Users, adding a new DTO for each record
        for(User user: users){
            //add the new DTO to the ArrayList using the all-args constructor
            outgoingUsers.add(new OutgoingUserDTO(user.getUserId(), user.getUsername(), user.getRole(), user.getTeam()));
        }
        return outgoingUsers;
    }
}
