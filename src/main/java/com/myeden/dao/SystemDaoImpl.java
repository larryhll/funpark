package com.myeden.dao;

import com.myeden.dao.intf.SystemDao;
import com.myeden.entity.SystemDO;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
@Component("systemDaoImpl")
public class SystemDaoImpl extends CommonDao implements SystemDao {

    private static final String FIND_DO_BY_ID = "SELECT * FROM SYSTEM WHERE SYSTEM_ID=:arg1 AND SYSTEM_DELETED=0";

    private static final String FIND_DO_BY_ID_TYPE = "SELECT * FROM SYSTEM WHERE SYSTEM_TYPE_NAME=:arg1 AND SYSTEM_DELETED=0";

    @Transactional
    @Override
    public void save(SystemDO systemDO) {
        template.save(systemDO);
    }

    @Transactional
    @Override
    public void update(SystemDO systemDO) {
        template.update(systemDO);
    }

    @Transactional
    @Override
    public SystemDO findSystemDoByID(int id) {
        List<SystemDO> systemDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_DO_BY_ID).addEntity(SystemDO.class);
        sqlQuery.setInteger("arg1", id);
        systemDOs=sqlQuery.list();
        if (null != systemDOs && systemDOs.size() > 0) {
            return systemDOs.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public SystemDO findSystemByType(String type) {
        List<SystemDO> systemDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_DO_BY_ID_TYPE).addEntity(SystemDO.class);
        sqlQuery.setString("arg1", type);
        systemDOs=sqlQuery.list();
        if (null != systemDOs && systemDOs.size() > 0) {
            return systemDOs.get(0);
        }
        return null;
    }
}
