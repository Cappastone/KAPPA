package com.codeup.kappa.models;

import javax.persistence.*;

@Entity
@Table(name = "platform_links")
public class PlatformLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String xbox;

    @Column
    private String playstation;

    @Column
    private String nintendo;

    @Column
    private String discord;

    @Column
    private String twitch;

    @Column
    private String youtube;

    public PlatformLinks() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getXbox() {
        return xbox;
    }

    public void setXbox(String xbox) {
        this.xbox = xbox;
    }

    public String getPlaystation() {
        return playstation;
    }

    public void setPlaystation(String playstation) {
        this.playstation = playstation;
    }

    public String getNintendo() {
        return nintendo;
    }

    public void setNintendo(String nintendo) {
        this.nintendo = nintendo;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public String getTwitch() {
        return twitch;
    }

    public void setTwitch(String twitch) {
        this.twitch = twitch;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
