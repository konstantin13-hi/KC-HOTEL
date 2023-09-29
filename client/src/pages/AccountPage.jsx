import { Navigate, useParams } from "react-router-dom";
import { UserContext } from "../UserContext"
import { useContext , useState} from "react"
import { Link } from "react-router-dom";
import axios from "axios";

export default function AccountPage(){
  const {user,ready,setUser} = useContext(UserContext);
  const [redirect,setRedirect] = useState(false);
  let {subpage} =useParams();
  if (subpage === undefined){
   
    subpage = 'profile';
  }
  console.log(subpage)

  if(!ready){
    return "Loading";
  }

  if(!user && ready && !redirect){
    return <Navigate to={'/login'}/>
  }
  function linkClasses(type = null){
    let string = 'py-2 px-6';
    if (type ===subpage){
        string +=' bg-primary rounded-full text-white';

    }
   
    return string;

  }

 async function logOut(){
   await axios.post('/logout');
   setUser(null);
   setRedirect(true);
  }

  if (redirect){
    return <Navigate path={'/'} />
  }


    return (
      <div>
       <nav className="w-full justify-center flex p-2 mt-8 gap-4s">
        <Link className={linkClasses('profile')} to='/account'>My profile</Link>
        <Link className={linkClasses('bookings')}  to='/account/bookings'>My bookings</Link>
        <Link className={linkClasses('places')}  to='/account/places'>My accommodations</Link>

       </nav>
        <div className="text-center max-w-lg mx-auto">
            Logged in as {user.name} ({user.email}) <br/>
            <button onClick={logOut} className="primary max-w-sm mt-2">Logout</button>
        </div>
       
      </div>

    )

}