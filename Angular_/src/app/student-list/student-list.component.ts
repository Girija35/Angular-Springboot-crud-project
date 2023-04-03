import { Component, OnInit } from '@angular/core';
import { Student} from '../student';
import { StudentService } from '../student.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit{
  students:Observable<Student[]>;
  constructor(private studentService:StudentService,private router:Router){}
  ngOnInit(): void {
    this.getStudents();
  }
  getStudents(){
    this.students=this.studentService.getStudentsList();
    }
  studentDetails(id:number){
    this.router.navigate(['student-details',id]);
  }
  updateStudent(id:number){
      this.router.navigate(['update-student',id]);
    }
  deleteStudent(id:number){
      this.studentService.deleteStudent(id).subscribe(data=>{
      console.log(data);
      this.getStudents();
    })
  }
  }
  

