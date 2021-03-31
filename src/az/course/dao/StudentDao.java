package az.course.dao;

import az.course.model.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getStudentList () throws  Exception;
    boolean addStudent (Student student) throws Exception;
}
