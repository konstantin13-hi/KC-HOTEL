import { Navigate, useLocation, useParams } from "react-router-dom";
import { UserContext } from "../UserContext.jsx"
import { useContext , useState} from "react"
import { Link } from "react-router-dom";
import axios from "axios";
import PlacesPage from "./PlacesPage.jsx";

export default function AccountNav(){
    const {pathname} = useLocation();
    console.log("useLocation =" + pathname);
    let subpage = pathname.split('/')?.[2];
    console.log("useLsubpage ="+subpage);
    if (subpage === undefined) {
    subpage = 'profile'}
    function linkClasses(type = null){
        let string = 'inline-flex py-2 px-6 gap-1 rounded-full';
        if (type === subpage){
            string +=' bg-primary text-white';
    
        }
        else{
          string += ' bg-gray-200'
        }
       
        return string;
    
      }

    return(
    <nav className="w-full justify-center flex p-2 mt-8 gap-4s">
    <Link className={linkClasses('profile')} to='/account'>
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
    <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z" />
    </svg>

      My profile</Link>
    <Link className={linkClasses('bookings')}  to='/account/bookings'>
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
    <path strokeLinecap="round" strokeLinejoin="round" d="M12 6.042A8.967 8.967 0 006 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 016 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 016-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0018 18a8.967 8.967 0 00-6 2.292m0-14.25v14.25" />
    </svg>
      My bookings</Link>
    <Link className={linkClasses('places')}  to='/account/places'>
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
    <path strokeLinecap="round" strokeLinejoin="round" d="M8.25 21v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21m0 0h4.5V3.545M12.75 21h7.5V10.75M2.25 21h1.5m18 0h-18M2.25 9l4.5-1.636M18.75 3l-1.5.545m0 6.205l3 1m1.5.5l-1.5-.5M6.75 7.364V3h-3v18m3-13.636l10.5-3.819" />
    </svg>

      My accommodations</Link>

   </nav>)
}