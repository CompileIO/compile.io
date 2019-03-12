import { Assignment } from './assignment';
import { Course } from './course';

export class Professor {
    id: string;
    name: string;
    userName: string;
    courses: Course[];
    assignments: Assignment[] ;

}