## Assignments

### Get all assignments [GET]  [/Assignment]

Get all of the assignments in the database

+ Request (application/json)

    + Parameters

        + none

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de5a10cab086a0b7ab03"),
                "assignmentName" : "Homework 1",
                "timeout" : 500,
                "size" : 500,
                "tries" : 20,
                "startDate" : ISODate("2019-05-07T00:00:00Z"),
                "startTime" : ISODate("2019-05-08T15:11:00Z"),
                "endDate" : ISODate("2019-05-25T00:00:00Z"),
                "endTime" : ISODate("2019-05-08T15:11:00Z"),
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/professor-files/palamujg",
                "fileName" : "SimpleTest.java",
                "createdByUsername" : "palamujg",
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "availableToOtherSections" : false,
                "_class" : "compile_io.mongo.models.Assignment"
            }
        }

### Get one assignment [GET]  [/Assignment/{id}]

Get one of the assignments in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Assignment

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de5a10cab086a0b7ab03"),
                "assignmentName" : "Homework 1",
                "timeout" : 500,
                "size" : 500,
                "tries" : 20,
                "startDate" : ISODate("2019-05-07T00:00:00Z"),
                "startTime" : ISODate("2019-05-08T15:11:00Z"),
                "endDate" : ISODate("2019-05-25T00:00:00Z"),
                "endTime" : ISODate("2019-05-08T15:11:00Z"),
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/professor-files/palamujg",
                "fileName" : "SimpleTest.java",
                "createdByUsername" : "palamujg",
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "availableToOtherSections" : false,
                "_class" : "compile_io.mongo.models.Assignment"
            }
        }

### Upload an assignment file to the database [POST]  [/Assignmnet/uploadFile]

Upload an assignment test file for a specific assignment to the server
The body requires a multipart file to run, and returns the updated assignment that you are submitting the file to

+ Request (application/json)

    + Parameters

        + none

    + Body

            {
                "file" : Multipart file,
                "assignmentId" : " ObjectId("5cd2de5a10cab086a0b7ab03")"
            }


+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de5a10cab086a0b7ab03"),
                "assignmentName" : "Homework 1",
                "timeout" : 500,
                "size" : 500,
                "tries" : 20,
                "startDate" : ISODate("2019-05-07T00:00:00Z"),
                "startTime" : ISODate("2019-05-08T15:11:00Z"),
                "endDate" : ISODate("2019-05-25T00:00:00Z"),
                "endTime" : ISODate("2019-05-08T15:11:00Z"),
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/professor-files/palamujg",
                "fileName" : "SimpleTest.java",
                "createdByUsername" : "palamujg",
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "availableToOtherSections" : false,
                "_class" : "compile_io.mongo.models.Assignment"
            }
        }

### Serve an assignment file to the user [POST]  [/Assignment/getFile/{filename:.+}]

This is a post request that acts like a get request
This serves the file to the user 

+ Request (application/json)

    + Parameters

        + filename (string, required)

    + Body

            {
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/professor-files/palamujg"  (path to the file in the server)
            }


+ Response 200 (application/json)

        {
            the file in the server from the filepath given
        }

### Create an assignment [POST]  [/Assignment/Create]

Create an assignment in the database

+ Request (application/json)

    + Parameters

        + none

    + Body

            {
                "_id" : ObjectId("5cd2de5a10cab086a0b7ab03"),
                "assignmentName" : "Homework 1",
                "timeout" : 500,
                "size" : 500,
                "tries" : 20,
                "startDate" : ISODate("2019-05-07T00:00:00Z"),
                "startTime" : ISODate("2019-05-08T15:11:00Z"),
                "endDate" : ISODate("2019-05-25T00:00:00Z"),
                "endTime" : ISODate("2019-05-08T15:11:00Z"),
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/professor-files/palamujg",
                "fileName" : "SimpleTest.java",
                "createdByUsername" : "palamujg",
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "availableToOtherSections" : false,
                "_class" : "compile_io.mongo.models.Assignment"
            }


+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de5a10cab086a0b7ab03"),
                "assignmentName" : "Homework 1",
                "timeout" : 500,
                "size" : 500,
                "tries" : 20,
                "startDate" : ISODate("2019-05-07T00:00:00Z"),
                "startTime" : ISODate("2019-05-08T15:11:00Z"),
                "endDate" : ISODate("2019-05-25T00:00:00Z"),
                "endTime" : ISODate("2019-05-08T15:11:00Z"),
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/professor-files/palamujg",
                "fileName" : "SimpleTest.java",
                "createdByUsername" : "palamujg",
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "availableToOtherSections" : false,
                "_class" : "compile_io.mongo.models.Assignment"
            }
        }


