export default function LoginPage(){
    return (
       <div className="flex items-center justify-around grow"> 
        <div className="mb-64">
            <h1 className="text-center">Login</h1>
<form className="max-w-md mx-auto">
    <input type="email" placeholder="your@mail.com"/>
    <input type="text" placeholder="password"/>
    <button className="primary">Login</button>
</form>

        </div>
        </div>
    )
}