**DISCLAIMER**: *As changes are made, this README may become out-of-date. Please update or remove this file as the project changes.*
# Up-to-Date as of May 15, 2019
## Current Features
- Can run JUnit tests on single Java files.
- Can run Python files.

## For issue [#152](https://github.com/CompileIO/compile.io/issues/152) (Java Multifile)
### What's Done
- Dockerfile construction for Java multifile.
- Able to retrieve multifile builder from factory class.
### Known Issues
- Docker build process, container execution, and container teardown runs too long, ~1 hour.
- Docker container construction needs to be optimized. Perhaps look into building an image everytime the code is run; Make it so image construction happens once, and then containers are made from that image for a student submisison.
#### *Please look at the* [commit logs](https://github.com/CompileIO/compile.io/commits/152-multifile-submission) *as well as the* [issue itself](https://github.com/CompileIO/compile.io/issues/152) *for more information (like TODOs/Acceptance Criteria)*
## To Run This Code
- Make sure that you have the latest version of **Docker** installed. Click [here](https://www.docker.com/) and install Docker Desktop for your OS for a Desktop friendly Docker app.
- Docker can also be installed using a package manager, if you have a package manager on your computer.
- For Windows, you need to enable Vitrualization in your BIOS. Once that's enabled, have Hyper-V enabled if you are running Windows. This can be done from the Control Panel. A restart will be required to turn on Hyper-V.
- *This code can be run locally, independent of the Frontend*. Run the `main` method in `Main.java`. This file reflects how the Docker code is called on the backend.
- Check the [Documentation](https://github.com/CompileIO/compile.io/tree/develop/Documentation) folder in the repository for more information on how to run the code locally.
