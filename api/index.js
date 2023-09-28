const express = require('express')
const cors = require('cors');
const app = express();
const jwt = require('jsonwebtoken');
const User = require('./modules/User')
const bcrypt =require('bcryptjs')
const bcryptSalt = bcrypt.genSaltSync(10);

require('dotenv').config()
const jwtSecret = 'adasdafsdgagsdfgs';

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
    try{
        const userDoc = await User.create({
            name,
            email,
            password:bcrypt.hashSync(password,bcryptSalt),
        })
        res.json(userDoc);
    }
    catch(e){
        res.status(422).json(e);

    }
   
})

app.post('/login',async(req,res)=>{
    const {email,password} = req.body;
  const userDoc = await User.findOne({email})
  if(userDoc){
    const passOk=bcrypt.compareSync(password,userDoc.password)
   if(passOk){
    jwt.sign({email:userDoc.email,id:userDoc.id},jwtSecret,{},(err,token)=>{
        if(err)throw err;
        res.cookie('token',token).json('pass is ok');
    }) ;
}
   else{
    res.status(422).json('pass isnt ok');
   }
    
  }else{
    res.json('not found');
  }
}
)

//sBbhgiwCCQ6ImopI
