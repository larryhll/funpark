package com.myeden.dao.intf;

import com.myeden.entity.LayoutDO;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
public interface LayoutDao {

    public void save(LayoutDO layoutDO);

    public void update(LayoutDO layoutDO);

    public LayoutDO findByPosition(int position);

    public List<LayoutDO> findAllLayout();

}
