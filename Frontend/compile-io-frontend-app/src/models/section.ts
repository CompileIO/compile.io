import { Student } from './student';
import { Assignment } from './assignment';
export class Section {
    id: string;
	year: number;
	term: number;
	sectionNumber: number;
	studentUsernames: string[];
	useCourseDescription: boolean;
	description: string ;
	assignments: Assignment[];
	courseId: string;
}
