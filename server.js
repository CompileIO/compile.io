//Initiallising node modules
var express = require("express");
var bodyParser = require("body-parser");
var app = express(); 
var http = require('http');
var path = require('path')
var formidable = require('formidable');
var fs = require('fs');
var multer = require("multer");


//var hostname = '0.0.0.0';
var hostname = 'localhost';
var port = 4000;

// Body Parser Middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
//app.use(bodyParser.urlencoded({ extended: false })); 
//app.use(bodyParser.json()); 

const cors = require('cors')
const IncomingForm = require('formidable').IncomingForm;

const corsOptions = {
    origin: "http://localhost:4200",
    credentials: true
}

app.use(cors(corsOptions));



 //GET API
app.get("/get_request", function (req, res) {
    console.log("GET");
    res.send([{ className: "CSSE132" }, {className: "CSSE120" }, {className: "CSSE220"}]);
  });

  //POST API
  app.post("/upload", multer({dest: "./uploads/"}).array("uploads[]",12), function(req, res) {
    res.send(req.files);
    console.log(req.files);
});

// app.post("/upload", function (req, res) {
//     console.log("in post request!");
//     console.log(req.body);
//     // var formData = new FormData();
//     var form = new formidable.IncomingForm();
//     console.log(form);
//     // form.parse(req, function (err, fields, files) {
//     //   var oldpath = files.filetoupload.path;
//     //   var newpath = 'C:/Users/palamujg/' + files.filetoupload.name;
//     //   fs.rename(oldpath, newpath, function (err) {
//     //     if (err) throw err;
//     //     res.write('File uploaded and moved!');
//     //     res.end();
//     //   });
//  });
//     //var form = new IncomingForm();
//     //form.on('file', (field, file) => {
//     //    //do stuff with the file
//     //    //file.path to access
//     //    console.log("HERE TOO!");
//     //});
//     //form.on('end', () => {
//     //    res.json();
//     //});
//     //form.parse(req);
// //   });

    

  //PUT API
app.put("put_request", function(req , res){

  });

    //PUT API
app.delete("delete_request", function(req , res){
    
});


//Setting up server
var server = app.listen(process.env.PORT || port, hostname, function () {
    // var port = server.address().port;
    console.log("App now running on port", port);
    console.log(`Server running at http://${hostname}:${port}/`);
});





//CORS Middleware
//app.use(function (req, res, next) {
//    //Enabling CORS 
//    console.log("HELLO!");
//    res.header("Access-Control-Allow-Origin", "*");
//    res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
//    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, contentType,Content-Type, Accept, Authorization");
//    next();
//});


//app.options('*', function (req, res) {
//    res.header("Access-Control-Allow-Origin", "*");
//    res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
//    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, contentType,Content-Type, Accept, Authorization");
//});

