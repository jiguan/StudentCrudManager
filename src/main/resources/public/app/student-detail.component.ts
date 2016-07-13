import { Component, OnInit } from 'angular2/core';
import { StudentService } from './student.service';
import { RouteParams,  Router  } from 'angular2/router';
import { Student } from './student';
import 'rxjs/Rx';

@Component({
	selector: 'student-detail',
	templateUrl: 'app/student-detail.html',
    // styleUrls: ['app/view/post-detail.component.css']
})

export class StudentDetailComponent {
	student: Student = new Student();
	constructor(private _router: Router, private _studentService: StudentService, private _routeParams: RouteParams) {
		let id = this._routeParams.get('id');
		if(id!==null) {
			this._studentService.getStudent(id).subscribe(
				 data => { this.student = data },
				 err => console.error(err)
			 );
		}
	}
	save() {
		this._studentService.saveStudent(this.student).subscribe(
		data => { this.student = data },
		err => console.error(err)
	);
		this._router.navigate(['StudentList']);
	}
	cancel() {
		this._router.navigate(['StudentList']);
	}

	goBack() {
		window.history.back();
	}
}
