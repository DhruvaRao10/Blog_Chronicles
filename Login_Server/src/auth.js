const passport = require('passport') ; 
const GoogleStrategy = require('passport-google-oauth2').Strategy ; 
// require('dotenv').config() ;


const GOOGLE_CLIENT_ID =       "638460345107-0jbluuvkpkddth2tlu1sgtq2kkr7ftba.apps.googleusercontent.com"  ;    //process.env.GOOGLE_CLIENT_ID;
const GOOGLE_CLIENT_SECRET =   "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"; // process.env.GOOGLE_CLIENT_SECRET;

/*passport.use(new GoogleStrategy({
    clientID: process.env.GOOGLE_CLIENT_ID,
    clientSecret: process.env.GOOGLE_CLIENT_SECRET,   
    callbackURL: "http://localhost:5000/auth/google/callback",
    passReqtoCallback: true 
  },

  function(request , accessToken, refreshToken, profile, done) {
     console.log(profile) ; 
     done(null,profile) ; 
  }
));*/

passport.use(new GoogleStrategy({
   clientID: GOOGLE_CLIENT_ID,
   clientSecret: GOOGLE_CLIENT_SECRET,   
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
