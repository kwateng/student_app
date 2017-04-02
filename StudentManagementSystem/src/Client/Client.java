/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;

import Admin.Admin;
import Admin.LoginData;
import Courses.Course;
import Courses.CourseList;
import Student.DataBase;
import Student.StudentInfo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kavindya
 */
public class Client {
private Socket clientSocket;
private StudentInfo student;
    public Client() throws UnknownHostException, IOException {
        
    }
    public void remove(String index) throws IOException
    {
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("3"+index);
    sender.setObject("none");
    ObjectOutputStream out=new ObjectOutputStream(clientSocket.getOutputStream());
    out.writeObject(sender);
    clientSocket.close();
    }
    public void add(StudentInfo student) throws IOException
    {
    clientSocket = new Socket("localhost", 6789);
    System.out.println("Executed");
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("1");
    sender.setObject(student);
    ObjectOutputStream out=new ObjectOutputStream(clientSocket.getOutputStream());
    out.writeObject(sender);

    clientSocket.close();
    }
    public StudentInfo show(String index) throws IOException
    {
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("2"+index);
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    ObjectInputStream out=new ObjectInputStream(clientSocket.getInputStream());
    try {
            this.student = (StudentInfo) out.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    clientSocket.close();
        System.out.println(student.getFirstName());
        System.out.println("Executed");
    return student;
    }
    public LoginData getLogin(String username,String password) throws UnknownHostException, IOException
    {
    LoginData login = null;
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("5"+"xyz"+username+"xyz"+password);
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    ObjectInputStream out=new ObjectInputStream(clientSocket.getInputStream());
    try {
            login = (LoginData) out.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    clientSocket.close();
    System.out.println(login.getUsername());
    System.out.println("Executed");
    return login;
    }

    public void addAdmin(Admin admin) throws UnknownHostException, IOException {
    clientSocket = new Socket("localhost", 6789);
    System.out.println("Executed");
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("4");
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    clientSocket.close();
    }
    public CourseList viewCourses(String semester) throws IOException
    {
     CourseList list = null;
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("7"+semester);
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    System.out.println(semester);
    ObjectInputStream out=new ObjectInputStream(clientSocket.getInputStream());
    try {
            list = (CourseList) out.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    clientSocket.close();
        System.out.println("Executed");
     return list;
    }
    public void add(Course course) throws IOException
    {
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("6");
    sender.setObject(course);
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    clientSocket.close();
    }

    public Course viewCourse(String text) throws IOException {
    Course list = null;
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("8"+text);
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    ObjectInputStream out=new ObjectInputStream(clientSocket.getInputStream());
    try {

            list = (Course) out.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    clientSocket.close();
        System.out.println("Executed");
     return list;
    }

    public void enrole(String index, String toString,String name) throws UnknownHostException, IOException {
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("9"+"xyz"+index+"xyz"+toString+"xyz"+name);
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    clientSocket.close();
    }

    public CourseList viewStudentCourses(String text) throws UnknownHostException, IOException {
    CourseList list = null;
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("10"+text);
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    ObjectInputStream out=new ObjectInputStream(clientSocket.getInputStream());
    try {

            list = (CourseList) out.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    clientSocket.close();
        System.out.println("Executed");
     return list;
    }
     public DataBase viewSemesterStatics(String semester) throws UnknownHostException, IOException {
    DataBase list = null;
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("11"+semester);
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    ObjectInputStream out=new ObjectInputStream(clientSocket.getInputStream());
    try {

            list = (DataBase) out.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    clientSocket.close();
        System.out.println("Executed");
     return list;
    }
      public DataBase viewCourseStatics(String courseId) throws UnknownHostException, IOException {
    DataBase list = null;
    clientSocket = new Socket("localhost", 6789);
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("12"+courseId);
    sender.setObject("none");
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    ObjectInputStream out=new ObjectInputStream(clientSocket.getInputStream());
    try {

            list = (DataBase) out.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    clientSocket.close();
        System.out.println("Executed");
     return list;
    }

    public void update(StudentInfo student) throws UnknownHostException, IOException
    {
    clientSocket = new Socket("localhost", 6789);
    this.student=student;
    System.out.println("Executed");
    SendReceiverServer sender=new SendReceiverServer();
    sender.setTitle("13");
    sender.setObject(student);
    ObjectOutputStream out1=new ObjectOutputStream(clientSocket.getOutputStream());
    out1.writeObject(sender);
    clientSocket.close();
    }
}
