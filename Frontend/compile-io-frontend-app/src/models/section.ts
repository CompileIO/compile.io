import { Assignment } from './assignment';
import { Course } from './course';

export class Section {
  id: string;
  number: string;
  term: string;
  year: string;
  course: Course;
  assignments: Assignment[];
}
