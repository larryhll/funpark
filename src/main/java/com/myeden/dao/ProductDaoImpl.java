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
    private static final String FIND_PRODUCTS_BY_CONDITIONS = "SELECT * FROM PRODUCT WHERE PRODUCT_CATEGORY= :arg1 AND PRODUCT_TYPE= :arg2 AND PRODUCT_PUBLISH_STATE= :arg3 AND PRODUCT_RECOMMEND= :arg4 AND PRODUCT_DELETED=0 order BY PRODUCT_UPLOAD_DATE DESC";
    private static final String FIND_PRODUCTS_BY_CONDITIONS_ALL = "SELECT * FROM PRODUCT WHERE PRODUCT_TYPE= :arg1 AND PRODUCT_PUBLISH_STATE= :arg2 AND PRODUCT_RECOMMEND= :arg3 AND PRODUCT_DELETED=0 order BY PRODUCT_UPLOAD_DATE DESC";



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

    @Transactional
    @Override
    public List<ProductDO> getAllProducts(String proCategory, int proType, int proPublish, int proRecommd) {

        String all="全部";
        if(all.equalsIgnoreCase(proCategory.trim())){

            List<ProductDO> productDOs=null;
            SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_PRODUCTS_BY_CONDITIONS_ALL).addEntity(ProductDO.class);
            sqlQuery.setInteger("arg1", proType);
            sqlQuery.setInteger("arg2", proPublish);
            sqlQuery.setInteger("arg3", proRecommd);
            productDOs = sqlQuery.list();
            return productDOs;

        }else{

            List<ProductDO> productDOs=null;
            SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_PRODUCTS_BY_CONDITIONS).addEntity(ProductDO.class);
            sqlQuery.setString("arg1", proCategory);
            sqlQuery.setInteger("arg2", proType);
            sqlQuery.setInteger("arg3", proPublish);
            sqlQuery.setInteger("arg4", proRecommd);
            productDOs = sqlQuery.list();
            return productDOs;

        }



    }

    //select * from log where DATE_FORMAT(LOG_DOWNLOAD_DATE,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') and log_deleted=1;

    private static final String FIND_CURRENT_MONTH_PUBLISHED = "select * from PRODUCT where DATE_FORMAT(PRODUCT_UPLOAD_DATE,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') and PRODUCT_DELETED=0 and PRODUCT_PUBLISH_STATE=0 order BY PRODUCT_UPLOAD_DATE DESC ";
    private static final String FIND_LAST_MONTH_PUBLISHED = "select * from PRODUCT where date_format(PRODUCT_UPLOAD_DATE,'%Y-%m')=date_format(DATE_SUB(curdate(), INTERVAL 1 MONTH),'%Y-%m') and PRODUCT_DELETED=0 and PRODUCT_PUBLISH_STATE=0 order BY PRODUCT_UPLOAD_DATE DESC";
    private static final String FIND_LAST_MONTH_PUBLISHED_All = "select * from PRODUCT where PRODUCT_DELETED=0 and PRODUCT_PUBLISH_STATE=0 order BY PRODUCT_UPLOAD_DATE DESC";



    @Transactional
    @Override
    public List<ProductDO> getCurrentMonthPulbished() {
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_CURRENT_MONTH_PUBLISHED).addEntity(ProductDO.class);
        return sqlQuery.list();
    }



    @Transactional
    @Override
    public List<ProductDO> getLastMonthPublished() {
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_LAST_MONTH_PUBLISHED).addEntity(ProductDO.class);
        return sqlQuery.list();
    }

    @Transactional
    @Override
    public List<ProductDO> getLatestProducts() {

        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_LAST_MONTH_PUBLISHED_All).addEntity(ProductDO.class);

        return sqlQuery.list();
    }



   /* @Override



    public List<ProductDO> getAllProducts(Object[] objects) {
        List<ProductDO> productDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery("").addEntity(ProductDO.class);
        //sqlQuery.setParameters(objects);
        //sqlQuery.setParameterList()

        return null;
    }*/
}
