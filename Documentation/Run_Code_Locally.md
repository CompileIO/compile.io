To run code locally there will be a couple files you will have to change from Release 1

From Compile.io (top level directory)
* go into the directory Spring_Server/gs-uploading-files-complete/src/main/java/compile_io
* inside this directory go into the file FileUploadController.java
* change the private static final String frontendVm variable to "http://localhost:4200"

Also from Compile.io (top level directory)
* go into the Frontend/index/src/app/upload/
* inside this directory go into the upload.service.ts file
* change the private apiUrl variable to 'http://localhost:8080';


Then to actually run the application
* open up a bash terminal in the directory Frontend/index 
* run the command npm start
* (this runs the frontend code)

* open up another bash terminal in the directory Spring_Server/gs-uploading-files-complete
* run the commands in order:
./gradlew build        (compiles the server code)
java -jar build/libs/gs-spring-boot-0.1.0.jar     (runs the server)

Have those terminals open, and go to http://localhost:4200 to run everything locally 

To run back-end tests locally:
* Navigate to the `compile.io` directory
* Navigate to `Spring_Server/gs-uploading-files-complete` within the `compile.io` directory
* Run `./gradlew test`
This will start the gradle building process, which runs the back-end tests.
If the build was successful, then the tests have passed. 
If the build fails, the offending test case will be displayed, along with the corresponding stack trace of the error/failure.
