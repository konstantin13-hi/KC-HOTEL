import {useState, useEffect, useContext} from "react";
import { Link, useParams } from "react-router-dom"
import Perks from "../Perks.jsx";
import axios from "axios";
import PhotosUploder from "../PhotosUploder.jsx";
import { Navigate } from "react-router-dom";
import PlacesForm from "../pages/PlacesFormPage.jsx";
import PlacesFormPage from "../pages/PlacesFormPage.jsx";
import AccountNav from "./AccountNav.jsx";
import PlaceImg from "../PlaceImg.jsx";
import {UserContext} from "../UserContext.jsx";
export default function PlacesPage(){
    const [places, setPlaces] = useState([]);
    const token = localStorage.getItem('token');
    const [loading, setLoading] = useState(true);

    const {user,ready,setUser} = useContext(UserContext);




      
    useEffect(() => {
        axios.get('http://localhost:8080/user-places',  {
            headers: {
                Authorization: `Bearer ${token}`,}
        })
          .then(({ data }) => {

            setPlaces(data);
              setLoading(false);
          })
          .catch(error => {
            console.error('error bla bla bla', error);
              setLoading(false);
          });
      }, []);

    if (ready && !user) {
        return <Navigate to={'/login'} />
    }


 
   return (
     <div>
       <AccountNav />
       <div className="text-center">
         <Link className="inline-flex gap-1 bg-primary text-white py-2 px-6 rounded-full" to={'/account/places/new'}>
           <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-6 h-6">
             <path fillRule="evenodd" d="M12 3.75a.75.75 0 01.75.75v6.75h6.75a.75.75 0 010 1.5h-6.75v6.75a.75.75 0 01-1.5 0v-6.75H4.5a.75.75 0 010-1.5h6.75V4.5a.75.75 0 01.75-.75z" clipRule="evenodd" />
           </svg>
           Add new place
         </Link>
       </div>
       <div className="mt-2">

           {loading ? (
               // Если данные еще загружаются, отображаем сообщение Loading
               'Loading...'
           ) : (
               // Иначе, отображаем список places
               places.length > 0 && places.map(place => (
                   <Link to={`/account/places/${place.id}`} className="flex cursor-pointer gap-4 bg-gray-100 p-4 rounded-2xl mt-8 h-100 overflow-hidden" key={place.id}>
                       <div className="flex w-20 h-20 bg-gray-300">
                           <PlaceImg place={place} />
                       </div>
                       <div className="flex-grow-1 w-full">
                           <h2 className="text-xl">{place.title}</h2>
                           <p className="text-sm mt-2">
                               {place.description.length > 350
                                   ? `${place.description.slice(0, 350)}...`
                                   : place.description}
                           </p>
                       </div>
                   </Link>)
         ))}
       </div>
     </div>
   );
}