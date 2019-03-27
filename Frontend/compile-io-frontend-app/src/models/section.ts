import { Student } from './student';
import { Assignment } from './assignment';
export class Section {
    id: string;
	Year: number; //2019
	Term: number; //1
	sectionNumber: number;
	students: Student[];
	useClassDescription: boolean ;
	description: string ;
	assignments: Assignment[];
}
