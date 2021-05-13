import { Assignment } from './Assignment';
import { Exam } from './Exam';

export class Requirement {
  label: string;
  dueDate: Date;
  assignment?: Assignment;
  exam?: Exam;
}

export function assignmentToRequirement(assignment: Assignment): Requirement {
  return {
    label: assignment.label,
    dueDate: assignment.deadline,
    assignment: assignment,
  };
}

export function examToRequirement(exam: Exam): Requirement {
  return {
    label: exam.label,
    dueDate: exam.dueDate,
    exam: exam,
  };
}
