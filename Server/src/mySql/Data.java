/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mySql;
import Admin.Admin;
import Admin.LoginData;
import Courses.Course;
import Courses.CourseList;
import Student.DataBase;
import Student.StudentInfo;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author Kavindya
 */
public class Data {
public void add(StudentInfo info) throws SQLException
    {
   Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {
 
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   connection = DriverManager.getConnection(connectionURL, "root", "");
   Image image = info.getiImage().getImage();

   RenderedImage rendered = null;
   if (image instanceof RenderedImage)
   {
    rendered = (RenderedImage)image;
   }
   else
   {
    BufferedImage buffered = new BufferedImage(
        info.getiImage().getIconWidth(),
        info.getiImage().getIconHeight(),
        BufferedImage.TYPE_INT_RGB
    );
    Graphics2D g = buffered.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    rendered = buffered;
   }
    ImageIO.write(rendered, "JPEG", new File("image.jpg"));
    File image1 = new File("image.jpg");
    psmnt = connection.prepareStatement("insert into students(name,indexNo,pass,namewinitial,bday,address,semester,gender,image, Phone,date) "+ "values(?,?,?,?,?,?,?,?,?,?,?)");
    psmnt.setString(1,info.getFirstName());
    psmnt.setString(2,info.getIndexNo());
    psmnt.setString(3,info.getPassword());
    psmnt.setString(4,info.getNameWithInitials());
    psmnt.setString(5,info.getDateOfbirth());
    psmnt.setString(6,info.getHomeAddres());
    psmnt.setString(7,info.getSemester());
    psmnt.setString(8,info.getGender());
    psmnt.setString(10,info.getTelephoneNo());
    psmnt.setString(11,info.getRegDate());

    fis = new FileInputStream(image1);
    psmnt.setBinaryStream(9,fis,fis.available());
    int s = psmnt.executeUpdate();
    if(s>0) {
    System.out.println("Uploaded successfully !");
            }
    else {
    System.out.println("unsucessfull to upload in adding student");
         }
    psmnt.close();
    PreparedStatement pt = connection.prepareStatement("insert into login(name,pass,isadmin)"+"values(?,?,?)");
    pt.setString(1,info.getIndexNo());
    pt.setString(2, info.getPassword());
    pt.setBoolean(3, false);
    pt.executeUpdate();
    PreparedStatement pstmt = connection.prepareStatement("create table if not exists "+info.getIndexNo()+"(courseId VARCHAR(255), courseName VARCHAR(255));");
    pstmt.executeUpdate();
    pt.close();
    pstmt.close();
  }
  // catch if found any exception during rum time.
  catch (Exception ex) {
  System.out.println("Found some error : adding student "+ex);
  }
  finally {
  connection.close();
  
  }
    }
public StudentInfo show(String index)
    {
   Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   connection = DriverManager.getConnection(connectionURL, "root", "");
   psmnt = connection.prepareStatement("SELECT * from students WHERE indexNo=?");
   psmnt.setString(1,index);
   rs = psmnt.executeQuery();
   while (rs.next()){
       infor=new StudentInfo();
       System.out.println("YesFound");
       infor.setFirstName(rs.getString(2));
       infor.setIndexNo(rs.getString(3));
       infor.setNameWithInitials(rs.getString(5));
       infor.setPassword(rs.getString(4));
       infor.setDateOfbirth(rs.getString(6));
       infor.setHomeAddres(rs.getString(7));
       infor.setSemester(rs.getString(8));
       infor.setGender(rs.getString(9));
       infor.setTelephoneNo(rs.getString(11));
       infor.setRegDate(rs.getString(12));
       InputStream is = rs.getBinaryStream("image");
       BufferedImage image = ImageIO.read(is); //the image is read in
       ImageIO.write(image, "jpg", new FileOutputStream("recived.jpg"));
       ImageIcon       icon=new ImageIcon("recived.jpg");
       infor.setiImage(icon);
        }
       }
      
// catch if found any exception during rum time.
    catch (Exception ex) {
        System.out.println("Exception in showing students"+ex);
                         }
    return getInfor();
    }
