import { Component, Input, OnInit } from '@angular/core';
import { Project } from '../../../model/Project';
import { Subject } from '../../../model/Subject';
import { SubjectService } from '../../../service/subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectService } from '../../../service/project.service';

@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css']
})
export class EditProjectComponent implements OnInit {

  private PLACE_HOLDER: Project = {
    experiences: "project experience"
  }

  private EMPTY: Project = {
    experiences: ""
  }

  @Input() project: Project = this.EMPTY
  @Input() parentSubject: Subject
  @Input() isNew: boolean;

  constructor(private projectService: ProjectService,
              private route: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar) {
    if(this.project){
      return
    }
    this.route.params.subscribe(param => {
      projectService.getProject(param.id).subscribe(project => this.project = project, error => {
          console.log('This project doesn\'t exist!');
          router.navigate(['/']);
      });
    });
  }

  ngOnInit(): void {
    if (this.isNew) {
      this.project = this.PLACE_HOLDER; // placeholder
    }
  }

  save() {
    this.projectService.updateProject(this.project).subscribe(_ => {
      this.snackBar.open('Updated successfully', "Close",{duration: 3000})
    }, error => this.snackBar.open("Error!", "Close",{duration: 3000}));
  }

  create() {
    if(!this.parentSubject){
      this.snackBar.open("Please choose a subject!", "Close",{duration: 3000})
      return
    }
    this.projectService.createProject(this.parentSubject, this.project).subscribe((response: any) => {
      if(response.id){
        this.snackBar.open(`Created with id ${response.id}`, "Close",{duration: 3000})
      }else{
        this.snackBar.open("Error!", "Close",{duration: 3000})
      }
    }, error => {
      console.log(error)
      this.snackBar.open('Error!', 'Close', { duration: 3000 });
    });
  }

  delete() {
    this.projectService.deleteProject(this.project.id).subscribe(res => {
      this.snackBar.open("Deleted successfully!", "Close",{duration: 3000})
    }, error => this.snackBar.open("Error!", "Close",{duration: 3000}))
  }

}