### Update a Assignment [PUT] [/Assignment/Update/{id}]

Updates a new Assignment with the provided data

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Assignment

    + Body

            {
                "_id" : ObjectId("5cd2de5a10cab086a0b7ab03"),
                "assignmentName" : "Homework 1",
                "timeout" : 500,
                "size" : 500,
                "tries" : 20,
                "startDate" : ISODate("2019-05-07T00:00:00Z"),
                "startTime" : ISODate("2019-05-08T15:11:00Z"),
                "endDate" : ISODate("2019-05-25T00:00:00Z"),
                "endTime" : ISODate("2019-05-08T15:11:00Z"),
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/professor-files/palamujg",
                "fileName" : "SimpleTest.java",
                "createdByUsername" : "palamujg",
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "availableToOtherSections" : false,
                "_class" : "compile_io.mongo.models.Assignment"
            }


+ Response 200 (application/json)
    
        {
            {
                "_id" : ObjectId("5cd2de5a10cab086a0b7ab03"),
                "assignmentName" : "Homework 1",
                "timeout" : 500,
                "size" : 500,
                "tries" : 20,
                "startDate" : ISODate("2019-05-07T00:00:00Z"),
                "startTime" : ISODate("2019-05-08T15:11:00Z"),
                "endDate" : ISODate("2019-05-25T00:00:00Z"),
                "endTime" : ISODate("2019-05-08T15:11:00Z"),
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/professor-files/palamujg",
                "fileName" : "SimpleTest.java",
                "createdByUsername" : "palamujg",
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "availableToOtherSections" : false,
                "_class" : "compile_io.mongo.models.Assignment"
            }
        }

### Delete an Assignment [DELETE] [/Assignment/Delete/{id}]

Delete an assignment from the system

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Assignment


+ Response 200 (application/json)
    
        {
            data: `Deleted a Assignment`
        }

## Code
### Get all Codes [GET]  [/Code]

Get all of the codes in the database

+ Request (application/json)

    + Parameters

        + none

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }
        }
### Get all Codes for a specific assignment for a specific student [GET]  [/Code/getAssignment/{assignmentId}/{studentUsername}]

Get all Codes for a specific assignment for a specific student

+ Request (application/json)

    + Parameters

        + assignmentId (string, required) - The id of a particular Assignment
        + studentUsername (string, required) - The username of a particular Student

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }
        }
### Get all Codes for a specific student [GET]  [/Code/getStudent/{studentUsername}]

Get all Codes for a specific student

+ Request (application/json)

    + Parameters

        + studentUsername (string, required) - The username of a particular Student

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }
        }
### Get a Code [GET]  [/Code/{id}]

Get all Codes for a specific student

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Code

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }
        }

### run a Code [Post]  [/Code/{id}]

Runs the file given on the specifc assignment that the code id provided is linked to

+ Request (application/json)

    + Parameters

        + none
    + Body

            {
                "codeId" : ObjectId("5cd2de5a10cab086a0b7ab03"),
                "file" : Multipart file
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }
        }

### Serve a code file to the user [POST]  [/Code/getFile/{filename:.+}]

This is a post request that acts like a get request
This serves the file to the user 

+ Request (application/json)

    + Parameters

        + filename (string, required)

    + Body

            {
                "filepath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg"  (path to the file in the server)
            }


+ Response 200 (application/json)

        {
            the file in the server from the filepath given
        }
### Create a code [POST]  [/Code/Create]

Create a code in the database

+ Request (application/json)

    + Parameters

        + none
     + Body

            {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }
        }
### Update a code [PUT]  [/Code/Update/{id}]

Update a code in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Code

     + Body

            {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd309bc86baf57d408b6173"),
                "runTime" : 500,
                "submissionAttempts" : 1,
                "testResponses" : [ ],
                "unitResponses" : [
                [
                        "\nSimpleTest > testAdd PASSED\n",
                        "\nSimpleTest > testSub PASSED\n",
                        "\nSimpleTest > testFalse PASSED\n",
                        "\nSimpleTest > testTrue PASSED\n"
                ]
                ],
                "codePath" : "upload-dir/2020/1/csse120/2/homework_1/student-files/palamujg",
                "assignmentId" : "5cd2de5a10cab086a0b7ab03",
                "userName" : "palamujg",
                "submissionTime" : ISODate("2019-05-08T16:54:49.943Z"),
                "grade" : "4/4",
                "fileName" : "Simple.java",
                "_class" : "compile_io.mongo.models.Code"
            }
        }

