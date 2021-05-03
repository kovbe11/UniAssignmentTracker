import {Component} from '@angular/core';

@Component({
  selector: 'app-my-subjects-list',
  templateUrl: 'my-subjects-list.component.html',
})
export class MySubjectsListComponent {
  typesOfShoes: string[] = ['Boots', 'Clogs', 'Loafers', 'Moccasins', 'Sneakers'];
}
