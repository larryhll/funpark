package com.myeden.dao;

import com.myeden.dao.intf.LayoutDao;
import com.myeden.entity.LayoutDO;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */

@Component("layoutDaoImpl")
public class LayoutDaoImpl extends CommonDao implements LayoutDao {

    private static final String FIND_BY_POSITION = "SELECT * FROM LAYOUT WHERE LAYOUT_POSITION=:arg1";
    private static final String FIND_ALL_LAYOUT="SELECT * FROM LAYOUT WHERE LAYOUT_NAME='LAYOUT'";

    @Transactional
    @Override
    public void save(LayoutDO layoutDO) {
        template.save(layoutDO);
    }

    @Transactional
    @Override
    public void update(LayoutDO layoutDO) {
        template.update(layoutDO);
    }

    @Transactional
    @Override
    public LayoutDO findByPosition(int position) {

        List<LayoutDO> layoutDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_BY_POSITION).addEntity(LayoutDO.class);
        sqlQuery.setInteger("arg1", position);
        layoutDOs=sqlQuery.list();
        if (null != layoutDOs && layoutDOs.size()>0) {
            return layoutDOs.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public List<LayoutDO> findAllLayout() {
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_ALL_LAYOUT).addEntity(LayoutDO.class);

        return sqlQuery.list();
    }
}
