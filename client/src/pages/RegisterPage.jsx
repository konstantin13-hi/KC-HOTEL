import { useState } from "react"
import { Link } from "react-router-dom"
import axios from "axios";
export default function RegisterPage(){

    const[name,setName] = useState('');
    const[email,setEmail] = useState('');
    const[password,setPassword] = useState('');


   async function registerUser(event){
        event.preventDefault();
        try{
            await  axios.post('/register',{
                name,
                password,
                email,
            })
            alert('Registration successful.Now you can log in');
      
        }
        catch(e){
            alert('Registration failed');
        }
        }
    






return (
   


    <div className="flex items-center justify-around grow"> 
        <div className="mb-64">
            <h1 className="text-center">Register</h1>
            <form className="max-w-md mx-auto" onSubmit={registerUser}>
                <input type="text" 
                value={name} 
                onChange={event=>setName(event.target.value)}
                placeholder="Bill Lexos"/>
                <input type="email"
                value={email} 
                onChange={event=>setEmail(event.target.value)}
                placeholder="your@mail.com"/>
                <input type="password"
                value={password}
                onChange={event=>setPassword(event.target.value)}
                 placeholder="password"/>
                 
                <button className="primary">Register</button>
            </form>
            <div className="p-2 text-center">
                Dont have an account ?  
                <Link className="underline" to={"/login"}>Login </Link>
            </div>

        </div>
     </div>
 )
}