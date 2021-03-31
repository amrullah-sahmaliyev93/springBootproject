package az.course.dao.impl;

import az.course.dao.DbHelper;
import az.course.dao.LessonDao;
import az.course.model.Lesson;
import az.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {
    @Override
    public List<Lesson> getLessonList() throws Exception {
        List<Lesson> lessonList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT L.ID,L.LESSON_NAME,L.LESSON_TIME,L.LESSON_PRICE FROM LESSON L\n" +
                "WHERE ACTIVE =1";
        try{
            c = DbHelper.getConnection();
            if (c != null){
                ps = c.prepareStatement(sql);
                rs =ps.executeQuery();
                while(rs.next()){
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lesson.setLessonTime(rs.getInt("LESSON_TIME"));
                    lesson.setLessonPrice(rs.getDouble("LESSON_PRICE"));
                    lessonList.add(lesson);
                }
            }else{
                System.out.println("Connection is null!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtility.close(c,ps,rs);
        }
        return lessonList;
    }

    @Override
    public boolean addLesson(Lesson lesson) throws Exception {
        boolean result=false;
        Connection c= null;
        PreparedStatement ps=null;
        String sql ="INSERT INTO LESSON (ID,LESSON_NAME,LESSON_TIME,LESSON_PRICE)\n" +
                "VALUES(LESSON_SEQ.NEXTVAL,?,?,?)";
        try{
            c =DbHelper.getConnection();
            if(c!=null){
                ps = c.prepareStatement(sql);
                ps.setString(1,lesson.getLessonName());
                ps.setInt(2,lesson.getLessonTime());
                ps.setDouble(3,lesson.getLessonPrice());
                ps.execute();
                result =true;
            }else{
                System.out.println("Connection is null!");
            }
        }catch (Exception ex){
           ex.printStackTrace();
        }finally {
            c.commit();
            JdbcUtility.close(c,ps,null);
        }
        return result;
    }
}
