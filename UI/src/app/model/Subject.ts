import { Assignment } from './Assignment';
import { Exam } from './Exam';
import { Project } from './Project';

export class Subject {
  id?: number;
  name: string;
  description: string;
  experiences: string;
  scoring: string;
  officialCredit: number;
  experiencedCredit: number;
  isSubscribed: boolean;
  assignments: Assignment[];
  projects: Project[];
  exams: Exam[];
}