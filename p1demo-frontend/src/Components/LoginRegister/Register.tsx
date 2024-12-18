import axios from "axios"
import { useState } from "react"
import { Button, Container, Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"

interface User{
    username:string,
    password:string,
    teamId:number
}

export const Register:React.FC = () => {
    //Define a state object to store the new User info
    const[newUser, setNewUser] = useState<User>({
        username:"",
        password:"",
        teamId: 0
    })

    //useNavigate hook to switch URLs
    const navigate = useNavigate()

    //function that stores values when input boxes change
    //SyntheticEvent is just the type of event coming in, we didn't need to specify data type - we're just being typesafe
    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) => {
        //I'm going to store name and value in variables, for ease of use below
        const name = event.target.name
        const value = event.target.value

        //annoying syntax - we need to send the entire user object to make a change to one field
        //"Take whatever input was changed, and set the matching state object field to the value of that input"
        //[name] can be EITHER username or password here. Flexible! Whatever input changes will change the appropriate newUser state field
        setNewUser((newUser) => ({...newUser, [name]:value}))
    }

    //Register function that sends the username/password to the backend in a POST request
    const register = async () => {
        //TODO: check that the username/password are present and valid
        //POST request
        const response = await axios.post("http://localhost:4444/users", newUser)
        .then(() => {
            alert("User " + newUser.username + " created!")
            navigate("/")
        })
        .catch((error) => alert("Registration failed! Make sure all fields are correct."))
    }
    
    return(
        <Container className="d-flex flex-column align-items-center mt-5">
            <h3>Register</h3>
            
            {/* Making a separate div for each input box */}
            <div>
                <Form.Control type="text" placeholder="username" name="username" onChange={storeValues}/>
            </div>
            <div>
                <Form.Control type="password" placeholder="password" name="password" onChange={storeValues}/>
            </div>
            <div>
                <Form.Control type="number" placeholder="teamId" name="teamId" onChange={storeValues}/>
            </div>
            <div className="d-flex gap-2">
                <Button onClick={() => navigate("/")}>Back</Button>
                <Button onClick={register}>Register</Button>
            </div>
        </Container>
    )
}