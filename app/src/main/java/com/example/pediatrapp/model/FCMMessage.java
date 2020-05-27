package com.example.pediatrapp.model;

public class FCMMessage {

    private String to;
    private Mensaje data;

    public FCMMessage() {
    }

    public FCMMessage(String to, Mensaje data) {
        this.to = to;
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Mensaje getData() {
        return data;
    }

    public void setData(Mensaje data) {
        this.data = data;
    }


}
