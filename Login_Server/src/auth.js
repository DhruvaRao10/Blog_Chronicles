const passport = require('passport') ; 
const GoogleStrategy = require('passport-google-oauth2').Strategy ; 
require('dotenv').config() ;


const GOOGLE_CLIENT_ID = process.env.GOOGLE_CLIENT_ID;
const GOOGLE_CLIENT_SECRET = process.env.GOOGLE_CLIENT_SECRET;

passport.use(new GoogleStrategy({
    clientID: process.env.GOOGLE_CLIENT_ID,
    clientSecret: process.env.GOOGLE_CLIENT_SECRET,   
    callbackURL: "http://localhost:5000/auth/google/callback",
    passReqtoCallback: true 
  },

  function(request , accessToken, refreshToken, profile, done) {
     console.log(profile) ; 
     done(null,profile) ; 
  }
));

passport.serializeUser((user,done)=>{
     done(null,user) ;   
});

passport.deserializeUser((user,done)=>{
   done(null,user) ;   
}); 