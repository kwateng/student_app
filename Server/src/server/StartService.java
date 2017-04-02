/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import Admin.Admin;
import Admin.LoginData;
import Client.SendReceiverServer;
import Courses.Course;
import Courses.CourseList;
import Student.DataBase;
import Student.StudentInfo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mySql.Data;

/**
 *
 * @author Kavindya
 */
public class StartService extends Thread{
    private Socket connectionSocket = null;
     public StartService(Socket socket) {
	this.connectionSocket = socket;
    }
public void run()
    {
        try {
            Data dta;
            StudentInfo st;
            String s;
            String index = null;
            int x = 0;
            String username = null;
            String password = null;
            String semester = null;
            String courseId = null;
            String courseID = null;
            String coursename = null;
            ObjectInputStream ob = null;
            SendReceiverServer object = null;
            ob = new ObjectInputStream(connectionSocket.getInputStream());
            object=(SendReceiverServer)ob.readObject();

            if (object.getTitle().startsWith("2"))
            {
            index = object.getTitle().substring(1);
            System.out.println("Show Student");
            dta = new Data();
            StudentInfo info = dta.show(index);
            System.out.println("Done");
            ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());
            out.writeObject(info);
            System.out.println(index);
            }
            else if (object.getTitle().startsWith("3"))
            {
            index = object.getTitle().substring(1);
            System.out.println("Executed");
            dta = new Data();
            dta.remove(index);
            System.out.println(index);
            }
            else if (object.getTitle().equalsIgnoreCase("1"))
            {
            st = (StudentInfo) object.getObject();
            System.out.println(st.getFirstName());
            dta = new Data();
            dta.add(st);
            }
            else if (object.getTitle().startsWith("5"))
            {
            String[] y = object.getTitle().split("xyz");
            username = y[1];
            password = y[2];
            System.out.println("Executed");
            dta = new Data();
            LoginData data = dta.getLogin(username, password);
            ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());
            out.writeObject(data);
            } 
            else if (object.getTitle().startsWith("4"))
            {
            Admin admin;
            admin = (Admin) object.getObject();
            System.out.println(admin.getEmail());
            dta = new Data();
            dta.addAdmin(admin);
            }
            else if (object.getTitle().startsWith("7"))
            {
            System.out.println("Executed");
            semester = object.getTitle().substring(1);
            CourseList list;
            dta = new Data();
            list = dta.viewCourses(semester);
            ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());
            out.writeObject(list);
            } 
            else if (object.getTitle().startsWith("6"))
            {
            Course course;
            course = (Course) object.getObject();
            System.out.println(course.getId());
            dta = new Data();
            dta.addCourse(course);
            } 
            else if (object.getTitle().startsWith("8"))
            {
            System.out.println("Executed");
            courseId = object.getTitle().substring(1);
            Course list;
            dta = new Data();
            list = dta.viewCourse(courseId);
            ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());
            out.writeObject(list);
            } else if (object.getTitle().startsWith("9"))
            {
            String[] y = object.getTitle().split("xyz");
            index = y[1];
            courseID = y[2];
            coursename = y[3];
            System.out.println("Executed");
            dta = new Data();
            dta.enroleCourse(index, courseID, coursename);
            }
            else if (object.getTitle().startsWith("10"))
            {
            index = object.getTitle().substring(2);
            System.out.println("Executed");
            CourseList list;
            dta = new Data();
            list = dta.viewStudentCourses(index);
            ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());
            out.writeObject(list);
            } 
            else if (object.getTitle().startsWith("11"))
            {
            semester = object.getTitle().substring(2);
            System.out.println("Executed");
            DataBase db;
            dta = new Data();
            db = dta.semesterStatics(semester);
            ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());
            out.writeObject(db);
            } 
            else if (object.getTitle().startsWith("12"))
            {
            courseId =object.getTitle().substring(2);
            System.out.println("Executed");
            DataBase db;
            dta = new Data();
            db = dta.courseStatics(courseId);
            ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());
            out.writeObject(db);
            }
            else if(object.getTitle().startsWith("13"))
            {
            StudentInfo  stt = (StudentInfo)object.getObject();
            System.out.println(stt.getFirstName());
            dta = new Data();
            dta.update(stt);
            }
            } catch (SQLException ex) {
            Logger.getLogger(StartService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartService.class.getName()).log(Level.SEVERE, null, ex);
        } 

}
}

