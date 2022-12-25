package com.denger.micotian.utils.notifications;

public class Notification {
    private String text;
    private String message;
    private long time;
    public Notification(String text, String message){
        this.message = message;
        this.text = text;

        time = System.currentTimeMillis();
    }


    public long getTime() {
        return time;
    }



    public String getMessage() {
        return message;
    }

    public String getText() {
        return text;
    }


}
