const express = require('express');
const path  = require('path');
const bcrypt = require('bcrypt');
//const collection = require('./config') ; 
// const router = require('express').Router() ; 
const session = require('express-session') ; 
const passport = require('passport');
// const passport  = require('x') ; 
require('./auth') ;         
//const{collection,searchcollection} = require('./config') ;
const{collection} = require('./config') ; 

const app = express() ;                               
app.use(express.json())  // conversion to jsonformat  
app.use(express.static(path.join(__dirname,'client')))          

app.use(express.urlencoded({extended: false}));

app.set('view engine','ejs'); 
app.use(express.static("public")) ;

var mongoose = require('mongoose');
const BlogsSchema = new mongoose.Schema({
                        Topic:String,
                        Title:String,
                        Content:String,
                    }) ;
 console.log(" before model creation ") ;                     
 

 const blogsmodel =  new mongoose.model('Blogs',BlogsSchema) ;
 



function isLoggedIn(req,res,next){
       req.user?next():res.sendStatus(401) ; 
}

// app.get('/auth',(req,res)=>{
//      res.sendFile('oauth.html') ; 
// });

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
      successRedirect: '/auth/protected',
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
       let name = req.user.displayName ;
       res.send(`Hello ${name}`) ;  
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

// app.get('/login',(req,res) => {
//        res.redirect('http://localhost:300/home') ; 
// })


app.post('/signup', async (req,res)=>{
         const data = {
            name: req.body.username,
            email:req.body.email, 
            password: req.body.password
         }
         const existingUser = await collection.findOne({name:data.name});
         console.log(existingUser);

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
                  app.get('/login',(req,res) => {
                        res.redirect('http://localhost:300/home') ; 
                 })
             }
             else{
                  req.send("wrong password");
             }
       }catch{
              res.send("wrong login details") ; 
       }
       
})


app.post("/search",async(req,res)=>{
      try{
            
        console.log(" in search method ") ;
        const midstringtopic = req.body.SearchString;
        const midstringtitle = req.body.SearchString;
        const midstringcontent = req.body.SearchString;

        console.log(" before 3 regex patterns");
        const regexPattern = new RegExp(`.*${midstringtopic}.*`,'i') ;   
        const regexPattern1 = new RegExp(`.*${midstringtitle}.*`,'i') ;       
        const regexPattern2 = new RegExp(`.*${midstringcontent}.*`,'i') ;

        existingTopic = await blogsmodel.find({ Topic : {$regex: regexPattern }}) ;
        existingTitle = await blogsmodel.find({Title : {$regex: regexPattern1 }}) ; 
        existingContent = await blogsmodel.find({ Content :  {$regex: regexPattern2 }}) ;  
3
        const combinedResults = existingTopic.concat(existingTitle, existingContent);
        console.log(" map print ");
        console.log(Array.from(combinedResults.map(id => combinedResults.find(result => result._id === id))));
        // Remove duplicates based on a unique identifier (e.g., _id)
        const uniqueResults = Array.from(new Set(combinedResults.map(result => result._id)))
                                   .map(id => combinedResults.find(result => result._id === id));
       
       if(uniqueResults){
         console.log(uniqueResults);
         res.json(uniqueResults); // Send the actual data from the database
      }
      else {
            console.log("Topic could not be found");
            res.status(404).send("Topic could not be found");
        }        
     } 
     catch{
            console.error("Error in search route:");
            res.status(500).send("Internal Server Error");
     }
      //mongoose.connection.close();
})

app.post("/createblog",async(req,res)=>{
      try{
            
          console.log(" in  create blog method ") ;
         
       
          const newblog = new blogsmodel({ Topic: req.body.Topic,
                                           Title: req.body.Title, 
                                           Content: req.body.Content 
                                         }); 
                                         console.log(" newblog created") ;                               
          newblog.save();
          console.log("new blog saved" );
          res.json(newblog);
    }
      catch{
            console.error("Error in creating blog:");
            res.status(500).send("Internal Server Error");
      }
     // mongoose.connection.close();
}) 



const port = 5000 ; 
app.listen(port,() =>{
      console.log(`Server running on port: ${port}` ) ; 
})



















// const MongoDBSession = require('connect-mongodb-session')(session) ; 
// const mongoURI = "mongodb://localhost:27017/Blog_Chronicles" ; 

// const isAuth = (req,res,next)=>{
//       if(req.session.isAuth){
//              next() 
//       }else{
//              res.redirect('/') ; 
//       }
// }



// const store = new MongoDBSession({
//       uri: mongoURI ,
//       sessioncollection: "mySessions",
//   })

// app.use(
//       session({
//            secret: "key that will sign cookie",
//            resave: false , 
//            saveUninitialized: false,
//            store: store,
//       })
//     );

app.get('/auth/google/failure', (req,res)=>{
      res.send('Authentication Failed !') ; 
}) ; 





//  app.get('/session',isAuth,(req,res) => {
//       //  req.session.isAuth = true , 
//        res.send('Session exists') ; 
//  });
 
 
// app.get('/session',(req,res) => {
//       req.session.isAuth = true ; 
//       console.log(req.session);
//       console.log(req.session.id) ;
//       res.send('Testing session') ;
//     });