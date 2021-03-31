package az.course.dao.impl;

import az.course.dao.DbHelper;
import az.course.dao.TeacherDao;
import az.course.model.Teacher;
import az.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> getTeacherList() throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT T.ID,T.NAME,T.SURNAME,T.ADRESS,T.DOB,T.PHONE FROM TEACHER T \n" +
                "WHERE ACTIVE =1 ";
        try{
            c = DbHelper.getConnection();
            if (c != null){
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacher.setAddress(rs.getString("ADRESS"));
                    teacher.setDob(rs.getDate("DOB"));
                    teacher.setPhone(rs.getString("PHONE"));
                    teacherList.add(teacher);
                }

            }else{
                System.out.println("Connection is null!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtility.close(c,ps,rs);
        }
        return teacherList;
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO TEACHER (ID,NAME,SURNAME,ADRESS,DOB,PHONE)" +
                "VALUES(TEACHER_SEQ.NEXTVAL,?,?,?,?,?) ";
        try{
            c = DbHelper.getConnection();
            if(c!=null){
                ps = c.prepareStatement(sql);
                ps.setString(1,teacher.getName());
                ps.setString(2,teacher.getSurname());
                ps.setString(3, teacher.getAddress());
                ps.setDate(4,new java.sql.Date(teacher.getDob().getTime()));
                ps.setString(5,teacher.getPhone());
                ps.execute();
                result = true;
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
