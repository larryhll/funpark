package com.myeden.dao;

import com.myeden.dao.intf.MemberDao;
import com.myeden.entity.MemberDO;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by felhan on 11/12/2016.
 */

@Component("memberDaoImpl")
public class MemberDaoImpl extends CommonDao implements MemberDao {

    private static final String FIND_MEMBER_BY_MOBILE="SELECT * FROM MEMBER WHERE MEMBER_MOBILE=:arg1 AND MEMBER_PWD=:arg2 AND MEMBER_DELETED=0";

    private static final String FIND_MEMBER_BY_MOBILE_2="SELECT * FROM MEMBER WHERE MEMBER_MOBILE=:arg1 AND MEMBER_DELETED=0";


    private static final String FIND_MEMBER_BY_MOBILE_ALL="SELECT * FROM MEMBER WHERE MEMBER_DELETED=0";


    @Transactional
    @Override
    public void save(MemberDO memberDO) {
        template.save(memberDO);
    }

    @Transactional
    @Override
    public void update(MemberDO memberDO) {
        template.update(memberDO);
    }

    @Transactional
    @Override
    public MemberDO findMemberByMobile(String mobile, String pwd) {

        List<MemberDO> memberDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_MEMBER_BY_MOBILE);
        sqlQuery.setString("arg1", mobile);
        sqlQuery.setString("arg2", pwd);
        memberDOs=sqlQuery.addEntity(MemberDO.class).list();

        if (null != memberDOs && memberDOs.size() > 0) {
            return memberDOs.get(0);
        }

        return null;
    }
    @Transactional
    @Override
    public MemberDO findMemberByMobile(String mobile) {

        List<MemberDO> memberDOs=null;
        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_MEMBER_BY_MOBILE_2);
        sqlQuery.setString("arg1", mobile);
        memberDOs=sqlQuery.addEntity(MemberDO.class).list();

        if (null != memberDOs && memberDOs.size() > 0) {
            return memberDOs.get(0);
        }

        return null;
    }

    @Transactional
    @Override
    public List<MemberDO> getAllMembs() {

        SQLQuery sqlQuery = template.getSessionFactory().getCurrentSession().createSQLQuery(FIND_MEMBER_BY_MOBILE_ALL).addEntity(MemberDO.class);
        return sqlQuery.list();

    }




}
