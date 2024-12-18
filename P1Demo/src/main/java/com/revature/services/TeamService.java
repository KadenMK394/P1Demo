package com.revature.services;

//The Service layer is where we have our business logic
//User input validation, Data manipulation/reformatting, User authentication, etc.

import com.revature.DAOs.TeamDAO;
import com.revature.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //1 of the 4 stereotype annotations (makes a class a bean)
public class TeamService {

    //autowire the DAO
    private final TeamDAO teamDAO;

    @Autowired
    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    //This method inserts new Teams into the DB once they have been validated
    public Team insertTeam(Team team) {

        //Not too much to check, but we can make sure the values are valid

        if (team.getTeamName() == null || team.getTeamName().isBlank()){
            throw new IllegalArgumentException("Team name can't be null or blank!");
        }

        if (team.getTeamLocation() == null || team.getTeamLocation().isBlank()){
            throw new IllegalArgumentException("Team location can't be null or blank!");
        }

        //if none of these ifs get triggered, the Team is valid and can be sent to the DAO

        return teamDAO.save(team);

    }

    //This method gets all teams from the DB
    public List<Team> getAllTeams(){

        //not too much error handling to do here
        //TODO: maybe a different message if there are no records?

        return teamDAO.findAll();

    }

    //This method gets all teams from the DB BY LOCATION
    public List<Team> findByTeamLocation(String location){

        //make sure the location is valid
        if(location == null || location.isBlank()){
            throw new IllegalArgumentException("Location can't be null or blank!");
        }

        //get the list of teams
        List<Team> teams = teamDAO.findByTeamLocation(location);

        //send the List back to the controller
        return teams;

    }

}
