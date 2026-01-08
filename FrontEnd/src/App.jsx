import React, {useContext} from 'react';
import Lezioni from './Lezioni.jsx';
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import Studenti from './Studenti.jsx';
import Login from './Login.jsx';
import Home from './Home.jsx';
import RegistrazioneS from './RegistrazioneS.jsx';
import RegistrazioneP from './RegistrazioneP.jsx';
import './App.css';
import AuthContext from "./AuthContext.jsx";

function App() {
  const { isLogged, logout } = useContext(AuthContext);
  const navigate = useNavigate();
  return (
    <>
    <nav>
  <Link to="/lezioni">Lezioni</Link> | <Link to="/studenti">Studenti</Link> | <Link to="/Home">Home</Link>
  {!isLogged ? (
    <>
      {' '}| <Link to="/">Login</Link> | <Link to="/registrazioneS">Registrazione Studente</Link> | <Link to="/registrazioneP">Registrazione Professore</Link>
    </>
  ) : (
    <>
      {' '}| <button onClick={() => { logout(); navigate("/", { replace: true }); }}>Logout</button>
    </>
  )}
</nav>
      <Routes>
        <Route path="/lezioni" element={
        <div>
          <Lezioni/>
        </div>
        }/>
        <Route path="/studenti" element={
          <div>
            <h1>Mostrare Studenti</h1>
            <Studenti/>
          </div>
        }/>
        <Route path="/" element={
          <div>
            <h1>Login</h1>
            <Login/>
          </div>
        }/>
         <Route path="/Home" element={
          <div>
            <Home/>
          </div>
        }/>
          <Route path="/RegistrazioneS" element={
            <div>
              <RegistrazioneS/>
            </div>
          }/>
          <Route path="/RegistrazioneP" element={
            <div>
              <RegistrazioneP/>
            </div>
          }/>


        </Routes>

      </>
  );
}
  export default App;