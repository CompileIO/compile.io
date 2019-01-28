#!/bin/bash
set -x # Show the output of the following commands (useful for debugging)
    
echo "Importing the SSH deployment key"
openssl aes-256-cbc -K $encrypted_6905c7cecc50_key -iv $encrypted_6905c7cecc50_iv -in deploy-key.enc -out deploy-key -d
rm deploy-key.enc # Don't need it anymore
chmod 600 deploy-key
mv deploy-key ~/.ssh/id_rsa

echo "Installing npm dependencies"
npm i -g npm@latest #npm install all the dependencies needed
npm install


echo "Initializing a new git repo in _site, and pushing it to our server"
    mkdir _site
    cd _site
    git init
    git remote add deploy "git@compile-io.csse.rose-hulman.edu:~/srv/git/project.git"
    git config user.name "Travis CI"
    git config user.email "joshpal97@gmail.com"

    echo "Starting Frontend"
    cd Frontend/compile-io-frontend-app
    ng build --prod
    ng serve --prod --host 0.0.0.0
    echo "The site should be up and running at compile-io.csse.rose-hulman.edu"