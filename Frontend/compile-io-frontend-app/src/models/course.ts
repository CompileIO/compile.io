import { Professor } from './professor';
import { Section } from './section';
export class Course {
    id: string;
    courseName: string;
	professors: string[];
    sections: Section[];
    description: string;
    
}
