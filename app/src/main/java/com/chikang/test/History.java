package com.chikang.test;

public class History {

    // Used for storing Leaderboard object
    //Generate Getter and Setter
    private int id;
    private String username;
    private String playDate;
    private String duration;
    private int score;
    private int goblinKilled;
    private int angelSaved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGoblinKilled() {
        return String.valueOf(goblinKilled);
    }

    public void setGoblinKilled(int goblinKilled) {
        this.goblinKilled = goblinKilled;
    }

    public String getAngelSaved() {
        return String.valueOf(angelSaved);
    }

    public void setAngelSaved(int angelSaved) {
        this.angelSaved = angelSaved;
    }
}
