//Initiallising node modules
var express = require("express");
var bodyParser = require("body-parser");


//Initializing Database config statement and server hostname
var hostname = '0.0.0.0';
// var hostname = "http://localhost:4000"
var port = 4000;

// Body Parser Middleware
app.use(bodyParser.urlencoded({ extended: false })); 
app.use(bodyParser.json()); 

//CORS Middleware
app.use(function (req, res, next) {
    //Enabling CORS 
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, contentType,Content-Type, Accept, Authorization");
    next();
});

//Setting up server
 var server = app.listen(process.env.PORT || port, hostname, function () {
    // var port = server.address().port;
    console.log("App now running on port", port);
    console.log(`Server running at http://${hostname}:${port}/`);
 });

 //GET API
app.get("/get_request", function(req , res){
  });

  //POST API
app.post("/post_request", function(req , res){

  });

  //PUT API
app.put("put_request", function(req , res){

  });

    //PUT API
app.delete("delete_request", function(req , res){
    
});