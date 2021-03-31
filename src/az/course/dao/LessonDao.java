package az.course.dao;

import az.course.model.Lesson;

import java.util.List;

public interface LessonDao {
    List<Lesson> getLessonList()throws  Exception;
    boolean addLesson(Lesson lesson) throws Exception;
}
