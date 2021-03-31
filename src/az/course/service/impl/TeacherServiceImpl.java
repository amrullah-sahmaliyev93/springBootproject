package az.course.service.impl;

import az.course.dao.TeacherDao;
import az.course.model.Teacher;
import az.course.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao;
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao=teacherDao;
    }

    @Override
    public List<Teacher> getTeacherList() throws Exception {
        return teacherDao.getTeacherList();
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws Exception {
        return teacherDao.addTeacher(teacher);
    }
}
