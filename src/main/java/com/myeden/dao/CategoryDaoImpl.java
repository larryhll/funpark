package com.myeden.dao;

import com.myeden.dao.intf.CategoryDao;
import com.myeden.entity.CategoryDO;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
@Component("categoryDaoImpl")
public class CategoryDaoImpl extends CommonDao implements CategoryDao {

    private static final String FIND_CATEGORY_BY_ID = "SELECT * FROM CATEGORY WHERE CATEGORY_ID=:arg1 AND CATEGORY_DELETED=0";
    private static final String FIND_CATEGORY_BY_LEVEL_ONE = "SELECT * FROM CATEGORY WHERE CATEGORY_LEVEL = 1 AND CATEGORY_DELETED = 0";
    private static final String FIND_CATEGORY_LEVEL_TWO = "SELECT * FROM CATEGORY WHERE CATEGORY_LEVEL=2 AND CATEGORY_PREVIOUS=:arg1 AND CATEGORY_DELETED=0";

    @Transactional
    @Override
    public void save(CategoryDO categoryDO) {
        template.save(categoryDO);
    }

    @Transactional
    @Override
    public void update(CategoryDO categoryDO) {
        template.update(categoryDO);
    }

    @Transactional
    @Override
    public CategoryDO findCategoryByID(int id) {

        List<CategoryDO> categoryDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_CATEGORY_BY_ID).addEntity(CategoryDO.class);
        sqlQuery.setInteger("arg1", id);
        categoryDOs=sqlQuery.list();
        if (null != categoryDOs && categoryDOs.size() > 0) {
            return categoryDOs.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public List<CategoryDO> findLevelOne() {

        List<CategoryDO> categoryDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_CATEGORY_BY_LEVEL_ONE).addEntity(CategoryDO.class);
        return sqlQuery.list();

    }

    @Transactional
    @Override
    public List<CategoryDO> findLevelTwoById(int id) {

        SQLQuery sqlQuery=template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_CATEGORY_LEVEL_TWO).addEntity(CategoryDO.class);
        sqlQuery.setInteger("arg1", id);
        return sqlQuery.list();

    }
}
