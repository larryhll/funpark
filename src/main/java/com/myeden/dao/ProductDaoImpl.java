package com.myeden.dao;

import com.myeden.dao.intf.ProductDao;
import com.myeden.entity.ProductDO;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */

@Component("productDaoImpl")
public class ProductDaoImpl extends CommonDao implements ProductDao {

    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM PRODUCT WHERE PRODUCT_ID=:arg1 AND PRODUCT_DELETED=0";

    @Transactional
    @Override
    public void saveandupdate(ProductDO productDO) {
        template.save(productDO);
    }

    @Transactional
    @Override
    public void update(ProductDO productDO) {
        template.update(productDO);
    }

    @Transactional
    @Override
    public ProductDO findProductById(int id) {
        List<ProductDO> productDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_PRODUCT_BY_ID).addEntity(ProductDO.class);
        sqlQuery.setInteger("arg1", id);
        productDOs = sqlQuery.list();
        if (null != productDOs && productDOs.size() > 0) {
            return productDOs.get(0);
        }
        return null;
    }

    @Override
    public List<ProductDO> getAllProducts(Object[] objects) {
        List<ProductDO> productDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery("").addEntity(ProductDO.class);
        //sqlQuery.setParameters(objects);
        //sqlQuery.setParameterList()

        return null;
    }
}
