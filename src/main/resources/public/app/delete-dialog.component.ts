import { Component, Input, OnInit } from 'angular2/core';
import { RouteParams,  Router  } from 'angular2/router';
import { Student } from './student';
import { StudentService } from './student.service';
import 'rxjs/Rx';

@Component({
	selector: 'delete',
     inputs: ['student'],
	templateUrl: 'app/delete-dialog.html',
})

export class DialogComponent {
	@Input() student: Student;

    constructor(private _router: Router, private _studentService : StudentService) {}

    delete() {
        this._studentService.deleteStudent(this.student).subscribe(() => {
        console.log("Delete "+this.student.id);
        location.reload();
    }, err => console.log(err));
    }
}
