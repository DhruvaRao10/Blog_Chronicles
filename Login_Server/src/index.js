const express = require('express');
const path  = require('path');
const bcrypt = require('bcrypt');
//const collection = require('./config') ; 
// const router = require('express').Router() ; 
const session = require('express-session') ; 
const passport = require('passport');
const morgan = require('morgan')  ; 
// const passport  = require('x') ;       
require('./auth') ;         
const{collection} = require('./config') ; 
// const Blog = require('./config') ; 

const app = express() ;                               
app.use(express.json())  // conversion to jsonformat  
app.use(express.static(path.join(__dirname,'client')))          

app.use(express.urlencoded({extended: false}));

app.set('view engine','ejs'); 
app.use(express.static('public')) ;

var mongoose = require('mongoose');
// const BlogsSchema = new mongoose.Schema({
//                         Topic:String,
//                         Title:String,
//                         Snippet:String,
//                         Body:String,
//                     }) ;
//  console.log(" before model creation ") ;                     
 

//  const blogsmodel =  mongoose.model('Blog') ;
// const Blog = require('./config').Blog ; 

const blogSchema=new mongoose.Schema({
      title:{type:String, required:true},
      snippet:{type:String, required:true},
      body:{type:String, required:true}
  },{timestamps:true});
  

 
  const Blog = mongoose.model('Blog', blogSchema);


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
       res.redirect('http://localhost:300/home') ;  
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

app.get('/about',(req,res)=>{ 
      res.render('about' , {title:'About'})
  })
  
  app.get('/blog/create', (req,res) =>{
      res.render('create',{title:'Create a new Blog'})
  })
  
  app.get('/home', (req,res) =>{
      res.render('home',{title:'Create a new Blog'})
  })
  
  app.get('/blogs', (req,res)=>{
      Blog.find().sort({createdAt:-1})
      .then((result)=>{
          res.render('index', {title: 'ALL Blogs', blogs: result})
      })
      .catch((err)=>{
          console.log(err);
      })
})
  

// app.get('/login',(req,res) => {
//        res.render('home') ; 
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
             const checkEmail = await collection.findOne({email: req.body.email}) ; 
             if(!checkUser || !checkEmail){
                   res.send("user not found") ; 
             }
             const isPassMatch = await bcrypt.compare(req.body.password,checkUser.password);
             if(isPassMatch){
                       res.redirect('/login') ; 
                       return ; 
             }
             else{
                  req.send("wrong password");
             }
       }catch{
              res.send("wrong login details") ; 
       }
       
})

app.get('/login',(req,res) => {
      res.redirect('/home') ; 
})


app.post("/search",async(req,res)=>{
      try{                              
            
        console.log(" in search method ") ;
        console.log(req.body) ; 

        const midstringtitle = req.body.searchInput ;
        const midstringsnippet = req.body.searchInput ;  
        const midstringbody = req.body.searchInput ;

        console.log(" before 3 regex patterns");
        const regexPattern = new RegExp(`.*${midstringtitle}.*`,'i') ;   
        const regexPattern1 = new RegExp(`.*${midstringsnippet}.*`,'i') ;       
        const regexPattern2 = new RegExp(`.*${midstringbody}.*`,'i') ; 
                                                                                                         
        existingTitle = await Blog.find({ title : {$regex: regexPattern }}) ;
        existingSnippet = await Blog.find({snippet : {$regex: regexPattern1 }}) ; 
        existingBody = await Blog.find({ body :  {$regex: regexPattern2 }}) ;  
3
        const combinedResults = existingTitle.concat(existingSnippet , existingBody);
      //   console.log(" map print ");
      //   console.log(Array.from(combinedResults.map(id => combinedResults.find(result => result._id === id))));
        // Remove duplicates based on a unique identifier (e.g., _id)
        const uniqueResults = Array.from(new Set(combinedResults.map(result => result._id)))
                                   .map(id => combinedResults.find(result => result._id === id));
       if(uniqueResults){
         console.log(uniqueResults);
         res.send(uniqueResults); // Send the actual data from the database
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


app.post('/blogs',(req,res)=>{
      const blog=new Blog(req.body) //creating a new instance of Blog schema and passing the submitted data in form of req.body
      blog.save() //saving it to the database
      .then((result)=>{
          res.redirect('/blogs');
      })
      .catch((err)=>{
          console.log(err);
      })
  })


//   app.get('/search',(req,res))


  app.get('/blogs/:id',(req,res)=>{
      const id=req.params.id;
      Blog.findById(id)
      .then(result=>{
          res.render('details', {blog : result , title:'Blog Details'})
      })
      .catch(err=>{
          console.log(err);
      })
  
  })
  app.delete('/blogs/:id',(req,res)=>{
      const id=req.params.id;
      Blog.findByIdAndDelete(id)
      .then(result=>{
          res.json( {redirect:  '/blogs' })
      })
      .catch(err=>{
          console.log(err);
      })
  })
  
  app.use((req,res)=>{
      res.status(404).render('404',{title: '404'})
  })
  
  

// app.post("/createblog",async(req,res)=>{
//       try{
            
//           console.log(" in  create blog method ") ;
         
       
//           const newblog = new blogsmodel({ Topic: req.body.Topic,
//                                            Title: req.body.Title, 
//                                            Content: req.body.Content 
//                                          }); 
//                                          console.log(" newblog created") ;                               
//           newblog.save();
//           console.log("new blog saved" );
//           res.json(newblog);
//     }
//       catch{
//             console.error("Error in creating blog:");
//             res.status(500).send("Internal Server Error");
//       }
//      // mongoose.connection.close();
// }) 

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

// app.get('/auth/google/failure', (req,res)=>{
//       res.send('Authentication Failed !') ; 
// }) ; 





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