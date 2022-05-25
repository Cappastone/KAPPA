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

}
