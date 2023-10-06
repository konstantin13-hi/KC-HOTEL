import { useState ,useEffect} from "react";
import { Link, useParams } from "react-router-dom"
import Perks from "../Perks";
import axios from "axios";
import PhotosUploder from "../PhotosUploder";
import { Navigate } from "react-router-dom";
import PlacesForm from "./PlacesFormPage";
import PlacesFormPage from "./PlacesFormPage";
import AccountNav from "./AccountNav";
export default function PlacesPage(){
    const [places, setPlaces] = useState([]);
      
    useEffect(() => {
        axios.get('/places')
          .then(({ data }) => {
            // console.log(data);
            setPlaces(data);
          })
          .catch(error => {
            console.error('error bla bla bla', error);
          });
      }, []); 

 
    return (
        <div className="p-8">
            <AccountNav/>
 
                <div className="text-center">
                
                <Link className="inline-flex bg-primary text-white py-2 px-4 rounded-full" to={'/account/places/new'}>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                </svg>
                Add new place</Link>

                </div>

                <div className="mt-4">
  {places.length > 0 && places.map(place => (
    <Link to={'/account/places/'+place._id} className="flex cursor-pointer bg-gray-200 p-2 rounded-2xl gap-2 mt-4" key={place._id}>
      <div className="flex w-32 h-32 bg-gray-400 shrink-0">
        {place.photos.length > 0 && (
          <img className ="object-cover" src={'http://localhost:4000/uploads/'+place.photos[0]} alt={place.title} />
        )}
      </div>
      <div className="grow-0 shrink">
      <h2 className="text-xl">{place.title}</h2>
      <p className="text-sm mt-2">{place.description}</p>

      </div>
    
      
      {place.owner}
      </Link>
  ))}
</div>

        </div>
    )
}