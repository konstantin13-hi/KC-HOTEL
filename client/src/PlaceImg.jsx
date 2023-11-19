


export default function PlaceImg({place ,index = 0,className =null}){

    if(!place.photos?.length){
        return '';
    }
    
    if(!className){
        className = "object-cover";
    }


   return (
     <div>
  <img className ={className} src={'http://localhost:8080/uploads/'+place.photos[index]} alt={place.title} />

     </div>
      
   )

}