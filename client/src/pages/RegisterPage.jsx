import { useState } from "react"
import { Link } from "react-router-dom"
import axios from "axios";
export default function RegisterPage(){

    const[name,setName] = useState('');
    const[email,setEmail] = useState('');
    const[password,setPassword] = useState('');
      const [error, setError] = useState("");
      const [errors, setErrors] = useState({});

      async function registerUser(event) {
        event.preventDefault();
        try {
          await axios.post('http://localhost:8080/register', {
            name,
            password,
            email,
          });
          alert('Registration successful. Now you can log in');
        } catch (e) {
          if (e.response && e.response.status === 400 && e.response.data) {
            const errorData = e.response.data;
            setErrors(errorData);
          } else {
            alert('Registration failed');
          }
        }
      }

      return (
        <div className="flex items-center justify-around grow">
          <div className="mb-64">
            <h1 className="text-center">Register</h1>
            <form className="max-w-md mx-auto" onSubmit={registerUser}>
              <input
                type="text"
                value={name}
                onChange={(event) => {
                  setName(event.target.value);
                  setErrors({ ...errors, name: undefined });
                }}
                placeholder="Bill Lexos"
              />
              {errors.name && <div className="error-message">{errors.name}</div>}
              <input
                type="email"
                value={email}
                onChange={(event) => {
                  setEmail(event.target.value);
                  setErrors({ ...errors, email: undefined });
                }}
                placeholder="your@mail.com"
              />
              {errors.email && <div className="error-message">{errors.email}</div>}
              <input
                type="password"
                value={password}
                onChange={(event) => {
                  setPassword(event.target.value);
                  setErrors({ ...errors, password: undefined });
                }}
                placeholder="password"
              />
              {errors.password && <div className="error-message">{errors.password}</div>}
              <button className="primary">Register</button>
            </form>
            <div className="p-2 text-center">
              Don't have an account ? <Link className="underline" to={'/login'}>
                Login
              </Link>
            </div>
          </div>
        </div>
      );

//
//    async function registerUser(event){
//         event.preventDefault();
//         try{
//             await  axios.post('http://localhost:8080/register',{
//                 name,
//                 password,
//                 email,
//             })
//             alert('Registration successful.Now you can log in');
//
//         }
//
//                 catch (e) {
//                 console.log(e);
//                 if (e.response && e.response.status === 400) {
//                       const errorMessage = e.response.data.password
//                       alert(`Registration failed: ${errorMessage}`);
//                     } else {
//                       alert('Registration failed');
//                     }
//              }
//
//     }
//
//
//
//
//
//
//
// return (
//
//
//
//     <div className="flex items-center justify-around grow">
//         <div className="mb-64">
//             <h1 className="text-center">Register</h1>
//             <form className="max-w-md mx-auto" onSubmit={registerUser}>
//                 <input type="text"
//                 value={name}
//                 onChange={event=>setName(event.target.value)}
//                 placeholder="Bill Lexos"/>
//                 <input type="email"
//                 value={email}
//                 onChange={event=>setEmail(event.target.value)}
//                 placeholder="your@mail.com"/>
//                 <input type="password"
//                 value={password}
//                 onChange={event=>setPassword(event.target.value)}
//                  placeholder="password"/>
//
//                 <button className="primary">Register</button>
//             </form>
//             <div className="p-2 text-center">
//                 Dont have an account ?
//                 <Link className="underline" to={"/login"}>Login </Link>
//             </div>
//
//
//         </div>
//      </div>
//  )
}