import { Professor } from './professor';
import { Student } from './student';
export class Course {
    id: string;
    courseName: string;
	crn: string;
	
    sectionNumber: number;
	instructor: Professor;
	students: Student[];
    
}