package com.myeden.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by felhan on 11/12/2016.
 */

@Entity
@Table(name="STUDENT")
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;


    @Column(name="NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student: id= "+id+" name = "+name;
    }
}
