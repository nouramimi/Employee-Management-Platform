package com.leoni.usersmanagementleoni.input;

import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = {"user_login", "day"}))
public class EmployeeWorkingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "worked_hours")
    private float workedHours;
    @Column(name = "day")
    @Temporal(TemporalType.DATE)
    private Date day;

    public EmployeeWorkingData()
    {

    }

    public EmployeeWorkingData(String userName, String userLogin, float workedHours, Date day) {
        //this.id=id;
        this.userName = userName;
        this.userLogin = userLogin;
        this.workedHours = workedHours;
        this.day = day;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public float getWorkedHours() {
        return workedHours;
    }

    @Override
    public String toString() {
        return "EmployeeWorkingData [userName=" + userName + ", userLogin=" + userLogin + ", workedHours=" + workedHours
                + ", day=" + day + "]";
    }

    public void setWorkedHours(float workedHours) {
        this.workedHours = workedHours;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }


}