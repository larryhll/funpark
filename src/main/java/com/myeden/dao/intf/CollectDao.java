package com.myeden.dao.intf;

import com.myeden.entity.CollectDO;

import java.util.List;

/**
 * Created by felhan on 11/16/2016.
 */
public interface CollectDao {

    public void save(CollectDO collectDO);

    public void update(CollectDO collectDO);

    public List<CollectDO> getMmeberCollects(String mobile, int type);

    public CollectDO findById(int id);

    public List<CollectDO> getMemberCollectState(String mobile ,int id);

    public CollectDO getMemberCollectStateByType(String mobile ,int id, int type);
}
