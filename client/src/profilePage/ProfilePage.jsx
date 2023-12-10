import { Navigate, useParams } from "react-router-dom";
import { UserContext } from "../UserContext.jsx"
import { useContext , useState} from "react"
import { Link } from "react-router-dom";
import axios from "axios";
import PlacesPage from "./PlacesPage.jsx";
import AccountNav from "./AccountNav.jsx";

export default function ProfilePage(){
  const {user,ready,setUser} = useContext(UserContext);
  const [redirect,setRedirect] = useState(false);
  let {subpage} =useParams();
    if (subpage === undefined){

        subpage = 'profile';
    }

  console.log("subpage is = "+subpage);

         if(!ready){
        return "Loading Loading Loading Loading Loading";
      }
    if (!redirect && ready  && !user) {
        return <Navigate to={'/login'} />
    }




 async function logOut(){
   await axios.post('http://localhost:8080/logOut');
   localStorage.removeItem('token');
     setRedirect(true);
   setUser(null);
  }



    if (redirect){
       return <Navigate  to={'/'}/>
     }




    return (
               <div>
                 <AccountNav />
                 {subpage === 'profile' && (
                   <div className="text-center max-w-lg mx-auto">
                     Logged in as {user.name} ({user.email})<br />
                     <button onClick={logOut} className="primary max-w-sm mt-2">Logout</button>
                   </div>
                 )}
                 {subpage === 'places' && (
                   <PlacesPage />
                 )}

               </div>
             );

}