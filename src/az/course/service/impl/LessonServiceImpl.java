package az.course.service.impl;

import az.course.dao.LessonDao;
import az.course.model.Lesson;
import az.course.service.LessonService;

import java.util.List;

public class LessonServiceImpl  implements LessonService {
    private LessonDao lessonDao;
    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<Lesson> getLessonList() throws Exception {
        return lessonDao.getLessonList();
    }

    @Override
    public boolean addLesson(Lesson lesson) throws Exception {
        return lessonDao.addLesson(lesson);
    }
}
