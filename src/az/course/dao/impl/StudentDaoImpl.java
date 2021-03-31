package az.course.dao.impl;

import az.course.dao.DbHelper;
import az.course.dao.StudentDao;
import az.course.model.Student;
import az.course.util.JdbcUtility;

import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {


    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        String sql = "SELECT S.ID,S.NAME,S.SURNAME,S.ADRESS,S.DOB,S.PHONE FROM STUDENT S \n" +
                "WHERE ACTIVE =1";
        try{
            c = DbHelper.getConnection();
            if (c!=null){
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()){
                 Student student = new Student();
                 student.setId(rs.getLong("ID"));
                 student.setName(rs.getString("NAME"));
                 student.setSurname(rs.getString("SURNAME"));
                 student.setAddress(rs.getString("ADRESS"));
                 student.setDob(rs.getDate("DOB"));
                 student.setPhone(rs.getString("PHONE"));
                 studentList.add(student);
                }
            }else{
                System.out.println("Connection is null!");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(c,ps,rs);
        }
        return studentList;
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO STUDENT (ID,NAME,SURNAME,ADRESS,DOB,PHONE)\n" +
                " VALUES(STUDENT_SEQ.NEXTVAL,?,?,?,?,?)";
        try{
            c = DbHelper.getConnection();
            if (c!=null){
                ps = c.prepareStatement(sql);
                ps.setString(1,student.getName());
                ps.setString(2,student.getSurname());
                ps.setString(3,student.getAddress());
                ps.setDate(4,new java.sql.Date(student.getDob().getTime()));
                ps.setString(5,student.getPhone());
                ps.execute();
                result = true;
            }else{
                System.out.println("Connection is null!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            c.commit();
            JdbcUtility.close(c,ps,null);
        }

        return result;
    }
}
