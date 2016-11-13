package com.myeden.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.annotation.Resource;

/**
 * Created by felhan on 11/12/2016.
 */
public class CommonDao {

    private SessionFactory sessionFactory ;

    @Autowired(required = true)
    public HibernateTemplate template;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }
}
