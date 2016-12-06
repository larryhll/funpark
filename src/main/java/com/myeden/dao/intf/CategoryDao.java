package com.myeden.dao.intf;

import com.myeden.entity.CategoryDO;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
public interface CategoryDao {

    public void save(CategoryDO categoryDO);

    public void update(CategoryDO categoryDO);

    public CategoryDO findCategoryByID(int id);

    public CategoryDO findCategoryByName(String name);

    public List<CategoryDO> findLevelOne();



}
