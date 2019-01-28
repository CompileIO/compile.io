#!/bin/bash
set -x
if [ $TRAVIS_BRANCH == 'master' ] ; then
    # echo "Initializing a new git repo in _site, and pushing it to our server"
    # mkdir _site
    # cd _site
    # git init
    # git remote add deploy "git@compile-io.csse.rose-hulman.edu:~/srv/git/project.git"
    # git config user.name "Travis CI"
    # git config user.email "joshpal97@gmail.com"
    
    echo "Making sure the repo has everything it needs"
    git add .
    git commit -m "Deploy"
    git push --force deploy master
    
    echo "Starting Backend"
    cd Spring_Server/gs-uploading-files-complete
    ./gradlew bootRun -D spring.profiles.active=prod
    echo "Starting Frontend"
    cd ../../Frontend/compile-io-frontend-app
    ng build --prod
    ng serve --prod --host 0.0.0.0
    echo "The site should be up and running at compile-io.csse.rose-hulman.edu"

#     Run Spring
# (in compile.io folder)
# sudo apt-get npm install
# sudo npm install

# might have to install java with 
# sudo apt install default-jre

# (Go inside Spring Server/gs..../)
# sudo chmod u+x gradlew
# sudo ./gradlew build
# sudo java -jar build/libs/gs-spring-boot-0.1.0.jar


# sudo forever start node_modules/@angular/cli/bin/ng serve --proxy-config proxy.conf.json --host 0.0.0.0
# sudo forever start /usr/bin/java -jar build/libs/gs-spring-boot-0.1.0.jar

else
    echo "Not deploying, since this branch isn't master."
fi