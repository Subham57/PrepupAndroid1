package com.subham.prepupfinal;

public class UpdateProfileRequest {

    public String fname;
    public String mname;
    public String lname;
    public long phone;
    public String email;
    public String institute;


    public UpdateProfileRequest() {
        super();
    }

    public UpdateProfileRequest(String fname, String mname, String lname, long phone, String email, String institute) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.institute = institute;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }
}
