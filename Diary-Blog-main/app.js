const express= require('express');
const app= express();
const mongoose=require('mongoose');
const Blog =require('./models/blog')


//third party middleware
const morgan=require('morgan');

//setting view engine as ejs
app.set('view engine', 'ejs');

const dbURI='mongodb://localhost:27017/blog'

app.listen(300);
//connecting database
mongoose.connect(dbURI, {useNewUrlParser:true , useUnifiedTopology:true})
.then((result)=> console.log('connected to db'))
.catch((err)=> console.log(err))


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
            res.redirect('/blogs')
        })
    app.get('/about',(req,res)=>{ 
        // res.send('<H1>Hello At ABOUT PAGE</H1>')   
        // res.sendFile('./views/about.html', {root:__dirname}) 
    res.render('about' , {title:'About'})
})

app.get('/blog/create', (req,res) =>{
    res.render('create',{title:'Create a new Blog'})
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
//saving data to the mongodb
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

 