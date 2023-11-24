// const express = require('express') ; 
// const session = require('express-session') ; 
// const mongoose = require('mongoose') ; 

// const app = express() ; 

// mongoose.connect('mongodb://localhost:27017/blog',{
//     useNewUrlParser: true , 
//     useCreateIndex: true , 
//     useUnifiedTopology: true 
// })

// .then(res => {
//      console.log('MongoDB connected') ; 
// });

// const store = new MongoDBSession({
//     uri: mongoURI,
//     collection: 'mySession',
// })

// app.use(
//     session({
//          secret: "key that will sign cookie",
//          resave: false , 
//          saveUninitialized: false,
//     })
// );


// app.get('/session',(req,res) => {
//     req.session.isAuth = true ; 
//     console.log(req.session);
//     console.log(req.session.id) ;
//     res.send('Hello Session');
// });

// // app.listen(5000,console.log("server running on http://localhost:4000")) 

