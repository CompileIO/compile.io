To run code locally there are several programs that must be installed
* NPM
* Angular cli
* Docker
* Mongo
* Gradle
* NPM Dependencies
Installation guides for these programs are below

Once everything is installed, follow these instructions in order to run the project locally

Start Mongo
* Open a command prompt and use the command `mongo`
  * If this doesn't work, you may need to open a seperate command prompt and type the command `mongod` then reattempt the previous step

Start Docker
* Help mike, i need instructions here

Start the backend
From Compile.io (top level directory)
* Go into the directory `Spring_Server/gs-uploading-files-complete/`
* Open a bash terminal and run the command `./gradlew bootRun -D spring.profiles.active=dev`

Start the frontend
From Compile.io (top level directory)
* go into the directory `Frontend/compile-io-frontend-app/`
* Open a bash terminal and run the command `npm start`

Finally open a web browser to `http://localhost:4200/`

To run back-end tests locally:
* Navigate to the `compile.io` directory
* Navigate to `Spring_Server/gs-uploading-files-complete` within the `compile.io` directory
* Run `./gradlew test`
This will start the gradle building process, which runs the back-end tests.
If the build was successful, then the tests have passed. 
If the build fails, the offending test case will be displayed, along with the corresponding stack trace of the error/failure.
