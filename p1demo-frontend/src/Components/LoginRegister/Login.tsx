import { Button, Container, Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"

export const Login:React.FC = () => {

    //TODO: state stuff

    //We can use the useNavigate hook to navigate between components programatically
        //which means we don't have to manually change the URL to switch components
    const navigate = useNavigate()

    return(
        <Container className="d-flex flex-column align-items-center mt-5">
            <h3>Login</h3>
            <div>
                <Form.Control type="text" placeholder="username" name="username"/>
            </div>
            <div>
                <Form.Control type="password" placeholder="password" name="password"/>
            </div>
            <div className="d-flex gap-2">
                <Button onClick={() => navigate("/teams")}>Login</Button>
                <Button onClick={() => navigate("/register")}>Register</Button>
            </div>
        </Container>
    )
}