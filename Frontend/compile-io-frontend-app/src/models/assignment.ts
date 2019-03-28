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
    filepath: string;
    courseName: string;
    createdByUsername: string;
    sectionIds: string[];
    availableToOtherSections: boolean ;
    
}
