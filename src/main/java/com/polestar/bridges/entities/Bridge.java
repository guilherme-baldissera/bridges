package com.polestar.bridges.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"latitude" ,"longitude" })
)
@Entity
public class Bridge implements Serializable {

    private static final long serialVersionUID = -2216756051002651100L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private double height;

    @Getter
    @Setter
    private double width;

    @Getter
    @Setter
    private double length;

    @Getter
    @Setter
    private double latitude;

    @Getter
    @Setter
    private double longitude;

    @Getter
    @Setter
    @Column(unique = true)
    private String name;

    public Bridge(){
    }

    @JsonCreator
    public Bridge(double height, double width, double length, double latitude, double longitude, String name){
        this.height = height;
        this.width = width;
        this.length = length;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
}
