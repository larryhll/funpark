package com.myeden.dao.intf;

import com.myeden.entity.ProductDO;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
public interface ProductDao {

    public void saveandupdate(ProductDO productDO);

    public void update(ProductDO productDO);

    public ProductDO findProductById(int id);

    public ProductDO findProductByName(String name);

    public List<ProductDO> findProductByCategoryName(String name);

    public List<ProductDO> getAllProducts(String proCategory, int proType, int proPublish, int proRecommd);

    public List<ProductDO> getCurrentMonthPulbished();

    public List<ProductDO> getLastMonthPublished();

    public List<ProductDO> getLatestProducts();

}