private StudentInfo infor;

    /**
     * @return the infor
     */
    public StudentInfo getInfor() {
        return infor;
    }

    /**
     * @param infor the infor to set
     */
    public void setInfor(StudentInfo infor) {
        this.infor = infor;
    }

    public void remove(String index) {
         // declare a connection by using Connection interface
   Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {
 // Load JDBC driver "com.mysql.jdbc.Driver"
   Class.forName("com.mysql.jdbc.Driver").newInstance();
             connection = DriverManager.getConnection(connectionURL, "root", "");
             java.sql.Statement statement = connection.createStatement();
             statement.executeUpdate("delete from students where indexNo='"+index+"'");
             statement.executeUpdate("delete from login where name='"+index+"'");
               statement.executeUpdate("DROP TABLE "+index);
    }
// catch if found any exception during rum time.
  catch (Exception ex) {
// create a file object for image by specifying full path of image as parameter.
      System.out.println("Exception in removing student"+ex);
                       }
    }

    public LoginData getLogin(String username,String password)
    {
    LoginData login = null;
    Connection connection = null;
    String connectionURL = "jdbc:mysql://localhost:3306/management";
    ResultSet rs = null;
    PreparedStatement psmnt = null;
    FileInputStream fis;
    try {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
             connection = DriverManager.getConnection(connectionURL, "root", "");
             psmnt = connection.prepareStatement("SELECT * from login WHERE name=? and pass=?");
             psmnt.setString(1, username);
             psmnt.setString(2, password);
             rs=psmnt.executeQuery();
             while(rs.next())
             {
             login=new LoginData();
             System.out.println("laisla");
             login.setIsAdmin(rs.getBoolean(3));
             login.setPassword(rs.getString(2));
             login.setUsername(rs.getString(1));
             System.out.println(" yuyyu");
             }

    }
// catch if found any exception during rum time.
  catch (Exception ex) {
      System.out.println("Exception in getting login data"+ex);
// create a file object for image by specifying full path of image as parameter.
                       }
   return login;
    }

    
    public void addAdmin(Admin admin) throws SQLException
    {
   Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {

   Class.forName("com.mysql.jdbc.Driver").newInstance();
   connection = DriverManager.getConnection(connectionURL, "root", "");
/* prepareStatement() is used for create statement object that is
used for sending sql statements to the specified database. */
   psmnt = connection.prepareStatement("insert into admin(name,username,mobile,email,gender,password) "+ "values(?,?,?,?,?,?)");
   psmnt.setString(1,admin.getName());
   psmnt.setString(2,admin.getUsername());
   psmnt.setString(3,admin.getMobileNo());
   psmnt.setString(4,admin.getEmail());
   psmnt.setString(5,admin.getGender());
   psmnt.setString(6,admin.getPassword());
   int s = psmnt.executeUpdate();
   if(s>0) {
    System.out.println("Uploaded successfully !");
           }
    else {
    System.out.println("unsucessfull to upload");
         }
    psmnt.close();
    PreparedStatement pt = connection.prepareStatement("insert into login(name,pass,isadmin)"+"values(?,?,?)");
    pt.setString(1,admin.getUsername());
    pt.setString(2, admin.getPassword());
    pt.setBoolean(3, true);
    pt.executeUpdate();
    pt.close();
        }
// catch if found any exception during rum time.
  catch (Exception ex) {
  System.out.println("Found some error in adding a admin: "+ex);
  }
  finally {
// close all the connections.
  connection.close();

  }
  }
    public void enroleCourse(String index,String courseId,String courseName) throws SQLException
    {
  Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   PreparedStatement ps=null;
   FileInputStream fis;
   try {

   Class.forName("com.mysql.jdbc.Driver").newInstance();
   connection = DriverManager.getConnection(connectionURL, "root", "");
/* prepareStatement() is used for create statement object that is
used for sending sql statements to the specified database. */
   psmnt = connection.prepareStatement("insert into "+index+"(courseId,courseName) "+ "values(?,?)");
   psmnt.setString(1,courseId);
   psmnt.setString(2,courseName);
   int s = psmnt.executeUpdate();
   if(s>0) {
    System.out.println("Uploaded successfully !");
           }
    else {
    System.out.println("unsucessfull to upload");
         }
    ps = connection.prepareStatement("insert into "+courseId+"(studentIndex) "+ "values(?)");
    ps.setString(1,index);
    ps.executeUpdate();
    ps.close();
    psmnt.close();
        }
// catch if found any exception during rum time.
  catch (Exception ex) {
  System.out.println("Found some error in enroling to a course: "+ex);
  }
  finally {
// close all the connections.
  connection.close();

  }
    }
    public CourseList viewCourses(String Semester)
    {
        CourseList course=new CourseList();
        Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {
 // Load JDBC driver "com.mysql.jdbc.Driver"
     Class.forName("com.mysql.jdbc.Driver").newInstance();
     connection = DriverManager.getConnection(connectionURL, "root", "");
     psmnt = connection.prepareStatement("SELECT * from courses WHERE semester=?");
     psmnt.setString(1, Semester);
     rs = psmnt.executeQuery();
     while (rs.next()){
         System.out.println("COursesssssssss found");
       Course courses=new Course();
       courses.setId(rs.getString(1));
       courses.setLabAssistantName(rs.getString(5));
       courses.setTitle(rs.getString(2));
       courses.setLecturerInChargr(rs.getString(3));
       courses.setLectureName(rs.getString(4));
       courses.setSemester(rs.getString(6));
       courses.setLectureHoursPerWeek(rs.getString(7));
       courses.setPracticleHoursPerWeek(rs.getString(8));
       course.addCourse(courses);
     }
      }
// catch if found any exception during rum time.
  catch (Exception ex) {
// create a file object for image by specifying full path of image as parameter.
      System.out.println("Exception in viewing course"+ex);
                       }
   return course;
    }

    public void addCourse(Course course) throws SQLException {
   Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {

   Class.forName("com.mysql.jdbc.Driver").newInstance();
   connection = DriverManager.getConnection(connectionURL, "root", "");
/* prepareStatement() is used for create statement object that is
used for sending sql statements to the specified database. */
   psmnt = connection.prepareStatement("insert into courses(id,name,lecincharge,lecture,labassistant,semester,lecturehours,labhours) "+ "values(?,?,?,?,?,?,?,?)");
   psmnt.setString(1,course.getId());
   psmnt.setString(2,course.getTitle());
   psmnt.setString(3,course.getLecturerInChargr());
   psmnt.setString(4,course.getLectureName());
   psmnt.setString(5,course.getLabAssistantName());
   psmnt.setString(6,course.getSemester());
   psmnt.setString(7, course.getLectureHoursPerWeek());
   psmnt.setString(8,course.getPracticleHoursPerWeek());
   int s = psmnt.executeUpdate();
   if(s>0) {
    System.out.println("Uploaded successfully !");
           }
    else {
    System.out.println("unsucessfull to upload");
         }
    psmnt.close();
    PreparedStatement pstmt = connection.prepareStatement("create table "+course.getId()+"(studentIndex VARCHAR(255));");
    pstmt.executeUpdate();
    psmnt.close();
        }
// catch if found any exception during rum time.
  catch (Exception ex) {
  System.out.println("Found some error in adding course : "+ex);
  }
  finally {
// close all the connections.
  connection.close();

  }
    }

    public Course viewCourse(String courseId)
    {
         Course course=new Course();
        Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {
 // Load JDBC driver "com.mysql.jdbc.Driver"
     Class.forName("com.mysql.jdbc.Driver").newInstance();
     connection = DriverManager.getConnection(connectionURL, "root", "");
     psmnt = connection.prepareStatement("SELECT * from courses WHERE id=?");
     psmnt.setString(1, courseId);
     rs = psmnt.executeQuery();
     while (rs.next()){
         System.out.println("Course Found");
       //Course courses=new Course();
       course.setId(rs.getString(1));
       course.setLabAssistantName(rs.getString(5));
       course.setTitle(rs.getString(2));
       course.setLecturerInChargr(rs.getString(3));
       course.setLectureName(rs.getString(4));
       course.setSemester(rs.getString(6));
       course.setLectureHoursPerWeek(rs.getString(7));
       course.setPracticleHoursPerWeek(rs.getString(8));

     }
      }
// catch if found any exception during rum time.
  catch (Exception ex) {
// create a file object for image by specifying full path of image as parameter.
      System.out.println("Exception in view course");
                       }
    return course;
    }

    public CourseList viewStudentCourses(String index) {
         CourseList course=new CourseList();
        Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   ResultSet qs=null;
   PreparedStatement psmnt = null;
   PreparedStatement ps = null;
   FileInputStream fis;
   try {
 // Load JDBC driver "com.mysql.jdbc.Driver"
     Class.forName("com.mysql.jdbc.Driver").newInstance();
     connection = DriverManager.getConnection(connectionURL, "root", "");
     psmnt = connection.prepareStatement("SELECT * from "+index);
   //  psmnt.setString(1, index);
     rs = psmnt.executeQuery();
     while (rs.next()){
         System.out.println("Course found");
     ps = connection.prepareStatement("SELECT * from courses WHERE id=?");
     ps.setString(1,rs.getString(1));
     qs = ps.executeQuery();
       while (qs.next())
       {
       Course courses=new Course();
       courses.setId(qs.getString(1));
       courses.setTitle(qs.getString(2));
       courses.setLecturerInChargr(qs.getString(3));
           System.out.println(qs.getString(3));
       courses.setLectureName(qs.getString(4));
       courses.setLabAssistantName(qs.getString(5));
       courses.setSemester(qs.getString(6));
       courses.setLectureHoursPerWeek(qs.getString(7));
       courses.setPracticleHoursPerWeek(qs.getString(8));
       course.addCourse(courses);
         }
     qs.close();
     }
    psmnt.close();
      }
// catch if found any exception during rum time.
  catch (Exception ex) {
// create a file object for image by specifying full path of image as parameter.
      System.out.println("Exception in view Student courses"+ex);
                       }

   return course;
    }
    public DataBase semesterStatics(String semester)
    {
        DataBase db=new DataBase();
         Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   connection = DriverManager.getConnection(connectionURL, "root", "");
   psmnt = connection.prepareStatement("SELECT * from students WHERE semester=?");
   psmnt.setString(1,semester);
   rs = psmnt.executeQuery();
   while (rs.next()){
       infor=new StudentInfo();
       System.out.println("YesFound");
       System.out.println(rs.getString(3));
       infor.setFirstName(rs.getString(2));
       infor.setIndexNo(rs.getString(3));
       infor.setNameWithInitials(rs.getString(5));
       infor.setPassword(rs.getString(4));
       infor.setDateOfbirth(rs.getString(6));
       infor.setHomeAddres(rs.getString(7));
       infor.setSemester(rs.getString(8));
       infor.setGender(rs.getString(9));
       infor.setTelephoneNo(rs.getString(11));
       infor.setRegDate(rs.getString(12));
       InputStream is = rs.getBinaryStream("image");
       BufferedImage image = ImageIO.read(is); //the image is read in
       ImageIO.write(image, "jpg", new FileOutputStream("recived.jpg"));
       ImageIcon       icon=new ImageIcon("recived.jpg");
       infor.setiImage(icon);
       db.addStudent(infor);
       }
        }
     catch (Exception ex) {
         System.out.println("Exception in semester staticd"+ex);
                         }
        return db;

    }

    public DataBase courseStatics(String courseId) {
         DataBase db=new DataBase();
   CourseList course=new CourseList();
   Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   ResultSet qs=null;
   PreparedStatement psmnt = null;
   PreparedStatement ps = null;
   FileInputStream fis;
   try {
 // Load JDBC driver "com.mysql.jdbc.Driver"
     Class.forName("com.mysql.jdbc.Driver").newInstance();
     connection = DriverManager.getConnection(connectionURL, "root", "");
     psmnt = connection.prepareStatement("SELECT * from "+courseId);
     rs = psmnt.executeQuery();
     while (rs.next()){
         System.out.println("Courses Found");
         System.out.println(rs.getString(1));
     ps = connection.prepareStatement("SELECT * from students WHERE indexNo=?");
     ps.setString(1,rs.getString(1));
     qs = ps.executeQuery();
       while (qs.next())
       {
      infor=new StudentInfo();
       System.out.println("Found");
       infor.setFirstName(qs.getString(2));
       infor.setIndexNo(qs.getString(3));
       infor.setNameWithInitials(qs.getString(5));
       infor.setPassword(qs.getString(4));
       infor.setDateOfbirth(qs.getString(6));
       infor.setHomeAddres(qs.getString(7));
       infor.setSemester(qs.getString(8));
       infor.setGender(qs.getString(9));
       infor.setTelephoneNo(qs.getString(11));
       infor.setRegDate(qs.getString(12));
       InputStream is = qs.getBinaryStream("image");
       BufferedImage image = ImageIO.read(is); //the image is read in
       ImageIO.write(image, "jpg", new FileOutputStream("recived.jpg"));
       ImageIcon       icon=new ImageIcon("recived.jpg");
       infor.setiImage(icon);
       db.addStudent(infor);
         }
     qs.close();
     }
    psmnt.close();
      }
// catch if found any exception during rum time.
  catch (Exception ex) {
// create a file object for image by specifying full path of image as parameter.
      System.out.println("Exception in course statics"+ex);

    }
   return db;
}

    public void update(StudentInfo info) {
        String index=info.getIndexNo();
        Connection connection = null;
   String connectionURL = "jdbc:mysql://localhost:3306/management";
   ResultSet rs = null;
   PreparedStatement psmnt = null;
   FileInputStream fis;
   try {
 // Load JDBC driver "com.mysql.jdbc.Driver"
   Class.forName("com.mysql.jdbc.Driver").newInstance();
             connection = DriverManager.getConnection(connectionURL, "root", "");
             java.sql.Statement statement = connection.createStatement();
             statement.executeUpdate("delete from students where indexNo='"+index+"'");
   Image image = info.getiImage().getImage();

   RenderedImage rendered = null;
   if (image instanceof RenderedImage)
   {
    rendered = (RenderedImage)image;
   }
   else
   {
    BufferedImage buffered = new BufferedImage(
        info.getiImage().getIconWidth(),
        info.getiImage().getIconHeight(),
        BufferedImage.TYPE_INT_RGB
    );
    Graphics2D g = buffered.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    rendered = buffered;
   }
    ImageIO.write(rendered, "JPEG", new File("image.jpg"));
    File image1 = new File("image.jpg");
    psmnt = connection.prepareStatement("insert into students(name,indexNo,pass,namewinitial,bday,address,semester,gender,image, Phone,date) "+ "values(?,?,?,?,?,?,?,?,?,?,?)");
    psmnt.setString(1,info.getFirstName());
    psmnt.setString(2,info.getIndexNo());
    psmnt.setString(3,info.getPassword());
    psmnt.setString(4,info.getNameWithInitials());
    psmnt.setString(5,info.getDateOfbirth());
    psmnt.setString(6,info.getHomeAddres());
    psmnt.setString(7,info.getSemester());
    psmnt.setString(8,info.getGender());
    psmnt.setString(10,info.getTelephoneNo());
    psmnt.setString(11,info.getRegDate());
    fis = new FileInputStream(image1);
    psmnt.setBinaryStream(9,fis,fis.available());
    int s = psmnt.executeUpdate();
    if(s>0) {
    System.out.println("Uploaded successfully !");
            }
    else {
    System.out.println("Unsucessfull to upload in adding student");
         }
    }
// catch if found any exception during rum time.
  catch (Exception ex) {
// create a file object for image by specifying full path of image as parameter.
      System.out.println("Exception in removing student"+ex);
                       }
    }
}
