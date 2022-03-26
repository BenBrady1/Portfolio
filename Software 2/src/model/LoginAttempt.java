package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.time.LocalDate;


/** Creates and control loginAttempt.*/
public class LoginAttempt {
    private String userName;
    private int loginAtempt;
    private LocalDate date;
    private Timestamp timestamp;
    private Boolean success;

    /** Constructor for LoginAttempt.
     * @param userName Username of LoginAttempt.
     * @param loginAtempt numbered attempt of LoginAttempt.
     * @param date date of the LoginAttempt.
     * @param timestamp timestamp of LoginAttempt.
     * @param success indicates if the attempt was successful.
     * */
    public LoginAttempt(String userName, int loginAtempt, LocalDate date, Timestamp timestamp, Boolean success){
        this.userName = userName;
        this.loginAtempt = loginAtempt;
        this.date = date;
        this.timestamp = timestamp;
        this.success = success;
    }

    /** Human readable representation of LoginAttempt object.*/
    public String toString(){
        String statement = "Username: " + userName +" || Login atmpempt: "+ loginAtempt +" || Date: "+ date +" || Time: "+ timestamp +" || Success: "+ success;
        return statement;
    }

    /** Gets UserName.*/
    public String getUserName() {
        return userName;
    }

    /** Sets UserName.*/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Gets LoginAtempt.*/
    public int getLoginAtempt() {
        return loginAtempt;
    }

    /** Sets LoginAtempt.*/
    public void setLoginAtempt(int loginAtempt) {
        this.loginAtempt = loginAtempt;
    }

    /** Gets date.*/
    public LocalDate getDate() {
        return date;
    }

    /** Sets date.*/
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /** Gets success.*/
    public boolean isSuccess() {
        return success;
    }

    /** Sets Success.*/
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /** Gets timestamp.*/
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /** Sets timestamp.*/
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /** Gets success.*/
    public Boolean getSuccess() {
        return success;
    }

    /** Sets success.*/
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /** Writes to LoginHistory.Txt.*/
    public static void usingPath(String statment) throws IOException {
        String append = statment + "\n ";
        Path path = Paths.get("src/LoginHisotry.txt");
        Files.write(path, append.getBytes(), StandardOpenOption.APPEND);
    }
}