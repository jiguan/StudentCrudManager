import { Component, OnInit } from 'angular2/core';
import { Router } from 'angular2/router';
import { Student } from './student';
import { StudentService } from './student.service';
import { DialogComponent } from './delete-dialog.component';
import 'rxjs/Rx';

@Component({
	selector: 'student-list',
	templateUrl: 'app/student-list.html',
	directives: [DialogComponent],
})

export class StudentListComponent {
	students: Student[];
	constructor(private _router: Router, private _studentService: StudentService) {
		this._studentService.getStudents().subscribe(
			 data => { this.students = data },
			 err => console.error(err)
		 );
	}
    add() {
        let link = ['AddStudent'];
        this._router.navigate(link);
    }
	edit(student: Student) {
		let link = ['EditStudent', { id: student.id }];
		this._router.navigate(link);
	}
    delete(student: Student) {
        this._studentService.deleteStudent(student).subscribe(
			() => {
                console.log("Delete successfully");
			}, err => console.log(err)
        );
    }
	goBack() {
		window.history.back();
	}

}