### Delete a code [DELETE]  [/Code/Delete/{id}]

Delete a code in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Code

+ Response 200 (application/json)
    
        {
            data: `Deleted a Code`
        }
## Course
### Get all Courses [GET]  [/Courses]

Get all of the courses in the database

+ Request (application/json)

    + Parameters

        + none

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de0a10cab086a0b7aafe"),
                "courseName" : "CSSE120",
                "professorUsernames" : [
                        "footezo",
                        "palamujg"
                ],
                "sections" : [
                        DBRef("Section", ObjectId("5cd2de3110cab086a0b7ab00"))
                ],
                "description" : "General CSSE 120 Description",
                "_class" : "compile_io.mongo.models.Course"
            }
        }
### Get a Course [GET]  [/Course/{id}]

Get a particular course in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Course

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de0a10cab086a0b7aafe"),
                "courseName" : "CSSE120",
                "professorUsernames" : [
                        "footezo",
                        "palamujg"
                ],
                "sections" : [
                        DBRef("Section", ObjectId("5cd2de3110cab086a0b7ab00"))
                ],
                "description" : "General CSSE 120 Description",
                "_class" : "compile_io.mongo.models.Course"
            }
        }
### Create a course [POST]  [/Course/Create]

Create a course in the database

+ Request (application/json)

    + Parameters

        + none

     + Body

            {
                "_id" : ObjectId("5cd2de0a10cab086a0b7aafe"),
                "courseName" : "CSSE120",
                "professorUsernames" : [
                        "footezo",
                        "palamujg"
                ],
                "sections" : [
                        DBRef("Section", ObjectId("5cd2de3110cab086a0b7ab00"))
                ],
                "description" : "General CSSE 120 Description",
                "_class" : "compile_io.mongo.models.Course"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de0a10cab086a0b7aafe"),
                "courseName" : "CSSE120",
                "professorUsernames" : [
                        "footezo",
                        "palamujg"
                ],
                "sections" : [
                        DBRef("Section", ObjectId("5cd2de3110cab086a0b7ab00"))
                ],
                "description" : "General CSSE 120 Description",
                "_class" : "compile_io.mongo.models.Course"
            }
        }
### Update a course [PUT]  [/Course/Update/{id}]

Update a course in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Course

     + Body

            {
                "_id" : ObjectId("5cd2de0a10cab086a0b7aafe"),
                "courseName" : "CSSE120",
                "professorUsernames" : [
                        "footezo",
                        "palamujg"
                ],
                "sections" : [
                        DBRef("Section", ObjectId("5cd2de3110cab086a0b7ab00"))
                ],
                "description" : "General CSSE 120 Description",
                "_class" : "compile_io.mongo.models.Course"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de0a10cab086a0b7aafe"),
                "courseName" : "CSSE120",
                "professorUsernames" : [
                        "footezo",
                        "palamujg"
                ],
                "sections" : [
                        DBRef("Section", ObjectId("5cd2de3110cab086a0b7ab00"))
                ],
                "description" : "General CSSE 120 Description",
                "_class" : "compile_io.mongo.models.Course"
            }
        }

### Delete a course [DELETE]  [/Course/Delete/{id}]

Delete a course in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Course

+ Response 200 (application/json)
    
        {
            data: `Deleted a course`
        }
## Professor
### Get all Professors [GET]  [/Professors]

Get all of the Professors in the database

+ Request (application/json)

    + Parameters

        + none

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2ddf210cab086a0b7aafd"),
                "name" : "Joshua Palamuttam",
                "userName" : "palamujg",
                "courses" : [
                        DBRef("Course", ObjectId("5cd2de0a10cab086a0b7aafe"))
                ],
                "_class" : "compile_io.mongo.models.Professor"
            }
        }
### Get a specific Professor [GET]  [/Professor/{id}]

Get a particular professor in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Assignment

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2ddf210cab086a0b7aafd"),
                "name" : "Joshua Palamuttam",
                "userName" : "palamujg",
                "courses" : [
                        DBRef("Course", ObjectId("5cd2de0a10cab086a0b7aafe"))
                ],
                "_class" : "compile_io.mongo.models.Professor"
            }
        }
### Get a specific Professor with username [GET]  [/Professor/Username/{username}]

Get a particular professor in the database through thier username

