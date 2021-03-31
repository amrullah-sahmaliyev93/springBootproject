package az.course.dao;

import az.course.model.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> getTeacherList() throws Exception;
    boolean addTeacher (Teacher teacher) throws Exception;
}
