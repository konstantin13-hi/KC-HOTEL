
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom"
import Perks from "../Perks";
import axios from "axios";
import PhotosUploder from "../PhotosUploder";
import { Navigate } from "react-router-dom"


export default function PlacesFormPage(){
   const {id} = useParams();


    const[title,setTitle] = useState('');
    const[address,setAddress] = useState('');
    const[description,setDescription] = useState('');
    const[perks,setPerks] = useState([]);
    const[extraInfo,setExtraInfo] = useState('');
    const[checkIn,setCheckIn] = useState('');
    const[checkOut,setCheckOut] = useState('');
    const[maxGuests,setMaxGuests]= useState(1)
    const[addedPhotos,setAddedPhotos] = useState([]);
    const [redirect,setRedirect] = useState(null);

    useEffect(() => {
        if (!id) {
          return;
        }
       axios.get('/places/'+id).then(response =>{
        const {data} =response ;
        console.log(data.title);
        setTitle(data.title);
        setAddress (data.address);
        setAddedPhotos (data.photos);
        setDescription (data.description);
        setPerks(data.perks);
        setExtraInfo(data.extraInfo);
        setCheckIn(data.checkIn);
        setCheckOut(data.checkOut);
        setMaxGuests(data.maxGuests);
       })
     
      }, [id]);

    function inputHeader(text){
        return (
            <h2 className="text-2xl mt-4"> {text} </h2>
        )
    }

    function inputDescription(text){
        return (
            <p className="text-gray-500">{text}</p>
        )
    }
    function preInput(header,description){
        return (
            <>
            {inputHeader(header)}
            {inputDescription(description)}
            </>
        )
    }


    async function savePlace(ev) {
        ev.preventDefault ();
        const placeData = {
        title, address, addedPhotos, 
        description,
         perks, extraInfo,
          checkIn, 
          checkOut, maxGuests
        };
        if (id) {
     const {data}  =  await axios.put('/places', {id, ...placeData});
       console.log(data);
        setRedirect ('/account/places');
        } else {
         await axios.post('/places', placeData);
        setRedirect (true);
        }
    }



    if (redirect) {
        return <Navigate to={redirect} />
    }



return (

         <div className="p-8">
                    <form onSubmit={savePlace}>
                        {preInput('Title','Title for your place....')}
                        <input type ="text" value={title} onChange={event => {setTitle(event.target.value)}} placeholder="title............"/>

                        {preInput('Address','Address to this place....')}
                        <input type ="text" value={address} onChange={event => {setAddress(event.target.value)}} placeholder="address............"/>

                        {preInput('Photos','more = better')}
                       <PhotosUploder addedPhotos={addedPhotos} onChange={setAddedPhotos}/>

                       {preInput('Description','description of the place')}
                        <textarea value={description} onChange={event => {setDescription(event.target.value)}}/>

                        {preInput('Perks','select all the perks of your place')}
                        <div className="grid gap-1 grid-cols-2 md:grid-cols-4 lg:grid-cols-6 ">
                            <Perks selected={perks} onChange={setPerks}/>
                        </div>
                 

                  {preInput('Extra Info','house rules , ect')}
                 <textarea value={extraInfo} onChange={event => {setExtraInfo(event.target.value)}} />

                 {preInput('Check in&out times','add check in and out')}
                <div className="grid gap-2 sm:grid-cols-3">
                        <div >
                            <h3 className="mt-2 -mb-1">Check in time </h3>
                            <input type="text" value={checkIn} onChange={event => {setCheckIn(event.target.value)}} placeholder="14:00"/> 
                        </div>

                        <div>
                            <h3 className="mt-2 -mb-1">Check out time </h3>
                            <input type="text" value={checkOut} onChange={event => {setCheckOut(event.target.value)}} /> 
                        </div>

                        <div>
                            <h3 className="mt-2 -mb-1">Max number of guest </h3>
                            <input type="number" value={maxGuests} onChange={event => {setMaxGuests(event.target.value)}} /> 
                        </div>
                            
                </div>
                <button className="primary my-4">Save</button>
                </form>
        </div>
)

}