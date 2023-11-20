import { Navigate, useParams } from "react-router-dom";
import { UserContext } from "../UserContext"
import { useContext , useState} from "react"
import { Link } from "react-router-dom";
import axios from "axios";
import PlacesPage from "./PlacesPage";
import AccountNav from "./AccountNav";

export default function ProfilePage(){
  const {user,ready,setUser} = useContext(UserContext);
  const [redirect,setRedirect] = useState(false);
  let {subpage} =useParams();
  if (subpage === undefined){
   
    subpage = 'profile';
  }
  console.log(subpage);


 async function logOut(){
   await axios.post('http://localhost:8080/logout');
    setRedirect('/');
   setUser(null);
  }

   if (ready && !user && !redirect) {
      return <Navigate to={'/login'} />
    }

    if (redirect) {
      return <Navigate to={redirect} />
    }

     if(!ready){
        return "Loading";
      }


    return (
               <div>
                 <AccountNav />
                 {subpage === 'profile' && (
                   <div className="text-center max-w-lg mx-auto">
                     Logged in as {user.name} ({user.email})<br />
                     <button onClick={logout} className="primary max-w-sm mt-2">Logout</button>
                   </div>
                 )}
                 {subpage === 'places' && (
                   <PlacesPage />
                 )}
               </div>
             );

}