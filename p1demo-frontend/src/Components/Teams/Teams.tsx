import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"

//interface to model Team objects
interface Team{
    teamId:number,
    teamName:string,
    teamLocation:string,
}

export const Teams:React.FC = () => {

    //We'll store a state object that holds an Array of Team objects
    //This will get filled after the GET request
    const [teams, setTeams] = useState<Team[]>([])

    //useEffect to send a GET request for teams on component load
    useEffect(() => {
        getAllTeams()
    }, []) 

    //useNavigate hook so we can change the URL as needed
    const navigate = useNavigate()

    //The function that sends the GET request
    const getAllTeams = async () => {
        //axios GET request
        const response = await axios.get("http://localhost:4444/teams")
        //populate the teams state object
        setTeams(response.data) //data holds the actual data stored in the response body
    }

    //hypothetical DELETE team method (just to show how to extract IDs)
    const deleteTeam = (teamId:number) => {
        alert("Team " + teamId + " has been deleted (but not really)")
    }


    return(
        <Container>
            <Button className="btn-info" onClick={() => navigate("/")}>Back</Button>

            <Table>
                <thead>
                    <tr>
                        <th>Team ID</th>
                        <th>Team Name</th>
                        <th>Location</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    {/* map() for teams gathered from the GET request */}
                    {teams.map((team:Team) => (
                        <tr>
                            <td>{team.teamId}</td>
                            <td>{team.teamName}</td>
                            <td>{team.teamLocation}</td>
                            <td>
                                <Button className="btn-danger" onClick={() => deleteTeam(team.teamId)}>Delete</Button>
                                {/*attach the deleteTeam function to the onClick through an arrow function so it doesn't invoke immediately on render*/}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>

        </Container>
    )
}