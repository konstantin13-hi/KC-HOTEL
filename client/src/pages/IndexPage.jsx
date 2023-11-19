import { Link } from "react-router-dom";
import Header from "../Header";
import { useEffect, useState } from "react";
import axios from "axios";


export default function IndexPage(){

   const [places,setPlaces] = useState();
   useEffect(()=>{
    axios.get('http://localhost:8080/places').then(response=>{
      setPlaces(response.data);
      console.log(response.data)
    })
   },[])

console.log(places);
    return(
  
        <div className="mt-8 grid gap-x-6 gap-y-8 grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
        {places?.length > 0 && places.map(place => (
      
          <Link to={'/place/'+place.id} key={place.id}>
            <div className="bg-gray-500 flex rounded-2xl">
            {place.photos?.[0] && (
              <img className="rounded-2xl aspect-square" src={`http://localhost:8080/uploads/${place.photos?.[0]}`} alt="" />
            )}
             </div>
             <h2 className="text-sm truncate leading-4">{place.title}</h2>
             <h3 className="font-bold leading-4">{place.address}</h3>
         
          </Link>
        ))}
      </div>

    )
}