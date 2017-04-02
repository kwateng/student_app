package Student;

import java.io.Serializable;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kavindya
 */
public class StudentInfo implements Serializable{


private String nameWithInitials;
private String firstName;
private String homeAddres;
private String dateOfbirth;
private String semester;
private String indexNo;
private String telephoneNo;
private String gender;
private String Password;
private String regDate;
private ImageIcon iImage;
    /**
     * @return the nameWithInitials
     */
    public String getNameWithInitials() {
        return nameWithInitials;
    }

    /**
     * @param nameWithInitials the nameWithInitials to set
     */
    public void setNameWithInitials(String nameWithInitials) {
        this.nameWithInitials = nameWithInitials;
    }

    /**
     * @return the homeAddres
     */
    public String getHomeAddres() {
        return homeAddres;
    }

    /**
     * @param homeAddres the homeAddres to set
     */
    public void setHomeAddres(String homeAddres) {
        this.homeAddres = homeAddres;
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
     * @return the indexNo
     */
    public String getIndexNo() {
        return indexNo;
    }

    /**
     * @param indexNo the indexNo to set
     */
    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    /**
     * @return the telephoneNo
     */
    public String getTelephoneNo() {
        return telephoneNo;
    }

    /**
     * @param telephoneNo the telephoneNo to set
     */
    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dateOfbirth
     */
    public String getDateOfbirth() {
        return dateOfbirth;
    }

    /**
     * @param dateOfbirth the dateOfbirth to set
     */
    public void setDateOfbirth(String dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the regDate
     */
    public String getRegDate() {
        return regDate;
    }

    /**
     * @param regDate the regDate to set
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    /**
     * @return the iImage
     */
    public ImageIcon getiImage() {
        return iImage;
    }

    /**
     * @param iImage the iImage to set
     */
    public void setiImage(ImageIcon iImage) {
        this.iImage = iImage;
    }

}
