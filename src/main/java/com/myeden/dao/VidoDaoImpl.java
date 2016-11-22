package com.myeden.dao;

import com.myeden.dao.intf.VideoDao;
import com.myeden.entity.VideoDO;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
@Component("vidoDaoImpl")
public class VidoDaoImpl extends CommonDao implements VideoDao {

    private static final String FIND_VIDEO_BY_ID = "SELECT * FROM VIDEO WHERE VIDEO_ID=:arg1 and VIDEO_DELETED=0";

    @Transactional
    @Override
    public void save(VideoDO videoDO) {
        template.save(videoDO);
    }

    @Transactional
    @Override
    public void update(VideoDO videoDO) {
        template.update(videoDO);
    }

    @Transactional
    @Override
    public VideoDO findVideoById(int id) {
        List<VideoDO> videoDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_VIDEO_BY_ID).addEntity(VideoDO.class);
        sqlQuery.setInteger("arg1", id);
        videoDOs=sqlQuery.list();
        if (null != videoDOs && videoDOs.size() > 0) {
            return videoDOs.get(0);
        }
        return null;
    }
}
