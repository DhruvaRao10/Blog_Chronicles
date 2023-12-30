const mongoose=require('mongoose')
const Schema=mongoose.Schema;

const blogSchema=new Schema({
    title:{type:String, required:true},
    snippet:{type:String, required:true},
    body:{type:String, required:true},
    imageUrl:{type:String, required:false, default:""},
    videoUrl:{type:String, required:false, default:""},
},{timestamps:true});


// first argument is th.e name which it should search for in the database : Second argument is the schema/ type of object to should store in database
const Blog=mongoose.model('Blog', blogSchema);

module.exports=Blog; 