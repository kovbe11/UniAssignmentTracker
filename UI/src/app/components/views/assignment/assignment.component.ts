import { Component, Input, OnInit } from '@angular/core';
import { Assignment } from '../../../model/Assignment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-assignment',
  templateUrl: './assignment.component.html',
  styleUrls: ['./assignment.component.css']
})
export class AssignmentComponent implements OnInit {

  @Input() assignment: Assignment
  @Input() basicInfo: boolean
  @Input() editable: boolean

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  formatDate(deadline: any): string {
    const zeroPad = (num, places) => String(num).padStart(places, '0')

    if (deadline instanceof Date){
      return `${deadline.getFullYear()}-${zeroPad(deadline.getMonth(),2)}-${zeroPad(deadline.getDay(),2)}`
    }
    return deadline
  }

  edit() {
    if (!this.editable) {
      return;
    }
    this.router.navigate(['/assignment/' + this.assignment.id]);
  }
}
