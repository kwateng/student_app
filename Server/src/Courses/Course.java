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
public class Course implements Serializable {
    private String id;
    private String title;
    private String lecturerName;
    private String labAssistantName;
    private String practicleHoursPerWeek;
    private String lectureHoursPerWeek;
    private String lecturerInChargr;
    private String semester;
    private ArrayList students;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the lectureName
     */
    public String getLectureName() {
        return lecturerName;
    }

    /**
     * @param lectureName the lectureName to set
     */
    public void setLectureName(String lectureName) {
        this.lecturerName = lectureName;
    }

    /**
     * @return the labAssistantName
     */
    public String getLabAssistantName() {
        return labAssistantName;
    }

    /**
     * @param labAssistantName the labAssistantName to set
     */
    public void setLabAssistantName(String labAssistantName) {
        this.labAssistantName = labAssistantName;
    }

    /**
     * @return the practicleHoursPerWeek
     */
    public String getPracticleHoursPerWeek() {
        return practicleHoursPerWeek;
    }

    /**
     * @param practicleHoursPerWeek the practicleHoursPerWeek to set
     */
    public void setPracticleHoursPerWeek(String practicleHoursPerWeek) {
        this.practicleHoursPerWeek = practicleHoursPerWeek;
    }

    /**
     * @return the lectureHoursPerWeek
     */
    public String getLectureHoursPerWeek() {
        return lectureHoursPerWeek;
    }

    /**
     * @param lectureHoursPerWeek the lectureHoursPerWeek to set
     */
    public void setLectureHoursPerWeek(String lectureHoursPerWeek) {
        this.lectureHoursPerWeek = lectureHoursPerWeek;
    }

    /**
     * @return the lecturerInChargr
     */
    public String getLecturerInChargr() {
        return lecturerInChargr;
    }

    /**
     * @param lecturerInChargr the lecturerInChargr to set
     */
    public void setLecturerInChargr(String lecturerInChargr) {
        this.lecturerInChargr = lecturerInChargr;
    }

    /**
     * @return the lecturerName
     */
    public String getLecturerName() {
        return lecturerName;
    }

    /**
     * @param lecturerName the lecturerName to set
     */
    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
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


}
