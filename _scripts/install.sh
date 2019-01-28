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
