import { Time } from "@angular/common";

export class Assignment {
    id: string;
    assignmentName: string;
    timeout: number;
    language: string;
    size: number;
    tries: number;
    startDate: Date;
    startTime: Time;
    endDate: Date;
    endTime: Time;
    // file: File;
    courseName: string;

    display() {
        console.log("Inside the create Assignment Method: " + 
    "ID: " + this.id + "\n" +
    "Course Name: " + this.courseName + "\n" +
    "Assignment Name: " + this.assignmentName + "\n" +
    "Timeout: " + this.timeout + "\n" +
    "language: " + this.language + "\n" +
    "Size: " + this.size + "\n" +
    "tries: " + this.tries + "\n" +
    "start date: " + this.startDate + "\n" +
    "start time: " + this.startTime + "\n" +
    "end date: " + this.endDate + "\n" +
    "end time: " + this.endTime);
    }
    
}
