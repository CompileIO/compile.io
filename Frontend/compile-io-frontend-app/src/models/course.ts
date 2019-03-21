import { Professor } from './professor';
import { Section } from './section';
export class Course {
    id: string;
    courseName: string;
	  professors: Professor[];
    sections: Section[];
    description: string;
    
}
