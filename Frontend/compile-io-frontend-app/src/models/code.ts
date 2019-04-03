import { Assignment } from "./assignment";
import { Time } from "@angular/common";

export class Code {
    id: string;
    language: string;
    runTime: number;
    submissionAttempts: number;
    testResponses: string [];
    codePath: string;
    fileName: string;
    assignmentId: string;
    userName: string ; 
    submisstionTime:Time;
    grade: string;


    
    
}