import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";
import Home from './Pages/Home';
import Weclcome from './Pages/Welcome';

function App() {
    return (
        <>
        <Home/>
        <Router> {/* Use BrowserRouter here */}
                <DefaultPage/>
                <nav> 
                     <Routes>
                        <Route path="/" element={<Weclcome/>} /> {/* Home page */}
                     </Routes>
                </nav>
        </Router>
        </>
    );
}

function DefaultPage() {
  const navigate = useNavigate();
  return <button onClick={() => navigate("/")}>Welcome</button>;
}

export default App;
