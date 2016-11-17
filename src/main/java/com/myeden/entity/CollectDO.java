package com.myeden.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by felhan on 11/16/2016.
 */

@Entity
@Table(name = "COLLECTS")
public class CollectDO {

    @Id
    @Column(name = "COLLECT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "COLLECT_TYPE")
    private int type;

    @Column(name = "COLLECT_MOBILE")
    private String mobile;

    @Column(name = "COLLECT_STATE")
    private int state;

    @Column(name = "COLLECT_PRODUCT_ID")
    private int productId;

    @Column(name = "COLLECT_PRODUCT_COVER")
    private String productCover;

    @Column(name = "COLLECT_DATE")
    private Calendar collectDate;

    @Column(name = "COLLECT_DELETED")
    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public String getProductCover() {
        return productCover;
    }

    public void setProductCover(String productCover) {
        this.productCover = productCover;
    }

    public Calendar getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Calendar collectDate) {
        this.collectDate = collectDate;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
