import { Assignment } from './Assignment';
import { Exam } from './Exam';
import { Subject } from './Subject';

export class Requirement {
  label: string;
  dueDate: Date;
  assignment?: Assignment;
  exam?: Exam;
  subjectName: string;
}

export function assignmentToRequirement(assignment: Assignment, subject: Subject): Requirement {
  return {
    label: assignment.label,
    dueDate: assignment.deadline,
    assignment: assignment,
    subjectName: subject.name
  };
}

export function examToRequirement(exam: Exam, subject: Subject): Requirement {
  return {
    label: exam.label,
    dueDate: exam.dueDate,
    exam: exam,
    subjectName: subject.name
  };
}
