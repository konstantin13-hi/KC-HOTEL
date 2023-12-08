import {Navigate, useParams} from "react-router-dom";
import {useContext, useEffect, useState} from "react";
import axios from "axios";
import AddressLink from "../AddressLink";
import PlaceGallery from "../PlaceGallery";
import BookingDates from "../BookingDates";
import {UserContext} from "../UserContext.jsx";

export default function BookingPage() {
  const {id} = useParams();
  const [booking,setBooking] = useState(null);
  const {user,ready,setUser} = useContext(UserContext);
  const token = localStorage.getItem('token');
  console.log("render component book")







  useEffect(() => {
    console.log("useEffect in book")
    if (id) {
      axios.get('http://localhost:8080/bookings', {
        headers: {
          Authorization: `Bearer ${token}`,}
      }).then(response => {
        const foundBooking = response.data.find(({id}) => id === id);
        if (foundBooking) {
          setBooking(foundBooking);
        }
      });
    }
  }, [id]);

  if ( ready &&!user) {
    console.log("Navigate in book")
    return <Navigate to={'/login'} />
  }



  if(!booking){
    console.log("loding in book")
    return "Loading Loading Loading Loading Loading";
  }

  return (
    <div className="my-8">
      <h1 className="text-3xl">{booking.place.title}</h1>
      <AddressLink className="my-2 block">{booking.place.address}</AddressLink>
      <div className="bg-gray-200 p-6 my-6 rounded-2xl flex items-center justify-between">
        <div>
          <h2 className="text-2xl mb-4">Your booking information:</h2>
          <BookingDates booking={booking} />
        </div>
        <div className="bg-primary p-6 text-white rounded-2xl">
          <div>Total price</div>
          <div className="text-3xl">${booking.price}</div>
        </div>
      </div>
      <PlaceGallery place={booking.place} />
    </div>
  );
}