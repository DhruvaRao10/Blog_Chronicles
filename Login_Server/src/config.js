const mongoose = require('mongoose');
const connect = mongoose.connect("mongodb://localhost:27017/Blog_Chronicles");
// const mongoURI = 'mongodb://localhost:27017/Blog_Chronicles' ; 

connect.then(() =>{
    console.log("Database connected");
})                 

.catch(() =>{
   console.log("Database not connected");
})




// connect.then({
//     useNewUrlParser: true , 
//     useCreateIndex: true , 
//     useUnifiedTopology: true 
// })
// .then((res)=>{
//      console.log('MongoDB connected') ; 
// }); 
//Schema


// const blogSchema=new mongoose.Schema({
//     title:{type:String, required:true},
//     snippet:{type:String, required:true},
//     body:{type:String, required:true}
// },{timestamps:true});


// // first argument is the name which it should search for in the database : Second argument is the schema/type of object to should store in database
// const Blog = mongoose.model('Blog', blogSchema);



                                             
const LoginSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true 
    },

    email:{
        type: String,
        required:true,  
        unique:true
    },

    password:{
        type:String,
        required: true 
    }
});

//  const SearchSchema = new mongoose.Schema({
//     Topic: {
//         type: String,
//         required: true 
//     },

//     Title:{
//         type: String,
//         required:true  
//     },

//     Content:{
//         type:String,
//         required: true 
//     }
// });

const collection = new mongoose.model('Users',LoginSchema) ;
//const searchcollection =  new mongoose.model('Search',SearchSchema) ;

module.exports = {
    collection,   
    //searchcollection
    // Blog
}