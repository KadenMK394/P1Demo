import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"

interface User{
    userId:number,
    username:string,
    role:string,
    team:any //cutting a corner here, didn't wanna make a whole team interface :)
}

export const Users:React.FC = () => {

    const [users, setUsers] = useState<User[]>([])

    useEffect(() => {
        getAllUsers()
    }, [])

    const navigate = useNavigate()

    const getAllUsers = async () => {
        const response = await axios.get("http://localhost:4444/users")
        .then((response) =>{
            setUsers(response.data)
        })
    }

    return(
        <Container>
            <h3> Users:</h3>
            
            <Table className="table-success table-hover table-striped">
                <thead className="table-dark">
                    <tr>
                        <th>User Id</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Team Name</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                {/* map() for users gathered from the GET request */}
                {users.map((user:User) => (
                        <tr>
                            <td>{user.userId}</td>
                            <td>{user.username}</td>
                            <td>{user.role}</td>
                            <td>{user.team.teamName}</td>
                            <td className="d-flex gap-2">
                                {/* Conditional Rendering - promote button if the user is a player, and demote button if the user is a manager*/}
                                {user.role === "player" ? <Button className="btn-verify">Promote</Button>: <Button className="btn-dark">Demote</Button>}
                                <Button className="btn-danger">Delete</Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <Button className="btn-info" onClick={() => navigate("/")}>Back</Button>

        </Container>
    )
}