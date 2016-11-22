package com.myeden.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Created by felhan on 10/30/2016.
 */

@Entity
@Table(name = "VIDEO")
public class VideoDO {

    @Id
    @Column(name = "VIDEO_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "VIDEO_NAME")
    private String videoName;

    @Column(name = "VIDEO_URL")
    private String videoUrl;

    @Column(name = "VIDEO_DELETED")
    private int videoDeleted;


    @ManyToOne(fetch = FetchType.EAGER/*, cascade = {CascadeType.ALL}*/)
    @JoinColumn(name = "VIDEO_PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private ProductDO productDO;

 /*   @Column(name = "PRODUCT_ID")
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }*/

    public ProductDO getProductDO() {
        return productDO;
    }

    public void setProductDO(ProductDO productDO) {
        this.productDO = productDO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getVideoDeleted() {
        return videoDeleted;
    }

    public void setVideoDeleted(int videoDeleted) {
        this.videoDeleted = videoDeleted;
    }
 /*   public int getVideoProductId() {
        return videoProductId;
    }

    public void setVideoProductId(int videoProductId) {
        this.videoProductId = videoProductId;
    }*/
}
