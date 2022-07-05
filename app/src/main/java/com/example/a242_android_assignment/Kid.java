package com.example.a242_android_assignment;

public class Kid {

    private String id;
    private String firstName;
    private String lastName;
    private String age;
    private String gender;
    private String address;
    private String guardianName;
    private String guardianNumber;
    private String guardianEmail;
    private String guardianAddress;


    public Kid(){

    }

    public Kid(String id, String firstName, String lastName, String age, String gender, String address, String guardianName, String guardianNumber, String guardianEmail, String guardianAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.guardianName = guardianName;
        this.guardianNumber = guardianNumber;
        this.guardianEmail = guardianEmail;
        this.guardianAddress = guardianAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianNumber() {
        return guardianNumber;
    }

    public void setGuardianNumber(String guardianNumber) {
        this.guardianNumber = guardianNumber;
    }

    public String getGuardianEmail() {
        return guardianEmail;
    }

    public void setGuardianEmail(String guardianEmail) {
        this.guardianEmail = guardianEmail;
    }

    public String getGuardianAddress() {
        return guardianAddress;
    }

    public void setGuardianAddress(String guardianAddress) {
        this.guardianAddress = guardianAddress;
    }
}
