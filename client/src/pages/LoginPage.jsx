import axios from "axios";
import { useContext, useState } from "react"
import { Link, Navigate, redirect } from "react-router-dom"
import { UserContext } from "../UserContext";

export default function LoginPage(){
    const[email,setEmail] = useState('');
    const[password,setPassword] = useState('');
    const[redirect,setRedirect] = useState(false);
    const{setUser} =  useContext(UserContext);

    

    async function handleLoginSubmit(ev){
        ev.preventDefault();
        try{
            //////??????????? data
        const {data} = await axios.post('/login',{email,password});
            alert('Login succesful');
            setUser(data);
            setRedirect(true);
        }
        catch(e){
            alert('Login failed')
        }

    }

    if (redirect){
        return <Navigate to={'/'}/>
    }
    
    return (
       <div className="flex items-center justify-around grow"> 
        <div className="mb-64">
            <h1 className="text-center">Login</h1>
<form className="max-w-md mx-auto" onSubmit={handleLoginSubmit}>
    <input 
    type="email" 
    value={email}
    onChange={event=>setEmail(event.target.value)}
    
    placeholder="your@mail.com"/>
    <input type="text" 
        value={password}
        onChange={event=>setPassword(event.target.value)}
    placeholder="password"/>
    <button className="primary">Login</button>
</form>
     <div className="p-2 text-center">
        Dont have an account ?  
        <Link className="underline" to={"/register"}>Register here </Link>
     </div>

        </div>
        </div>
    )
}