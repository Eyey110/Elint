package com.qisejin.lintlib.mode;

/**
 * No Description
 * <p>
 * Created by 15:55 2018/5/11.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class BaseModel {
    private String message;
    private String severity;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "message='" + message + '\'' +
                ", severity='" + severity + '\'' +
                '}';
    }
}
