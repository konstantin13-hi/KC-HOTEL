import { createContext, useEffect, useState } from "react"
import axios from "axios";
export const UserContext = createContext({});


export function UserContextProvider({children}){
const [user,setUser] = useState(null);
const [ready,setReady] = useState(false);
const [token,setToken] = useState(null);



   useEffect(()=>{
    if(!user){
    setToken(localStorage.getItem('token'));
    const token = localStorage.getItem('token');

    console.log("token is here=" +token);
    if (token!=null){
  axios.get('http://localhost:8080/profile' ,{
               headers: {
                             Authorization: `Bearer ${token}`,}
                           }
                ).then(({data})=>{
                     setUser(data);
                     setReady(true);
                     setToken(token);
                 })
        }
    else {
        setReady(true);
    }

    }


   },[user])



    return (
        <UserContext.Provider value={{user,setUser,ready,token}}>
               {children}
        </UserContext.Provider>

    );

}