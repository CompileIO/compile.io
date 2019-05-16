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

Start the backend
From Compile.io (top level directory)
* Go into the directory `Spring_Server/gs-uploading-files-complete/`
* Open a bash terminal and run the command `./gradlew bootRun -D spring.profiles.active=dev`

Start the frontend
From Compile.io (top level directory)
* go into the directory `Frontend/compile-io-frontend-app/`
* Open a bash terminal and run the command `npm start`

Finally open a web browser to `http://localhost:4200/`

Installation Guides

NPM
* `https://www.npmjs.com/get-npm`
* Follow the website's instructions in order to download and install

Angular CLI
* Open a bash console and use the command `npm install -g @angular/cli`

Docker
* `https://hub.docker.com/editions/community/docker-ce-desktop-windows`
* Follow the website's instructions in order to download and install

Mongo
* `https://www.mongodb.com/download-center/community`
* Follow the website's instructions in order to download and install

Gradle
* `https://gradle.org/install`
* Follow the website's instructions in order to download and install or use a package manager

NPM Depeondencies
* The dependencies are all kept track of in the package.json file
* Navigate to the directory `compile.io/Frontend/compile-io-frontend-app`
* Open a bash console and use the command `npm install`

Common Issues
* If ng serve results in an error talking about missing a module or not finding something, reinstall NPM Dependencies
* If the website is up, but no information is displayed, the backend is likely not running
