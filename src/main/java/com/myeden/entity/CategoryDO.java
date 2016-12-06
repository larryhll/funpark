package com.myeden.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by felhan on 10/30/2016.
 */

@Entity
@Table(name = "CATEGORY")
public class CategoryDO {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "CATEGORY_DELETED")
    private int categoryDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryDeleted() {
        return categoryDeleted;
    }

    public void setCategoryDeleted(int categoryDeleted) {
        this.categoryDeleted = categoryDeleted;
    }
}
