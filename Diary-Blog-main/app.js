const express= require('express');
const app= express();
const mongoose=require('mongoose');
const Blog =require('./models/blog')
const cheerio = require('cheerio');

//third party middleware
const morgan=require('morgan');

//setting view engine as ejs
app.set('view engine', 'ejs');

const dbURI= "mongodb+srv://hv27102000:vNbuux2UI35W1d2s@cluster0.f9cdeng.mongodb.net/"

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
//saving data to the mongodb
function htmlToText(html) {
    const $ = cheerio.load(html);
    return $('body').text();
}
app.post('/blogs',(req,res)=>{
    console.log(req.body);
    req.body.body=htmlToText(req.body.body);
    const blog=new Blog(req.body) //creating a new instance of Blog schema and passing the submitted data in form of req.body
    blog.save() //saving it to the database
    .then((result)=>{
        res.redirect('/blogs');
    })
    .catch((err)=>{
        console.log(err);   
    })
})
    



app.post("/search",async(req,res)=>{
    try{                              
          
      console.log(" in search method ") ;
      console.log(req.body) ; 

      const midstringtitle = req.body.searchInput ;
      const midstringsnippet = req.body.searchInput ;  
      const midstringbody = req.body.searchInput ;

      console.log(" before 3 regex patterns");
      const regexPattern = new RegExp(`.${midstringtitle}.`,'i') ;   
      const regexPattern1 = new RegExp(`.${midstringsnippet}.`,'i') ;       
      const regexPattern2 = new RegExp(`.${midstringbody}.`,'i') ; 
                                                                                                       
      existingTitle = await Blog.find({ title : {$regex: regexPattern }}) ;
      existingSnippet = await Blog.find({snippet : {$regex: regexPattern1 }}) ; 
      existingBody = await Blog.find({ body :  {$regex: regexPattern2 }}) ;  

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

 