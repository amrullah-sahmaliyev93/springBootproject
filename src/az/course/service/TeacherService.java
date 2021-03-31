package az.course.service;

import az.course.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeacherList() throws Exception;
    boolean addTeacher (Teacher teacher) throws Exception;

}
