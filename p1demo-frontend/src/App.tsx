
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import 'bootstrap/dist/css/bootstrap.css' //Needs this import for bootstrap to work
import { Login } from './Components/LoginRegister/Login'
import { Register } from './Components/LoginRegister/Register'
import { Teams } from './Components/Teams/Teams'
import { Users } from './Components/Users/Users'

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          {/* Empty string or / for path to render a component at startup */}
          <Route path="" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>
          <Route path="/teams" element={<Teams/>}/>
          <Route path="/users" element={<Users/>}/>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
