package com.myeden.dao.intf;

import com.myeden.entity.DownLogDO;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
public interface DownLogDao {

    public void save(DownLogDO downLogDO);

    public List<DownLogDO> getAllDownLogs();
}
