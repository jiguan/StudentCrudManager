# springboot-angular2
A simple project demonstrates integration of spring boot and angular2
- Back-end side connects to an embedded DB (HSQLDB) and provides RESTful APIs. 
- Front-end side provides the student list page, edit page and a delete confirmation dialog. 

###Prerequisite
- node v4.x.x 
- npm 3.x.x 

### Angular2 version
- The version of angular 2 is `2.0.0-beta.15`

### How to install
1. Go to `src/main/resources/public` repository, `npm install` to install angular2 dependencies
2. Run `npm start` to verify installation is correct, you should be directed to `localhost:3000` with a page but no data
3. Go back to `springboot-angular2` repository, and run `mvn spring-boot:run`. You should be directed to `localhost:8080`

### Links
- After application is started, [swagger](http://localhost:8080/swagger) presents API summary. 
- [Home page](http://localhost:8080/index.html) or [students page](http://localhost:8080/studnets) list all students

### Screenshots 
- Please check [Issues](https://github.com/jiguan/springboot-angular2/issues) for screenshots
