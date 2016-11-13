package com.myeden.dao;

import com.myeden.entity.Student;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by felhan on 11/12/2016.
 */

@Component("studentDaoImpl")
public class StudentDaoImpl extends CommonDao implements StudentDao {



    public void saveStu(Student student) {
        template.save(student);
    }
}
