const express = require('express')
const cors = require('cors');
const app = express();
const User = require('./modules/User')
const bcrypt =require('bcryptjs')
const bcryptSalt = bcrypt.genSaltSync(10);

require('dotenv').config()


app.use(express.json());
app.use(cors({
credentials:true,
origin:'http://localhost:5173',

}
    ));
 const mongoose = require('mongoose');
 mongoose.connect(process.env.MONGO_URL);


app.get('/test',(req,res)=>{
    res.json('test ok');
})

app.listen(4000);

app.post('/register',async(req,res)=>{
    const {name,email,password}=req.body;
   const userDoc = await User.create({
        name,
        email,
        password:bcrypt.hashSync(password,bcryptSalt),
    })
    res.json(userDoc);
})
//sBbhgiwCCQ6ImopI
