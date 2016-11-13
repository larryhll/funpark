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

    @Column(name = "CATEGORY_UPDATE_DATE")
    private Calendar categoryUpdateDate;

    @Column(name = "CATEGORY_LEVEL")
    private int categoryLevel;

    @Column(name = "CATEGORY_PREVIOUS")
    private int categoryPrevious;

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

    public Calendar getCategoryUpdateDate() {
        return categoryUpdateDate;
    }

    public void setCategoryUpdateDate(Calendar categoryUpdateDate) {
        this.categoryUpdateDate = categoryUpdateDate;
    }

    public int getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(int categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public int getCategoryPrevious() {
        return categoryPrevious;
    }

    public void setCategoryPrevious(int categoryPrevious) {
        this.categoryPrevious = categoryPrevious;
    }

    public int getCategoryDeleted() {
        return categoryDeleted;
    }

    public void setCategoryDeleted(int categoryDeleted) {
        this.categoryDeleted = categoryDeleted;
    }
}
