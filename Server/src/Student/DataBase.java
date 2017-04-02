package Student;


import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kavindya
 */
public class DataBase implements Serializable {
private ArrayList students;

    public DataBase() {
        this.students = new ArrayList<StudentInfo>();
    }

    /**
     * @return the students
     */
    public ArrayList getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(ArrayList students) {
        this.students = students;
    }
    public void addStudent(StudentInfo student)
    {
        students.add(student);
    }

}
