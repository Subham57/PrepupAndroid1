package com.subham.prepupfinal;

public class ChangePasswordRequest {

    public String emailId;
    public String Oldpass;
    public String Newpass;

    public ChangePasswordRequest() { super();
    }

    public ChangePasswordRequest(String emailId, String oldpass, String newpass) {
        this.emailId = emailId;
        Oldpass = oldpass;
        Newpass = newpass;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOldpass() {
        return Oldpass;
    }

    public void setOldpass(String oldpass) {
        Oldpass = oldpass;
    }

    public String getNewpass() {
        return Newpass;
    }

    public void setNewpass(String newpass) {
        Newpass = newpass;
    }
}
