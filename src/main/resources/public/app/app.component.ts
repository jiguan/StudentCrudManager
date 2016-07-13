import { Component } from 'angular2/core';
import { StudentService } from './student.service';
import { StudentDetailComponent } from './student-detail.component';
import { StudentListComponent } from './student-list.component';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router';
import { XHRBackend,RequestOptions,HTTP_PROVIDERS, ConnectionBackend } from 'angular2/http';

@Component({
	selector: 'my-app',
	template:`<router-outlet></router-outlet>`,
	directives: [ROUTER_DIRECTIVES],
	providers: [
		ROUTER_PROVIDERS,
		StudentService,
		HTTP_PROVIDERS
	]
})
@RouteConfig([
		{
			path: '/students',
			name: 'StudentList',
			component: StudentListComponent,
			useAsDefault: true
		},
		//edit existing student
		{
			path: '/student/:id',
			name: 'EditStudent',
			component: StudentDetailComponent,
		},
		//create a new student
		{
			path: '/student',
			name: 'AddStudent',
			component: StudentDetailComponent,
		}
])
export class AppComponent {

}
