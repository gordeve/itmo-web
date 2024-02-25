package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Talk implements Serializable {
    private long id;
    private long sourceUserId;
    private long targetUserId;
    private String text;
    private Date creationTime;
    private String sourceLogin;
    private String targetLogin;

    public String getSourceLogin() {
        return sourceLogin;
    }

    public void setSourceLogin(String sourceLogin) {
        this.sourceLogin = sourceLogin;
    }

    public String getTargetLogin() {
        return targetLogin;
    }

    public void setTargetLogin(String targetLogin) {
        this.targetLogin = targetLogin;
    }


    public long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
