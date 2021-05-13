import { Assignment } from './Assignment';
import { Exam } from './Exam';
import { Project } from './Project';

export class Subject {
  id?: number;
  name: string;
  description: string;
  scoring: string;
  officialCredit: number;
  experiencedCredit: number;
  subscribed: boolean;
  assignments: Assignment[];
  projects: Project[];
  exams: Exam[];
}
