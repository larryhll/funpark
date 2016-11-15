package com.myeden.dao;

import com.myeden.dao.intf.DownLogDao;
import com.myeden.entity.DownLogDO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
@Component("downLogDaoImpl")
public class DownLogDaoImpl extends CommonDao implements DownLogDao {

    private static final String FIND_ALL_LOGS = "SELECT * FROM LOG WHERE LOG_DELETED=0";

    @Transactional
    @Override
    public void save(DownLogDO downLogDO) {
        template.save(downLogDO);

    }

    @Transactional
    @Override
    public List<DownLogDO> getAllDownLogs() {
        return template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_ALL_LOGS).addEntity(DownLogDO.class).list();
    }
}
