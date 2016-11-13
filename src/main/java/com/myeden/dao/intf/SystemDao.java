package com.myeden.dao.intf;

import com.myeden.entity.SystemDO;

/**
 * Created by felhan on 11/13/2016.
 */
public interface SystemDao {

    public void save(SystemDO systemDO);

    public void update(SystemDO systemDO);

    public SystemDO findSystemDoByID(int id);

}
