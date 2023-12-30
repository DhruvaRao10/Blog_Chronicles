const express= require('express');
const app= express();
const mongoose=require('mongoose');
const Blog =require('./models/blog')

require("dotenv").config();
//third party middleware
const morgan=require('morgan');
const fileupload = require("express-fileupload");
app.use(fileupload({
    useTempFiles : true,
    tempFileDir : '/tmp/'
}));
app.use(express.json());

//setting view engine as ejs
app.set('view engine', 'ejs');

const dbURI='mongodb://localhost:27017/blogImageNew'

app.listen(3003);
//connecting database
mongoose.connect(dbURI, {useNewUrlParser:true , useUnifiedTopology:true})
.then((result)=> console.log('connected to db'))
.catch((err)=> console.log(err))

const cloudinaryM = require("./config/cloudinary");
cloudinaryM.cloudinaryConnect();


// app.use((req,res)=>{
//     console.log('new request made');
//     console.log('host:',req.hostname);
//     console.log('path:',req.path);
//     console.log('method:',req.method);
    
// })

//instead of doing above commented code we can use third party middleware called morgan
// app.use(morgan('dev'))
//using inbuilt middleware to serve the static files to frontend
app.use(express.static('public'));
app.use(express.urlencoded({extended:true}));   //used for accepting form data in backend imp
app.use(morgan('dev')); // not necessary

const cloudinary = require("cloudinary").v2;

//******/storing in the database*****
// app.get('/add-blog', (req,res)=>{
//     const blog=new Blog({
    //         title:'new blog',
    //         snippet:'about my new blog',
    //         body:'more about my new blog'
    //     });
    //     blog.save()
    //     .then((result)=>{
        //         res.send(result)
//     })
//     .catch((err)=>{
    //         console.log(err);
    //     })
    
    // })

    // app.get('/all-blogs',(req,res)=>{
//     Blog.find()
//     .then((result)=>{
    //         res.send(result)
    //     })
    //     .catch((err)=>{
        //         console.log(err);
        //     })
        // })
        // **********************************
        app.get('/',(req,res)=>{
            // res.send('<H1>Hello At HOME PAGE</H1>') 
            // res.sendFile('./views/index.html', {root:__dirname}) 
            // const blogs=[
            //     // {title: 'Yoshi finds eggs', snippet: 'hferb iikukr ulurgt  iuk'},
            //     // {title: 'MArio finds stars', snippet: 'hferb iikukr ulurgt  iuk'},
            //     // {title: 'How to defeat browser', snippet: 'hferb iikukr ulurgt  iuk'}
            // ]
            // res.render('index' , {title:'Home', blogs})
            res.redirect('/home')
        })
    app.get('/about',(req,res)=>{ 
        // res.send('<H1>Hello At ABOUT PAGE</H1>')   
        // res.sendFile('./views/about.html', {root:__dirname}) 
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

async function uploadFileToCloudinary(file, folder){
    const options = {folder};
    // console.log("TempFilePath", file.tempFilePath);
    
    // console.log(options);
    options.resource_type = "auto"; 
    return await cloudinary.uploader.upload(file.tempFilePath, options);
}

//saving data to the mongodb
app.post('/blogs',async(req,res)=>{

    console.log(req);
    const {title,snippet, body} = req.body;
    const imagefile = req.files.image;
    const videofile = req.files.video;
    let imagelink,videolink;
        console.log(title);
        console.log(snippet);
        console.log(body);
        console.log(imagefile);
        console.log(videofile);
     //saving it to the database


        //UPLOAD IMAGE TO CLOUDINARY
        try{

            imagelink = await uploadFileToCloudinary(imagefile, "himanshu");
            console.log("Image Response :",imagelink);       

        }
        catch(err){
            console.log(err);
            return res.status(400).json({
                success:false,
                message:'Something went wrong in uploading image file'
            })
        }

        //video upload to cloudinary
        try{

            videolink = await uploadFileToCloudinary(videofile, "himanshu");
            console.log("Video Response :" ,videolink);            

        }
        catch(err){
            console.log(err);
            return res.status(400).json({
                success:false,
                message:'Something went wrong in uploading video file'
            })
        }


        // LOCAL SERVER UPLOAD FOR IMAGE
        try{

            //create path where need to be stored on server
            let path = __dirname + "/imageFiles/"+ Date.now() + `.${imagefile.name.split('.')[1]}`// + extension
            // console.log("PATH", path);

            //add path to the move function
            imagefile.mv(path, (err)=>{
                console.log(err);   
            });
        }
        catch(err){
            console.log(err);
        }


        // LOCAL SERVER UPLOAD FOR VIDEO
        try{

            //create path where need to be stored on server
            let path = __dirname + "/videoFiles/"+ Date.now() + `.${videofile.name.split('.')[1]}`// + extension
            // console.log("PATH", path);

            //add path to the move function
            videofile.mv(path, (err)=>{
                console.log(err);   
            });
        }
        catch(err){
            console.log(err);
        }

        //make FINAL entry in database
        const fileData = await Blog.create({
            title,snippet, body,
            imageUrl:imagelink.secure_url,
            videoUrl:videolink.secure_url,
        })
        .then((result)=>{
            res.redirect('/blogs');
        })
        .catch((err)=>{
            console.log(err);
        })

})

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

 