/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;
import com.mysql.jdbc.Connection;
import java.net.*;
import java.sql.*;


class Main {
public static void main(String argv[]) throws Exception
{
    ServerSocket welcomeSocket=null;
    boolean listening=true;
    welcomeSocket = new ServerSocket(6789);
    CreateDatabase();
     while (listening)
     {
	    new StartService(welcomeSocket.accept()).start();
     }
        welcomeSocket.close();
}


public  static void  CreateDatabase(){
    Connection con = null;
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
      try{
        java.sql.Statement st = con.createStatement();
        st.executeUpdate("CREATE DATABASE IF NOT EXISTS management");
        st.executeUpdate("use management");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS students (id int(5) NOT NULL auto_increment,name varchar(255) default NULL,indexNo varchar(255) default NULL,pass varchar(255) default NULL,namewinitial varchar(255) default NULL,bday varchar(255) default NULL,address varchar(255) default NULL,semester varchar(255) default NULL,gender varchar(255) default NULL,image longblob,Phone varchar(15) default NULL,Date varchar(255) default NULL,PRIMARY KEY  (`id`) )");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS login(name varchar(255) default NULL,pass varchar(255) default NULL,isadmin BOOL)");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS admin(name varchar(255) default NULL,username varchar(255) default NULL,mobile varchar(255) default NULL,email varchar(255) default NULL,gender varchar(255) default NULL,password varchar(255) default NULL)");
         st.executeUpdate("CREATE TABLE IF NOT EXISTS courses(id varchar(255) default NULL,name varchar(255) default NULL,lecincharge varchar(255) default NULL,lecture varchar(255) default NULL,labassistant varchar(255) default NULL,semester varchar(255) default NULL,lecturehours varchar(255) default NULL,labhours varchar(255) default NULL)");
        System.out.println("Successfully Execute Table Crearing");
      }
      catch (SQLException s){
        System.out.println("SQL statement is not executed!");
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}
