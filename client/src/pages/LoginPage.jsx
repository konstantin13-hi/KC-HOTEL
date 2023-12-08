import axios from "axios";
import { useContext, useState } from "react"
import { Link, Navigate, redirect } from "react-router-dom"
import { UserContext } from "../UserContext";

export default function LoginPage(){
    const[email,setEmail] = useState('');
    const[password,setPassword] = useState('');
    const[redirect,setRedirect] = useState(false);
    const [errors, setErrors] = useState({});
    const{setUser} =  useContext(UserContext);
    const{setReady} =  useContext(UserContext);





    async function handleLoginSubmit(ev){
        ev.preventDefault();
        try{
        const {data} = await axios.post('http://localhost:8080/login',{email,password});
        const token = data.token;
        localStorage.setItem('token', token);

                 setUser(data);
                alert('Login successful');
                setRedirect(true);

        }
        catch(e){
        console.log(e);
                  if (e.response && e.response.status === 400 && e.response.data) {
                    const errorData = e.response.data;
                    setErrors(errorData);
                  }else{
                     alert('Login failed')
                  }

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
    onChange={(event) => {
                      setEmail(event.target.value);
                      setErrors({ ...errors, email: undefined });
                    }}

    placeholder="your@mail.com"/>
     {errors.email && <div className="error-message">{errors.email}</div>}
    <input type="text"
        value={password}
          onChange={(event) => {
                          setPassword(event.target.value);
                          setErrors({ ...errors, password: undefined });
                        }}
    placeholder="password"/>
    {errors.password && <div className="error-message">{errors.password}</div>}
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