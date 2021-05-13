import { Component, Input, OnInit } from '@angular/core';
import { Assignment } from '../../model/Assignment';

@Component({
  selector: 'app-assignment',
  templateUrl: './assignment.component.html',
  styleUrls: ['./assignment.component.css']
})
export class AssignmentComponent implements OnInit {

  @Input() assignment: Assignment
  @Input() basicInfo: boolean
  @Input() editable: boolean

  editing: boolean = false

  constructor() { }

  ngOnInit(): void {
  }

  formatDate(deadline: any): string {
    const zeroPad = (num, places) => String(num).padStart(places, '0')

    if (deadline instanceof Date){
      return `${deadline.getFullYear()}-${zeroPad(deadline.getMonth(),2)}-${zeroPad(deadline.getDay(),2)}`
    }
    return deadline
  }
}
