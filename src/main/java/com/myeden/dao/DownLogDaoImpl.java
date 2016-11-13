package com.myeden.dao;

import com.myeden.dao.intf.DownLogDao;
import com.myeden.entity.DownLogDO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by felhan on 11/13/2016.
 */
@Component("downLogDaoImpl")
public class DownLogDaoImpl extends CommonDao implements DownLogDao {

    @Transactional
    @Override
    public void save(DownLogDO downLogDO) {
        template.save(downLogDO);

    }
}