+ Request (application/json)

    + Parameters

        + username (string, required) - The username of a particular Professor

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2ddf210cab086a0b7aafd"),
                "name" : "Joshua Palamuttam",
                "userName" : "palamujg",
                "courses" : [
                        DBRef("Course", ObjectId("5cd2de0a10cab086a0b7aafe"))
                ],
                "_class" : "compile_io.mongo.models.Professor"
            }
        }
### Create a Professor [POST]  [/Professor/Create]

Create a Professor in the database

+ Request (application/json)

    + Parameters

        + none

     + Body

            {
                "_id" : ObjectId("5cd2ddf210cab086a0b7aafd"),
                "name" : "Joshua Palamuttam",
                "userName" : "palamujg",
                "courses" : [
                        DBRef("Course", ObjectId("5cd2de0a10cab086a0b7aafe"))
                ],
                "_class" : "compile_io.mongo.models.Professor"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2ddf210cab086a0b7aafd"),
                "name" : "Joshua Palamuttam",
                "userName" : "palamujg",
                "courses" : [
                        DBRef("Course", ObjectId("5cd2de0a10cab086a0b7aafe"))
                ],
                "_class" : "compile_io.mongo.models.Professor"
            }
        }
### Update a Professor [PUT]  [/Professor/Update/{id}]

Update a Professor in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Professor

     + Body

            {
                "_id" : ObjectId("5cd2ddf210cab086a0b7aafd"),
                "name" : "Joshua Palamuttam",
                "userName" : "palamujg",
                "courses" : [
                        DBRef("Course", ObjectId("5cd2de0a10cab086a0b7aafe"))
                ],
                "_class" : "compile_io.mongo.models.Professor"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2ddf210cab086a0b7aafd"),
                "name" : "Joshua Palamuttam",
                "userName" : "palamujg",
                "courses" : [
                        DBRef("Course", ObjectId("5cd2de0a10cab086a0b7aafe"))
                ],
                "_class" : "compile_io.mongo.models.Professor"
            }
        }

### Delete a Professor [DELETE]  [/Professor/Delete/{id}]

Delete a professor in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Professor

+ Response 200 (application/json)
    
        {
            data: `Deleted a professor`
        }
## Section
### Get all Sections [GET]  [/Sections]

Get all of the Sections in the database

+ Request (application/json)

    + Parameters

        + none

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab00"),
                "year" : 2020,
                "term" : 1,
                "sectionNumber" : 2,
                "studentUsernames" : [
                        "footezo",
                        "rileyma",
                        "palamujg"
                ],
                "useSectionDescription" : false,
                "description" : "CSSE 120 section 2 description",
                "SectionId" : "5cd2de0a10cab086a0b7aafe",
                "assignments" : [
                        DBRef("Assignment", ObjectId("5cd2de5a10cab086a0b7ab03"))
                ],
                "_class" : "compile_io.mongo.models.Section"
            }
        }
### Get a Section [GET]  [/Section/{id}]

Get a particular Section in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Section

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab00"),
                "year" : 2020,
                "term" : 1,
                "sectionNumber" : 2,
                "studentUsernames" : [
                        "footezo",
                        "rileyma",
                        "palamujg"
                ],
                "useSectionDescription" : false,
                "description" : "CSSE 120 section 2 description",
                "SectionId" : "5cd2de0a10cab086a0b7aafe",
                "assignments" : [
                        DBRef("Assignment", ObjectId("5cd2de5a10cab086a0b7ab03"))
                ],
                "_class" : "compile_io.mongo.models.Section"
            }
        }
### Create a Section [POST]  [/Section/Create]

Create a Section in the database

+ Request (application/json)

    + Parameters

        + none

     + Body

            {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab00"),
                "year" : 2020,
                "term" : 1,
                "sectionNumber" : 2,
                "studentUsernames" : [
                        "footezo",
                        "rileyma",
                        "palamujg"
                ],
                "useSectionDescription" : false,
                "description" : "CSSE 120 section 2 description",
                "SectionId" : "5cd2de0a10cab086a0b7aafe",
                "assignments" : [
                        DBRef("Assignment", ObjectId("5cd2de5a10cab086a0b7ab03"))
                ],
                "_class" : "compile_io.mongo.models.Section"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab00"),
                "year" : 2020,
                "term" : 1,
                "sectionNumber" : 2,
                "studentUsernames" : [
                        "footezo",
                        "rileyma",
                        "palamujg"
                ],
                "useSectionDescription" : false,
                "description" : "CSSE 120 section 2 description",
                "SectionId" : "5cd2de0a10cab086a0b7aafe",
                "assignments" : [
                        DBRef("Assignment", ObjectId("5cd2de5a10cab086a0b7ab03"))
                ],
                "_class" : "compile_io.mongo.models.Section"
            }
        }
