import { useState } from "react";
import { Link, useParams } from "react-router-dom"
import Perks from "../Perks";
import axios from "axios";

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

   async function addPpotoBylink(event){
    event.preventDefault();
     const {data : filename} =   await axios.post('/upload-by-link',{link:photoLink})
     setAddedPhotos(prev =>{
        return[...prev,filename]
     })
     setPhotoLink('')
    }

    function uploadPhoto(ev) {
        const files = ev.target.files;
      
        const data = new FormData();
        for (let i = 0; i < files.length; i++) {
          data.append('photos', files[i]);
        }
        console.log(data);
      
        axios.post('/upload', data, {
          headers: { 'Content-Type': 'multipart/form-data' }
        })
        .then(response => {
          const { data: filenames } = response;
          setAddedPhotos(prev => {
            return [...prev, ...filenames];
          });
        })
        .catch(error => {
          console.error('Error when enabling photos:', error);
        });
      }
    
    
    

    return (
        <div>
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
                        <div className="flex gap-2">
                            <input type="text" value={photoLink} onChange={event => {setPhotoLink(event.target.value)}} placeholder="Add using a link"/>
                            <button onClick ={addPpotoBylink} className="bg-gray-200 px-4 rounded-2xl">Add&nbsp;photo</button>
                        </div>
                        
                         <div className="grid gap-2 grid-cols-3 md:grid-cols-4 lg:grid-col-6">
                            {addedPhotos.length> 0 && addedPhotos.map(link=>(
                                <div className="h-32 flex">
                                   <img className="rounded-2xl w-full object-coverl" src={'http://localhost:4000/uploads/'+link} alt="" /> 
                                </div>
                            ))}
                            <label onChange={uploadPhoto}  className="h-32 curser-pointer flex gap-1 items-center justify-center border bg-transparent rounded-2xl p-8">
                            <input type="file" multiple className="hidden"/>
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-10 h-10">
                                <path strokeLinecap="round" strokeLinejoin="round" d="M12 16.5V9.75m0 0l3 3m-3-3l-3 3M6.75 19.5a4.5 4.5 0 01-1.41-8.775 5.25 5.25 0 0110.233-2.33 3 3 0 013.758 3.848A3.752 3.752 0 0118 19.5H6.75z" />
                                </svg>
                                Upload 


                            </label>
                         </div>
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