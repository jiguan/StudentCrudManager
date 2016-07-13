import { Injectable } from 'angular2/core';
import { Student } from './student';
import { Response, Http, Headers } from 'angular2/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class StudentService {
    constructor(private http: Http) {}
	getStudents():Observable<[Student]> {
		return this.http.get('http://localhost:8080/api/student')
	    .map(res => res.json());
	}
	getStudent(id: string) {
		return this.http.get('http://localhost:8080/api/student/'+id)
	    .map(res => res.json());
	}

	saveStudent(student: Student): Observable<Student> {
        var headers = new Headers();
        //headers.append('Content-Type', 'application/x-www-form-urlencoded');
        headers.append('Content-Type', 'application/json');
		if(student.id!==null) {
			return this.http.put('http://localhost:8080/api/student/'+student.id, JSON.stringify(student), {headers: headers}).map(resp => resp.json());
		} else {
			return this.http.post('http://localhost:8080/api/student', JSON.stringify(student), {headers: headers}).map(resp => resp.json());
		}
	}
	deleteStudent(student: Student): Observable<Response> {
		return this.http.delete('http://localhost:8080/api/student/'+student.id);
	}

}
