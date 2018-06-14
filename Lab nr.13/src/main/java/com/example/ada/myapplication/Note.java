package com.example.klaudiusz.myapplication;

import java.io.Serializable;

public class Note implements Serializable {
    private long id;
    private String description;
    private String subject;
    private String photo;

    @Override
    public String toString() {
        return subject;
    }
    public String returnString()
    {
        return "Note{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
    public Note()
    {

    }
    public Note(String description, String subject, String photo) {
        this.description = description;
        this.subject = subject;
        this.photo = photo;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
