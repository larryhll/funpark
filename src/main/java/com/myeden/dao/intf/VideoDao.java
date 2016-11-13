package com.myeden.dao.intf;

import com.myeden.entity.VideoDO;

/**
 * Created by felhan on 11/13/2016.
 */
public interface VideoDao {

    public void save(VideoDO videoDO);

    public void update(VideoDO videoDO);

    public VideoDO findVideoById(int id);


}
