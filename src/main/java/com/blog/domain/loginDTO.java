package com.blog.domain;

public class loginDTO {

    private int id;
    private String uid;
    private String password;
    private String gender;

    public loginDTO(int id, String uid, String password, String gender) {
        this.id = id;
        this.uid = uid;
        this.password = password;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "loginDTO{" +
                "id='" + uid + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuId() {
        return uid;
    }

    public void setuId(String id) {
        this.uid = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
