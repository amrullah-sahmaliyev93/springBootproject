package az.course.main;

import az.course.dao.LessonDao;
import az.course.dao.PaymentDao;
import az.course.dao.StudentDao;
import az.course.dao.TeacherDao;
import az.course.dao.impl.LessonDaoImpl;
import az.course.dao.impl.PaymentDaoImpl;
import az.course.dao.impl.StudentDaoImpl;
import az.course.dao.impl.TeacherDaoImpl;
import az.course.gui.MainFrame;
import az.course.model.Lesson;
import az.course.model.Payment;
import az.course.model.Student;
import az.course.model.Teacher;
import az.course.service.LessonService;
import az.course.service.PaymentService;
import az.course.service.StudentService;
import az.course.service.TeacherService;
import az.course.service.impl.LessonServiceImpl;
import az.course.service.impl.PaymentServiceImpl;
import az.course.service.impl.StudentServiceImpl;
import az.course.service.impl.TeacherServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which model: student,teacher,lesson,payment");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        StudentDao studentDao = new StudentDaoImpl();
        StudentService studentService = new StudentServiceImpl(studentDao);
        TeacherDao teacherDao = new TeacherDaoImpl();
        TeacherService teacherService = new TeacherServiceImpl(teacherDao);
        LessonDao lessonDao = new LessonDaoImpl();
        LessonService lessonService = new LessonServiceImpl(lessonDao);
        PaymentDao paymentDao = new PaymentDaoImpl();
        PaymentService paymentService = new PaymentServiceImpl(paymentDao);
        try {
            switch (sc.next()) {
                case "student":
                    System.out.println("Which metod: view,add,update,delet,search");
                    switch (sc.next()) {
                        case "view":

                            List<Student> studentList = studentService.getStudentList();
                            for (Student student : studentList) {
                                System.out.println(student.getId() + " " + student.getName() + " " + student.getSurname() + " " + student.getAddress() + " " + student.getDob() + " " +
                                        student.getPhone());
                            }

                            break;
                        case "add":
                            System.out.println("Enter name:");
                            String name = sc.next();
                            System.out.println("Enter surname:");
                            String surname = sc.next();
                            System.out.println("Enter address:");
                            String address = sc.next();
                            System.out.println("Enter dob: Format MM/DD/YYYY");
                            String dob = sc.next();
                            System.out.println("Enter phone:");
                            String phone = sc.next();
                            Student student = new Student();
                            student.setName(name);
                            student.setSurname(surname);
                            student.setAddress(address);
                            student.setDob(df.parse(dob));
                            student.setPhone(phone);
                            boolean isAdd = studentService.addStudent(student);
                            if (isAdd){
                                System.out.println("Student add sucess!");
                            }else{
                                System.out.println("Student has not ben aded!");
                            }
                            break;
                        default:
                            System.out.println("Invalid metod!");
                    }
                    break;
                case "teacher":
                    System.out.println("Which metod: view,add,update,delet,search");
                    switch (sc.next()) {
                        case "view":
                            List<Teacher> teacherList = teacherService.getTeacherList();
                            for (Teacher teacher : teacherList) {
                                System.out.println(teacher.getId() + " " + teacher.getName() + " " + teacher.getSurname() + " " + teacher.getAddress() + " " + teacher.getDob() + " " + teacher.getPhone());
                            }
                            break;
                        case "add":
                            System.out.println("Enter name:");
                            String nameT = sc.next();
                            System.out.println("Enter surname:");
                            String surnameT = sc.next();
                            System.out.println("Enter address:");
                            String addressT = sc.next();
                            System.out.println("Enter dob: Format MM/DD/YYYY");
                            String dobT = sc.next();
                            System.out.println("Enter phone:");
                            String phoneT = sc.next();
                            Teacher teacher = new Teacher();
                            teacher.setName(nameT);
                            teacher.setSurname(surnameT);
                            teacher.setAddress(addressT);
                            teacher.setDob(df.parse(dobT));
                            teacher.setPhone(phoneT);
                            boolean isAdd = teacherService.addTeacher(teacher);
                            if (isAdd){
                                System.out.println("Teacher add sucess!");
                            }else{
                                System.out.println("Teacher has not ben aded!");
                            }
                            break;
                        default:
                            System.out.println("Invalid metod!");
                            break;
                    }
                    break;
                case "lesson":
                    System.out.println("Which metod: view,add,update,delet,search");
                    switch (sc.next()) {
                        case "view":
                            List<Lesson> lessonList = lessonService.getLessonList();
                            for (Lesson lesson : lessonList) {
                                System.out.println(lesson.getId() + " " + lesson.getLessonName() + " " + lesson.getLessonTime() + " " + lesson.getLessonPrice());
                            }
                            break;
                        case "add":
                            System.out.println("Enter Lesson name:");
                            String lessonName = sc.next();
                            System.out.println("Enter Lesson time:");
                            Integer lessonTime = sc.nextInt();
                            System.out.println("Enter Lesson price:");
                            Double lessonPrice = sc.nextDouble();

                            Lesson lesson = new Lesson();
                            lesson.setLessonName(lessonName);
                            lesson.setLessonTime(lessonTime);
                            lesson.setLessonPrice(lessonPrice);
                            boolean isAdd = lessonService.addLesson(lesson);
                            if(isAdd){
                                System.out.println("Lesson added sucess");
                            }else{
                                System.out.println("Lesson has not been added!");
                            }
                            break;
                        default:
                            System.out.println("Invalid model!");

                    }
                    break;
                case "payment":
                    System.out.println("Which metod: view,add,update,delet,search");
                    switch (sc.next()) {
                        case "view":
                            List<Payment> paymentList = paymentService.getPaymentList();
                            for (Payment payment : paymentList) {
                                System.out.println(payment.getId() + " -- " + payment.getStudent().getName() + " " + payment.getStudent().getSurname() + " -- " + payment.getTeacher().getName() + " " + payment.getTeacher().getSurname() + " -- " +
                                        payment.getLesson().getLessonName() + " -- " + payment.getLesson().getLessonPrice());
                            }

                    }
                    break;
                default:
                    System.out.println("Invalid model!");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        main(args);
    }


}
