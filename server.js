//Initiallising node modules
var express = require("express");
var bodyParser = require("body-parser");
var app = express(); 


//var hostname = '0.0.0.0';
var hostname = 'localhost';
var port = 4000;

// Body Parser Middleware
app.use(bodyParser.urlencoded({ extended: false })); 
app.use(bodyParser.json()); 

const cors = require('cors')
const IncomingForm = require('formidable').IncomingForm;
//const upload = require('./upload');

var corsOptions = {
  origin: 'http://localhost:4200/',
  optionsSuccessStatus: 200, // some legacy browsers (IE11, various SmartTVs) choke on 204
}



//Setting up server
 var server = app.listen(process.env.PORT || port, hostname, function () {
    // var port = server.address().port;
    console.log("App now running on port", port);
    console.log(`Server running at http://${hostname}:${port}/`);
 });


app.use(cors(corsOptions));

//CORS Middleware
app.use(function (req, res, next) {
    //Enabling CORS 
    cors(corsOptions);
    console.log("HELLO!");
    req.header("Access-Control-Allow-Origin", "*");
    req.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
    req.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, contentType,Content-Type, Accept, Authorization");
    next();
});


 //GET API
app.get("/get_request", function(req , res){
  });

  //POST API
app.post("/", function (req, res, next) {
    //req.header("Access-Control-Allow-Origin", '*');
    var form = new IncomingForm();
    console.log("WOW!");
    form.on('file', (field, file) => {
        //do stuff with the file
        //file.path to access
    });
    form.on('end', () => {
        res.json();
    });
    form.parse(req);
  });

  //PUT API
app.put("put_request", function(req , res){

  });

    //PUT API
app.delete("delete_request", function(req , res){
    
});