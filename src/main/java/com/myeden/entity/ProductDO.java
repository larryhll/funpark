package com.myeden.entity;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by felhan on 10/30/2016.
 */

@Entity
@Table(name = "PRODUCT")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Pro.getById", query = "SELECT PRO.* FROM PRODUCT PRO WHERE PRO.PRODUCT_ID=:arg1", resultClass = ProductDO.class)
})
public class ProductDO{

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_PRICE")
    private double price;

    @Column(name = "PRODUCT_TYPE")
    private int type;

    @Column(name = "PRODUCT_MEDIA")
    private String media;

    @Column(name = "PRODUCT_LEVEL_ONE")
    private String prodcutLevelOne;

    @Column(name = "PRODUCT_LEVEL_TWO")
    private String productLevelTwo;

    @Column(name = "PRODUCT_MATCH_AGE")
    private String prodcutMatchAge;

    @Column(name = "PRODUCT_CATEGORY_TAG")
    private String productCategoryTag;

    @Column(name = "PRODUCT_DELETED")
    private int productDeleted;

    @Column(name = "PRODUCT_DESC")
    private String productDesc;

    @Column(name = "PRODUCT_COVER")
    private String productCover;

    @Column(name = "PRODUCT_IMAGES")
    private String productImages;

    @Column(name = "PRODUCT_PLAY_ADDR")
    private String productPlayAddr;

    @Column(name = "PRODUCT_PLAY_ENABLED")
    private int productPlayEnabled;

    @Column(name = "PRODUCT_TRIAL_ADDR")
    private String productTrialAddr;

    @Column(name = "PRODUCT_TRIAL_ENABLED")
    private int productTrialEnabled;

    @Column(name = "PRODUCT_APP_ENABLED")
    private int productAppEnabled;

    @Column(name = "PRODUCT_VERSION")
    private String productVersion;

    @Column(name = "PRODUCT_APK_VERSION")
    private String productApkVersion;

    @Column(name = "PRODUCT_MICRO_STORE_BYECODE_ADDR")
    private String productMicroStoreByecodeAddr;

    @Column(name = "PRODUCT_UPLOAD_DATE")
    private Calendar productUploadDate;

    @Column(name = "PRODUCT_MATCH_SCOPE")
    private String productMatchScope;

    @Column(name = "PRODUCT_MENU_TYPE")
    private int productMenuType;

    @Column(name = "PRODUCT_PACK_NAME")
    private String productPackName;

    @Column(name = "PRODUCT_APK_DOWN_URL")
    private String productApkDownUrl;

    @Column(name = "PRODUCT_MODIFY_DATE")
    private Calendar productModifyDate;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private List<VideoDO> videoDOs;

    @Column(name = "PRODCUT_VIDEOSS")
    private String productVideos;

    @Column(name="PRODUCT_PUBLISH_STATE")
    private int publishState;

    @Column(name="PRODUCT_RECOMMEND")
    private int productRecommend;

    @Column(name = "PRODUCT_CATEGORY")
    private String productCategory;

    @Column(name = "PRODCUT_SCORE")
    private int productScore;

    @Column(name = "PRODUCT_CATEGORY_ID")
    private int productCategoryId;

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public int getProductScore() {
        return productScore;
    }

    public void setProductScore(int productScore) {
        this.productScore = productScore;
    }

    public String getProductVideos() {
        return productVideos;
    }

    public void setProductVideos(String productVideos) {
        this.productVideos = productVideos;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getPublishState() {
        return publishState;
    }

    public void setPublishState(int publishState) {
        this.publishState = publishState;
    }

    public int getProductRecommend() {
        return productRecommend;
    }

    public void setProductRecommend(int productRecommend) {
        this.productRecommend = productRecommend;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getProdcutLevelOne() {
        return prodcutLevelOne;
    }

    public void setProdcutLevelOne(String prodcutLevelOne) {
        this.prodcutLevelOne = prodcutLevelOne;
    }

    public String getProductLevelTwo() {
        return productLevelTwo;
    }

    public void setProductLevelTwo(String productLevelTwo) {
        this.productLevelTwo = productLevelTwo;
    }

    public String getProdcutMatchAge() {
        return prodcutMatchAge;
    }

    public void setProdcutMatchAge(String prodcutMatchAge) {
        this.prodcutMatchAge = prodcutMatchAge;
    }

    public String getProductCategoryTag() {
        return productCategoryTag;
    }

    public void setProductCategoryTag(String productCategoryTag) {
        this.productCategoryTag = productCategoryTag;
    }

    public int getProductDeleted() {
        return productDeleted;
    }

    public void setProductDeleted(int productDeleted) {
        this.productDeleted = productDeleted;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductCover() {
        return productCover;
    }

    public void setProductCover(String productCover) {
        this.productCover = productCover;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public String getProductPlayAddr() {
        return productPlayAddr;
    }

    public void setProductPlayAddr(String productPlayAddr) {
        this.productPlayAddr = productPlayAddr;
    }

    public int getProductPlayEnabled() {
        return productPlayEnabled;
    }

    public void setProductPlayEnabled(int productPlayEnabled) {
        this.productPlayEnabled = productPlayEnabled;
    }

    public String getProductTrialAddr() {
        return productTrialAddr;
    }

    public void setProductTrialAddr(String productTrialAddr) {
        this.productTrialAddr = productTrialAddr;
    }

    public int getProductTrialEnabled() {
        return productTrialEnabled;
    }

    public void setProductTrialEnabled(int productTrialEnabled) {
        this.productTrialEnabled = productTrialEnabled;
    }

    public int getProductAppEnabled() {
        return productAppEnabled;
    }

    public void setProductAppEnabled(int productAppEnabled) {
        this.productAppEnabled = productAppEnabled;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getProductApkVersion() {
        return productApkVersion;
    }

    public void setProductApkVersion(String productApkVersion) {
        this.productApkVersion = productApkVersion;
    }

    public String getProductMicroStoreByecodeAddr() {
        return productMicroStoreByecodeAddr;
    }

    public void setProductMicroStoreByecodeAddr(String productMicroStoreByecodeAddr) {
        this.productMicroStoreByecodeAddr = productMicroStoreByecodeAddr;
    }

    public Calendar getProductUploadDate() {
        return productUploadDate;
    }

    public void setProductUploadDate(Calendar productUploadDate) {
        this.productUploadDate = productUploadDate;
    }

    public String getProductMatchScope() {
        return productMatchScope;
    }

    public void setProductMatchScope(String productMatchScope) {
        this.productMatchScope = productMatchScope;
    }

    public int getProductMenuType() {
        return productMenuType;
    }

    public void setProductMenuType(int productMenuType) {
        this.productMenuType = productMenuType;
    }

    public String getProductPackName() {
        return productPackName;
    }

    public void setProductPackName(String productPackName) {
        this.productPackName = productPackName;
    }

    public String getProductApkDownUrl() {
        return productApkDownUrl;
    }

    public void setProductApkDownUrl(String productApkDownUrl) {
        this.productApkDownUrl = productApkDownUrl;
    }

    public Calendar getProductModifyDate() {
        return productModifyDate;
    }

    public void setProductModifyDate(Calendar productModifyDate) {
        this.productModifyDate = productModifyDate;
    }

    public List<VideoDO> getVideoDOs() {
        return videoDOs;
    }

    public void setVideoDOs(List<VideoDO> videoDOs) {
        this.videoDOs = videoDOs;
    }
}


