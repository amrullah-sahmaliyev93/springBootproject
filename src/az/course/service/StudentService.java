package az.course.service;

import az.course.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentList () throws  Exception;
    boolean addStudent (Student student) throws Exception;

}
