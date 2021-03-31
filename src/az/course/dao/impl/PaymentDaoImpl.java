package az.course.dao.impl;

import az.course.dao.DbHelper;
import az.course.dao.PaymentDao;
import az.course.model.Lesson;
import az.course.model.Payment;
import az.course.model.Student;
import az.course.model.Teacher;
import az.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl  implements PaymentDao {
    @Override
    public List<Payment> getPaymentList() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        Connection c =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT P.ID,S.ID STUDENT_ID,S.NAME STUDENT_NAME,S.SURNAME STUDENT_SURNAME,T.ID TEACHER_ID,T.NAME TEACHER_NAME,T.SURNAME TEACHER_SURNAME,L.ID LESSON_ID,L.LESSON_NAME,L.LESSON_PRICE FROM PAYMENT P\n" +
                "INNER JOIN STUDENT S ON P.STUDENT_ID = S.ID\n" +
                "INNER JOIN TEACHER T ON P.TEACHER_ID = T.ID\n" +
                "INNER JOIN LESSON L ON P.LESSON_ID = L.ID\n" +
                "WHERE P.ACTIVE=1";
        try{
            c = DbHelper.getConnection();
            if (c!=null){
                ps = c.prepareStatement(sql);
                rs=ps.executeQuery();
                while (rs.next()){
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    payment.setStudent(student);
                    Teacher teacher = new Teacher();
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    payment.setTeacher(teacher);
                    Lesson lesson = new Lesson();
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lesson.setLessonPrice(rs.getDouble("LESSON_PRICE"));
                    payment.setLesson(lesson);
                    paymentList.add(payment);


                }
            }else{
                System.out.println("Connection is null!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtility.close(c,ps,rs);
        }

        return paymentList;
    }
}
