package com.myeden.service;

import com.myeden.dao.intf.CategoryDao;
import com.myeden.entity.CategoryDO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */

@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class CategoryService extends BaseService{

    @Autowired
    private CategoryDao categoryDao;

    @POST
    @Path("/add")
    public Response save(String request) {
        try {
            CategoryDO categoryDO = OBJECT_MAPPER.readValue(request, CategoryDO.class);
            categoryDao.save(categoryDO);
            Response.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @POST
    @Path("/update")
    public Response update(String request) {
        try {
            CategoryDO categoryDO = OBJECT_MAPPER.readValue(request, CategoryDO.class);
            // categoryDO = categoryDao.findCategoryByID(categoryDO.getId());
            categoryDao.update(categoryDO);
            Response.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/list/levelone")
    public Response findAllCatesByLevel() {
        return Response.ok(categoryDao.findLevelOne()).build();
    }

    @GET
    @Path("/list/leveltwo/{leveloneId}")
    public Response findAllLevelsforLevelOne(@PathParam("leveloneId") int leveloneId) {

        List<CategoryDO> categoryDOs = categoryDao.findLevelTwoById(leveloneId);
        return Response.ok(categoryDOs).build();

    }

    @GET
    @Path("/delete/{id}")
    public Response deleteCategoryById(@PathParam("id") int id) {
        CategoryDO categoryDO = categoryDao.findCategoryByID(id);
        if (null != categoryDO) {
            categoryDO.setCategoryDeleted(1);
            categoryDao.update(categoryDO);
        }
        return Response.ok().build();
    }


}
