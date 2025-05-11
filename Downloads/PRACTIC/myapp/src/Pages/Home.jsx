import AddUser from "./AddUser";
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";


export const Home =()=>{
    return (
        <Router> {/* Use BrowserRouter here */}
                <nav>
                <HomeButton/>
                     <Routes>
                        <Route path="/AddUser" element={<AddUser/>} /> {/* Home page */}
                     </Routes>
                </nav>
        </Router>
    );
}
function HomeButton() {
    const navigate = useNavigate();
    return <button onClick={() => navigate("/AddUser")}>Add User</button>;
}
export default Home;