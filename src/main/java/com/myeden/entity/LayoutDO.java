package com.myeden.entity;

import javax.persistence.*;

/**
 * Created by felhan on 10/30/2016.
 */

@Entity
@Table(name = "LAYOUT")
public class LayoutDO {

    @Id
    @Column(name = "LAYOUT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "LAYOUT_NAME")
    private String layoutName;

    @Column(name = "LAYOUT_POSITION")
    private int layoutPosition;

    @Column(name = "LAYOUT_VALUE")
    private int layoutValue;

    @Column(name="LAYOUT_PROD_URL")
    private String layoutProdUrl;

    public String getLayoutProdUrl() {
        return layoutProdUrl;
    }

    public void setLayoutProdUrl(String layoutProdUrl) {
        this.layoutProdUrl = layoutProdUrl;
    }

    public int getLayoutProductId() {
        return layoutProductId;
    }

    public void setLayoutProductId(int layoutProductId) {
        this.layoutProductId = layoutProductId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public int getLayoutPosition() {
        return layoutPosition;
    }

    public void setLayoutPosition(int layoutPosition) {
        this.layoutPosition = layoutPosition;
    }

    public int getLayoutValue() {
        return layoutValue;
    }

    public void setLayoutValue(int layoutValue) {
        this.layoutValue = layoutValue;
    }

    public int getLayoutDeleted() {
        return layoutDeleted;
    }

    public void setLayoutDeleted(int layoutDeleted) {
        this.layoutDeleted = layoutDeleted;
    }

    @Column(name = "LAYOUT_PRODUCT_ID")
    private int layoutProductId;

    @Column(name = "LAYOUT_DELETED")
    private int layoutDeleted;



}
