import { Assignment } from "./assignment";
import { Time } from "@angular/common";

export class Code {
    id: string;
    language: string;
    runTime: number;
    testResponses: string [];
    codePath: string;
    Assignment: Assignment;
    submisstionTime:Time;
    grade: string;
}