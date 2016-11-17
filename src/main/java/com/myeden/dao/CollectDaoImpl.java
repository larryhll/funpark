package com.myeden.dao;

import com.myeden.dao.intf.CollectDao;
import com.myeden.entity.CollectDO;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by felhan on 11/16/2016.
 */

@Component("collectDaoImpl")
public class CollectDaoImpl extends CommonDao implements CollectDao {

    @Transactional
    @Override
    public void save(CollectDO collectDO) {
        template.save(collectDO);
    }

    @Transactional
    @Override
    public void update(CollectDO collectDO) {
        template.update(collectDO);
    }

    private static final String FIND_MEMBER_COLLECTS = "SELECT * FROM COLLECTS WHERE COLLECT_MOBILE= :arg1 AND COLLECT_TYPE= :arg2 AND COLLECT_STATE=0 AND COLLECT_DELETED=0 ORDER BY COLLECT_DATE DESC";
    private static final String FIND_Collect_COLLECTS = "SELECT * FROM COLLECTS WHERE COLLECT_ID= :arg1 AND COLLECT_DELETED=0";

    @Transactional
    @Override
    public List<CollectDO> getMmeberCollects(String mobile, int type) {
        SQLQuery sqlQuery=template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_MEMBER_COLLECTS).addEntity(CollectDO.class);
        sqlQuery.setString("arg1", mobile);
        sqlQuery.setInteger("arg2", type);

        return sqlQuery.list();
    }

    @Transactional
    @Override
    public CollectDO findById(int id) {
        List<CollectDO> lists=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_Collect_COLLECTS).addEntity(CollectDO.class);
        sqlQuery.setInteger("arg1", id);
        lists=sqlQuery.list();
        if (null != lists && lists.size() > 0) {
            return lists.get(0);
        }
        return null;
    }
}
