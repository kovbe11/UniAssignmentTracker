import { Assignment } from './Assignment';

export class Project {
  id?: number;
  specification: string;
  experiences: string;
  documentation: string;
  projectAssignments: Assignment[];
}
