Currently we have a Continuous integration process in the sense that travis runs our jasmine tests on the frontend, but not the tests that we have on the backend.

The continuous deployment was a little tricky. In our master VM there is a deployment key that is hooked up to travis, but currently we have no way to push the code through travis and into our server. There is a git hook created that will allow people with the correct deploy-key.enc file to push code to our server, but we never got it to actually push to our server. 

What is apparently happening is that all the commands are running on the travis runner and not on the VM like we want it to so the next steps would be to scp instructions.sh to the compile.io server, and then run the commands through our instructions.sh script to our server. 

So the commented scripts are the skeleton for what should work if we were to get the commands on the server, but currently the block is that when travis runs we aren't pushing any code to the server, and when we want to run commands on the server all the commands are actually running on the travis runner. 