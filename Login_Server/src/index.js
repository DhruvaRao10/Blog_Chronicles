const express = require('express');
const path  = require('path');
const bcrypt = require('bcrypt');
const collection = require('./config') ; 
// const router = require('express').Router() ; 
const session = require('express-session') ; 
const passport = require('passport');
// const passport  = require('x') ; 
require('./auth') ;         

const app = express() ;                               
app.use(express.json())  // conversion to jsonformat  
app.use(express.static(path.join(__dirname,'client')))          

app.use(express.urlencoded({extended: false}));

app.set('view engine','ejs'); 
app.use(express.static("public")) ;

function isLoggedIn(req,res,next){
       req.user?next():res.sendStatus(401) ; 
}

app.get('/auth',(req,res)=>{
     res.sendFile('oauth.html') ; 
});

app.use(session({
      secret: 'mysecret',
      resave: false,
      saveUninitialized: true,
      cookie: {secure: false}
}));

app.use(passport.initialize());
app.use(passport.session()) ; 

app.get('/auth/google',
   passport.authenticate('google', { 
      scope: ['email' , 'profile'] }
));

app.get('/auth/google/callback', 
  passport.authenticate('google', { 
      successRedirect: '/auth/google/success',
      failureRedirect: '/auth/google/failure' 
}));
//   function(req, res) {
//     // Successful authentication, redirect home.
//     res.redirect('/');
//   });

app.get('/auth/google/failure', (req,res)=>{
      res.send('Authentication Failed !') ; 
}) ; 


app.get('/auth/protected',isLoggedIn,(req,res)=>{
       let name = req.user.displayName() ; 
       res.send('Hello $(name)') ; 
}) ; 

app.use('/auth/logout',(req,res) =>{
        req.session.destroy() ; 
        res.send("See you again");
})


app.get('/',(req,res)=>{
      res.render('login') ; 
});

app.get('/signup',(req,res) => {
       res.render('signup') ; 
}); 


app.post('/signup', async (req,res)=>{
         const data = {
            name: req.body.username,
            email:req.body.email, 
            password: req.body.password
         }
         const existingUser = await collection.findOne({name:data.name});

         if(existingUser){
              res.send("User exists choose another name") ; 
         }
         else{
             //pass hash
             const saltRounds = 10 ;
             const hashedPassword = await bcrypt.hash(data.password, saltRounds);

             data.password = hashedPassword

             const userdata = await collection.insertMany(data);
             console.log(userdata);

         }
      //    const userdata = await collection.insertMany(data);
      //    console.log(userdata);
})

app.post("/login",async (req,res) =>{
       try{
             const checkUser = await collection.findOne({name: req.body.username});
             const checkEmail = await collection.findOne({name: req.body.email}) ; 
             if(!checkUser || !checkEmail){
                   res.send("user not found") ; 
             }
             const isPassMatch = await bcrypt.compare(req.body.password,checkUser.password);
             if(isPassMatch){
                   res.render("home") ; 
             }
             else{
                  req.send("wrong password");
             }
       }catch{
              res.send("wrong login details") ; 
       }
       
})

const port = 5000 ; 
app.listen(port,() =>{
      console.log(`Server running on port: ${port}` ) ; 
})




