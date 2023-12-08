
import { useContext, useEffect, useState } from "react"
import { differenceInCalendarDays } from "date-fns";
import axios from "axios";
import { Navigate, useLocation, useParams } from "react-router-dom";
import { UserContext } from "../UserContext.jsx";


export default function BookingWidget({place}){
  const [checkIn,setCheckIn] = useState('');
  const [checkOut,setCheckOut] = useState('');
  const [numberOfGuests,setNumberOfGuests] = useState(1);
  const [name,setName] = useState('');
  const [phone,setPhone] = useState('');
  const [redirect,setRedirect] = useState ('');
  const {user} = useContext(UserContext);

   useEffect(()=>{
    if(user){
        setName(user.name);
    }
   },[user])

  let numberOfDays =0;
  if(checkIn && checkOut){
    numberOfDays = differenceInCalendarDays(new Date(checkOut),new Date(checkIn));
  }

  async function bookThisPlace (){
      const token = localStorage.getItem('token');
      const response = await axios.post('http://localhost:8080/createbookings', {
          placeId: place.id,
          checkIn,
          checkOut,
          numberOfGuests,
          name,
          phone,
          price: numberOfDays * place.price
      }, {
          headers: {
              Authorization: `Bearer ${token}`
          }
      });
      console.log("booking+="+token)
        const bookingId = response.data.id;
        setRedirect(`/account/bookings/${bookingId}`)
   
  }
  if (redirect){
    return <Navigate to={redirect}/>
  }
  


return (


    <div className="bg-white p-4 rounded-2xl shadow">
    <div className="text-2xl text-center">
    Price : ${place.price} /  per night
    </div>
    <div className="border rounded-2xl mt-4">
        <div className="flex">
            <div className="my-3 px-4 ">
                <label>Check in:</label>
                <input value={checkIn} onChange={ev =>setCheckIn(ev.target.value)} type="date" />
            </div>
            <div className="my-3 px-4 border-l">
                <label>Check out:</label>
                <input value={checkOut} onChange={ev =>setCheckOut(ev.target.value)} type="date" />
            </div>

         </div>
         <div className="my-3 px-4 border-t">
             <label>Number of guests:</label>
             <input value={numberOfGuests} onChange={ev =>setNumberOfGuests(ev.target.value)}  type="number" />

         </div>

         {numberOfDays > 0 && (
            <div className="my-3 px-4 border-t">
            <label>Your full name:</label>
            <input value={name} onChange={ev =>setName(ev.target.value)}  type="text" />
            <label>Phone number:</label>
            <input value={phone} onChange={ev =>setPhone(ev.target.value)}  type="tel" />
           
            </div>
         )}

    </div>

 

    
    <button onClick={bookThisPlace} className="primary mt-4">
         Book this place
       
         
         </button>
</div>


)
}