package com.myeden.entity.reqresp;



/**
 * Created by felhan on 12/19/2016.
 */
public class LayoutDOApp {


    private int id;
    private String layoutName;
    private int layoutPosition;
    private int layoutValue;
    private String layoutProdUrl;
    private int productRecommend;

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

    public String getLayoutProdUrl() {
        return layoutProdUrl;
    }

    public void setLayoutProdUrl(String layoutProdUrl) {
        this.layoutProdUrl = layoutProdUrl;
    }

    public int getProductRecommend() {
        return productRecommend;
    }

    public void setProductRecommend(int productRecommend) {
        this.productRecommend = productRecommend;
    }
}
