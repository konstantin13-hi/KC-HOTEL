import { useState } from "react";
import { Link, useParams } from "react-router-dom"
import Perks from "../Perks";
import axios from "axios";
import PhotosUploder from "../PhotosUploder";


export default function PlacesPage(){
    const {action} = useParams();
    const[title,setTitle] = useState('');
    const[address,setAddress] = useState('');
    const[addedPhotos,setAddedPhotos] = useState([]);
    const[photoLink,setPhotoLink] = useState('');
    const[description,setDescription] = useState('');
    const[perks,setPerks] = useState([]);
    const[extraInfo,setExtraInfo] = useState('');
    const[checkIn,setCheckIn] = useState('');
    const[checkOut,setCheckOut] = useState('');
    const[maxGuest,setMaxGuest]= useState(1)

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

  
    
    
    

    return (
        <div className="p-8">
            {action !=='new' &&(

                    <div className="text-center">
                    <Link className="inline-flex bg-primary text-white py-2 px-4 rounded-full" to={'/account/places/new'}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                    </svg>
                    Add new place</Link>

                    </div>

            )}

            {action === 'new' &&(
                <div>
                    <form>
                        {preInput('Title','Title for your place....')}
                        <input type ="text" value={title} onChange={event => {setTitle(event.target.value)}} placeholder="title............"/>

                        <h2 className="text-2xl mt-4"> Address </h2>
                        <p className="text-gray-500">Address to this place.... </p>
                        <input type ="text" value={address} onChange={event => {setAddress(event.target.value)}} placeholder="address............"/>
                        <h2 className="text-2xl mt-4"> Photos </h2>
                        <p className="text-gray-500">more = better </p>
                       <PhotosUploder/>
                         <h2 className="text-2xl mt-4"> Description </h2>
                        <p className="text-gray-500">description of the place</p>
                        <textarea value={description} onChange={event => {setDescription(event.target.value)}}/>
                        <h2 className="text-2xl mt-4"> Perks </h2>
                        <p className="text-gray-500">select all the perks of your place</p>
                        <div className="grid gap-1 grid-cols-2 md:grid-cols-4 lg:grid-cols-6 ">
                            <Perks selected={perks} onChange={setPerks}/>
                        </div>
            


            </form>
            <h2 className="text-2xl mt-4"> Extra Info </h2>
            <p className="text-gray-500">house rules , ect</p>
            <textarea value={extraInfo} onChange={event => {setExtraInfo(event.target.value)}} />
            <h2 className="text-2xl mt-4"> Check in&out times </h2>
            <p className="text-gray-500">add check in and out</p>

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
                         <input type="number" value={maxGuest} onChange={event => {setMaxGuest(event.target.value)}} /> 
                    </div>
                          
            </div>
                    <button className="primary my-4">Save</button>
              
                </div>
            )}
        </div>
        

       
        
    )

}