/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Courses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Kavindya
 */
public class CourseList implements Serializable{
private ArrayList courseList;

    public CourseList() {
        this.courseList = new ArrayList<Course>();
    }

    /**
     * @return the courseList
     */
    public ArrayList getCourseList() {
        return courseList;
    }

    /**
     * @param courseList the courseList to set
     */
    public void setCourseList(ArrayList courseList) {
        this.courseList = courseList;
    }
    public void addCourse(Course course)
    {
        courseList.add(course);
    }
}
