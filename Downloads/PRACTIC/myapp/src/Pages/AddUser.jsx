import { useState, useEffect } from 'react';
import '../CSS/inputfields.css';

const AddUser = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const [length, setLength] = useState(0);
    const [small, setSmall] = useState(0);
    const [capital, setCapital] = useState(0);
    const [specialChar, setSpecialChar] = useState(0);

    useEffect(() => {
        const button = document.getElementsByClassName('addUserbutton')[0];
        if (username === "" || password === "") {
            button.disabled = true; // Correctly disable the button
        } else {
            button.disabled = false; // Enable the button
        }
    }, [username, password]);


    useEffect(()=>{
        setPassword(password.replace(/[^A-Za-z0-9~!@#$%^&*()_+=]/, ''));
        if(password.length>8){
            setLength(1);
        }else{setLength(0);}

        if(/[a-z]/.test(password)){
            setSmall(1);
        }else{
            setSmall(0);
        }

        if(/[A-Z]/.test(password)){
            setCapital(1);
        }else{
            setCapital(0);
        }

        if(/[^A-Za-z0-9]/.test(password)){
            setSpecialChar(1);
        }else{
            setSpecialChar(0);
        }
    },[password]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        const user = {
            username: username,
            password: password
        };
        try {
            const response = await fetch('http://localhost:8080/addUser', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });

            const data = await response.json();
            setMessage('User added successfully!');
            setPassword('');
            setUsername('');
            console.log('Success:', data);
        } 
        catch (error) {
            setMessage('Error adding user.');
            console.error('Error:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit} className='add-user-container'>
            <div className='add-user-items'>
                <input
                    type="text"
                    name="username"
                    value={username}
                    placeholder='Username*'
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    name="password"
                    value={password}
                    placeholder='Password*'
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <button type="submit" className='addUserbutton'>Add User</button>
            <div className='message-container'>
            <p className="message" style={{display: length === 0 ? 'block' : 'none', color:'red'  }}>Password Length should be between 8 - 16</p>
            <p className="message" style={{display: small === 0 ? 'block' : 'none' , color:'red'}}>Password should have one small letter</p>
            <p className="message" style={{display: capital === 0 ? 'block' : 'none' , color:'red'}}>Password should have one capital letter</p>
            <p className="message" style={{display: specialChar === 0 ? 'block' : 'none' , color:'red' }}>Password should have one special character</p>
            <p  className="message"  style={{display: message === '' ? 'none' : 'block'  }}>  {message}</p>
            </div>
        </form>
    );
};
export default AddUser;