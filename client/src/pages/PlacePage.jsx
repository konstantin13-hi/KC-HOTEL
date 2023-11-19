import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import BookingWidget from "../BookingWidget";
import PlaceGallery from "../PlaceGallery";
import AddressLink from "../AddressLink";
export default function PlacePage(){
  const {id} = useParams();
  const [place,setPlace] = useState(null);
  const [showAllPhotos,setShowAllPhotos] = useState(false);

  useEffect(()=>{
    if(!id){
        return;
    }
    axios.get(`http://localhost:8080/places/${id}`).then(response => {
        setPlace(response.data);
    })

  },[id])

  if(!place) return '';

  if (showAllPhotos) {
    return (
      <div className="absolute inset-0 bg-black  text-white min-h-screen">
        <div className="p-8 grid gap-4 bg-black">
            <div>
                <h2 className="text-3xl">Photos of {place.title}</h2>
                <button onClick={()=>setShowAllPhotos(false)} className="fixed flex gap-1 py-2 px-2 px-4 rounded-2xl shadow shadow-black text-black" >
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
  <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
</svg>

                    Close photos</button>
            </div>
        {place?.photos?.length > 0 &&
          place.photos.map((photo, index) => (
            <div key={index}>
              <img   style={{ width: '100%', objectFit: 'cover' }} src={'http://localhost:8080/uploads/' + photo} alt="" />
            </div>
          ))}
          </div>
      </div>
    );
  }

  return (
    <div className="mt-4 bg-gray-100 -mx-8 px-8 pt-4">
        dsdsdsd
        <h1 className="text-2xl">{place.title}</h1>
       <AddressLink>{place.address}</AddressLink>
      <PlaceGallery place={place}/>
       
        <div className="mt-4 mb-4 gap-8 grid grid-cols-1 md:grid-cols-[2fr_1fr]">
            <div>
                <div className="my-4">
                    <h2 className="font-semibold text-2xl">Description</h2>
                    {place.description}
                </div>
                Check-in: {place.checkIn}<br/>
                Check-out: {place.checkOut}<br/>
                Max number of guest: {place.maxGuests}
                <div className="mt-2 text-sm gray-gray-700 leading-4">{place.extraInfo}</div>
            </div>

           <div>
           <BookingWidget place={place}/>

           </div>

        </div>

        <div className="bg-white -mx-8 px-8 py-8 border-t">
        <div>
            <h2 className="font-semibold text-2xl">Extra info </h2>
        </div>
        <div className="mt-2 text-sm gray-gray-700 leading-4">{place.extraInfo}</div>
       

        </div>
       
       
    </div>
  )
}