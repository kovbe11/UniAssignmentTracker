import { Component, Input, OnInit } from '@angular/core';
import { Assignment } from '../../model/Assignment';

@Component({
  selector: 'app-assignment',
  templateUrl: './assignment.component.html',
  styleUrls: ['./assignment.component.css']
})
export class AssignmentComponent implements OnInit {

  @Input() assignment: Assignment

  // used on one page subject display
  @Input() basicInfo: boolean

  constructor() { }

  ngOnInit(): void {
  }

  // js has a really bad way of handling dates, but it's still better than java
  // this function formats the date
  formatDate(deadline: any): string {
    const zeroPad = (num, places) => String(num).padStart(places, '0')

    // sometimes it doesn't create a date object out of the string, and it caused issues.
    if (deadline instanceof Date){
      return `${deadline.getFullYear()}-${zeroPad(deadline.getMonth(),2)}-${zeroPad(deadline.getDay(),2)}`
    }
    return deadline
  }
}
