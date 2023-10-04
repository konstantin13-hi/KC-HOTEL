


export default function Perks ({selected,onChange}){
    
    function handleCbClick(ev){
        const {checked, name} = ev.target;
        if (checked) {
        onChange ([...selected, name]);
        } else {
        onChange([ ...selected.filter(selectedName => selectedName !==name)]);
    }
    }

    return (
        <>
          <label className="border p-4 flex rounded-2xl gap-2 items-center cursor-pointer">
                            <input type="checkbox" name="wifi" onChange={handleCbClick}/>
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M8.288 15.038a5.25 5.25 0 017.424 0M5.106 11.856c3.807-3.808 9.98-3.808 13.788 0M1.924 8.674c5.565-5.565 14.587-5.565 20.152 0M12.53 18.22l-.53.53-.53-.53a.75.75 0 011.06 0z" />
                            </svg>
                            <span>Wifi</span>
            </label>

            <label className="border p-4 flex rounded-2xl gap-2 items-center cursor-pointer">
                            <input type="checkbox" name="parking" onChange={handleCbClick}/>
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M8.25 18.75a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m3 0h6m-9 0H3.375a1.125 1.125 0 01-1.125-1.125V14.25m17.25 4.5a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m3 0h1.125c.621 0 1.129-.504 1.09-1.124a17.902 17.902 0 00-3.213-9.193 2.056 2.056 0 00-1.58-.86H14.25M16.5 18.75h-2.25m0-11.177v-.958c0-.568-.422-1.048-.987-1.106a48.554 48.554 0 00-10.026 0 1.106 1.106 0 00-.987 1.106v7.635m12-6.677v6.677m0 4.5v-4.5m0 0h-12" />
                            </svg>
                            <span>Free parking</span>
            </label>

            <label className="border p-4 flex rounded-2xl gap-2 items-center cursor-pointer">
                            <input type="checkbox"name="tv" onChange={handleCbClick}/>
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M6 20.25h12m-7.5-3v3m3-3v3m-10.125-3h17.25c.621 0 1.125-.504 1.125-1.125V4.875c0-.621-.504-1.125-1.125-1.125H3.375c-.621 0-1.125.504-1.125 1.125v11.25c0 .621.504 1.125 1.125 1.125z" />
                            </svg>
                            <span>TV</span>
            </label>

            <label className="border p-4 flex rounded-2xl gap-2 items-center cursor-pointer">
                            <input type="checkbox" name="pets" onChange={handleCbClick}/>
                                                <svg fill="#000000" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" 
                        width="20px" height="20px" viewBox="0 0 512 512" enableBackground="new 0 0 512 512" xmlSpace="preserve">
                    <g>
                        <path d="M256-0.5C114.333-0.5-0.5,114.333-0.5,256S114.333,512.5,256,512.5S512.5,397.667,512.5,256S397.667-0.5,256-0.5z
                            M256,472.422C136.47,472.422,39.578,375.53,39.578,256C39.578,136.47,136.47,39.578,256,39.578
                            c119.53,0,216.422,96.892,216.422,216.422C472.422,375.53,375.53,472.422,256,472.422z"/>
                        <path d="M133.48,236.619c-9.394,8.673-6.982,27.397,5.385,41.863c12.368,14.45,29.996,19.131,39.374,10.427
                            c9.378-8.643,6.967-27.397-5.401-41.832C160.47,232.642,142.842,227.945,133.48,236.619z"/>
                        <path d="M338.833,243.757c-11.006,15.625-11.694,34.52-1.55,42.223c10.129,7.671,27.257,1.314,38.247-14.325
                            c10.99-15.593,11.679-34.473,1.55-42.191C366.95,221.777,349.808,228.18,338.833,243.757z"/>
                        <path d="M226.129,240.125c16.517-3.977,25.111-28.65,19.179-55.107c-5.887-26.458-24.078-44.681-40.595-40.705
                            c-16.501,4.008-25.08,28.681-19.194,55.139C191.452,225.879,209.628,244.102,226.129,240.125z"/>
                        <path d="M309.057,144.313c-16.501-3.977-34.677,14.247-40.595,40.705c-5.918,26.458,2.661,51.131,19.193,55.107
                            c16.485,3.976,34.677-14.247,40.595-40.673C334.184,172.995,325.558,148.321,309.057,144.313z"/>
                        <path d="M256,262.513c-39.374,0-68.932,62.7-68.932,83.444c0,51.005,29.558-1.613,68.932-1.613c39.39,0,68.932,52.618,68.932,1.613
                            C324.932,325.213,295.39,262.513,256,262.513z"/>
                    </g>
                    </svg>
                            <span>Pets</span>
            </label>
            <label className="border p-4 flex rounded-2xl gap-2 items-center cursor-pointer">
                            <input type="checkbox" name="entrence" onChange={handleCbClick}/>
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 9V5.25A2.25 2.25 0 0013.5 3h-6a2.25 2.25 0 00-2.25 2.25v13.5A2.25 2.25 0 007.5 21h6a2.25 2.25 0 002.25-2.25V15M12 9l-3 3m0 0l3 3m-3-3h12.75" />
                            </svg>
                            <span>Entrence</span>
            </label>

                     
        </>
    )
}