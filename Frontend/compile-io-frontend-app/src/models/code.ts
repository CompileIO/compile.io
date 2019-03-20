import { Assignment } from "./assignment";
import { Time } from "@angular/common";

export class Code {
    id: string;
    language: string;
    runTime: number;
    submissionAttempts: number;
    testResponses: string [];
    codePath: string;
    Assignment: Assignment; // supposed to be private String assignmentId;
    userName: string ; //currently don't use this
    submisstionTime:Time;
    grade: string;


    
    
}