package az.course.service.impl;

import az.course.dao.StudentDao;
import az.course.model.Student;
import az.course.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getStudentList() throws Exception {
        return studentDao.getStudentList();
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        return studentDao.addStudent(student);
    }
}