### Update a Section [PUT]  [/Section/Update/{id}]

Update a Section in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Section

     + Body

            {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab00"),
                "year" : 2020,
                "term" : 1,
                "sectionNumber" : 2,
                "studentUsernames" : [
                        "footezo",
                        "rileyma",
                        "palamujg"
                ],
                "useSectionDescription" : false,
                "description" : "CSSE 120 section 2 description",
                "SectionId" : "5cd2de0a10cab086a0b7aafe",
                "assignments" : [
                        DBRef("Assignment", ObjectId("5cd2de5a10cab086a0b7ab03"))
                ],
                "_class" : "compile_io.mongo.models.Section"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab00"),
                "year" : 2020,
                "term" : 1,
                "sectionNumber" : 2,
                "studentUsernames" : [
                        "footezo",
                        "rileyma",
                        "palamujg"
                ],
                "useSectionDescription" : false,
                "description" : "CSSE 120 section 2 description",
                "SectionId" : "5cd2de0a10cab086a0b7aafe",
                "assignments" : [
                        DBRef("Assignment", ObjectId("5cd2de5a10cab086a0b7ab03"))
                ],
                "_class" : "compile_io.mongo.models.Section"
            }
        }

### Delete a Section [DELETE]  [/Section/Delete/{id}]

Delete a Section in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Section

+ Response 200 (application/json)
    
        {
            data: `Deleted a section`
        }
        
## Student
### Get all Students [GET]  [/Students]

Get all of the Students in the database

+ Request (application/json)

    + Parameters

        + none

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab01"),
                "codes" : [
                        DBRef("Code", ObjectId("5cd2f03582d6332d4c6d4714"))
                ],
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "name" : "Zachary Foote",
                "userName" : "footezo",
                "_class" : "compile_io.mongo.models.Student"
            }
        }
### Get a specific Student [GET]  [/Student/{id}]

Get a particular Student in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Assignment

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab01"),
                "codes" : [
                        DBRef("Code", ObjectId("5cd2f03582d6332d4c6d4714"))
                ],
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "name" : "Zachary Foote",
                "userName" : "footezo",
                "_class" : "compile_io.mongo.models.Student"
            }
        }
### Get a specific Student with username [GET]  [/Student/Username/{username}]

Get a particular Student in the database through thier username

+ Request (application/json)

    + Parameters

        + username (string, required) - The username of a particular Student

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab01"),
                "codes" : [
                        DBRef("Code", ObjectId("5cd2f03582d6332d4c6d4714"))
                ],
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "name" : "Zachary Foote",
                "userName" : "footezo",
                "_class" : "compile_io.mongo.models.Student"
            }
        }
### Create a Student [POST]  [/Student/Create]

Create a Student in the database

+ Request (application/json)

    + Parameters

        + none

     + Body

            {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab01"),
                "codes" : [
                        DBRef("Code", ObjectId("5cd2f03582d6332d4c6d4714"))
                ],
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "name" : "Zachary Foote",
                "userName" : "footezo",
                "_class" : "compile_io.mongo.models.Student"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab01"),
                "codes" : [
                        DBRef("Code", ObjectId("5cd2f03582d6332d4c6d4714"))
                ],
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "name" : "Zachary Foote",
                "userName" : "footezo",
                "_class" : "compile_io.mongo.models.Student"
            }
        }
### Update a Student [PUT]  [/Student/Update/{id}]

Update a Student in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Student

     + Body

            {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab01"),
                "codes" : [
                        DBRef("Code", ObjectId("5cd2f03582d6332d4c6d4714"))
                ],
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "name" : "Zachary Foote",
                "userName" : "footezo",
                "_class" : "compile_io.mongo.models.Student"
            }

+ Response 200 (application/json)

        {
            "data": {
                "_id" : ObjectId("5cd2de3110cab086a0b7ab01"),
                "codes" : [
                        DBRef("Code", ObjectId("5cd2f03582d6332d4c6d4714"))
                ],
                "sectionIds" : [
                        "5cd2de3110cab086a0b7ab00"
                ],
                "name" : "Zachary Foote",
                "userName" : "footezo",
                "_class" : "compile_io.mongo.models.Student"
            }
        }

### Delete a Student [DELETE]  [/Student/Delete/{id}]

Delete a Student in the database

+ Request (application/json)

    + Parameters

        + id (string, required) - The id of a particular Student

+ Response 200 (application/json)
    
        {
            data: `Deleted a student`
        }