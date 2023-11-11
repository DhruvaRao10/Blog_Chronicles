const mongoose = require('mongoose');
const connect = mongoose.connect("mongodb://localhost:27017/Blog_Chronicles");

connect.then(() =>{
    console.log("Database connected");
})

.catch(() =>{
   console.log("Database not connected");
})

//Schema

const LoginSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true 
    },

    email:{
        type: String,
        required:true  
    },

    password:{
        type:String,
        required: true 
    }
});

const collection = new mongoose.model('Users',LoginSchema) ;

module.exports = collection ; 