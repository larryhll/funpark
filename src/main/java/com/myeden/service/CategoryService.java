package com.myeden.service;

import com.myeden.dao.intf.CategoryDao;
import com.myeden.dao.intf.ProductDao;
import com.myeden.entity.CategoryDO;
import com.myeden.entity.ProductDO;
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

    @Autowired
    private ProductDao productDao;

    @POST
    @Path("/add")
    public Response save(String request) {
        try {
            CategoryDO categoryDO = OBJECT_MAPPER.readValue(request, CategoryDO.class);
            CategoryDO categoryDO2 = categoryDao.findCategoryByName(categoryDO.getCategoryName());
            if (null != categoryDO2) {
                return Response.ok().header("code","803").build();
            }
            categoryDao.save(categoryDO);
            //categoryDO = categoryDao.findCategoryByName(categoryDO.getCategoryName());
            return Response.ok(categoryDO).header("code", "0").build();
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
            if(categoryDO.getCategoryLevel()==2) {
                List<ProductDO> productDOs = productDao.getAllProductsByCategoryId(categoryDO.getId());
                if (null != productDOs && productDOs.size() > 0) {
                    for (ProductDO productDO : productDOs) {
                        productDO.setProductCategory(categoryDO.getCategoryName());
                        productDao.update(productDO);
                    }
                }
            }
           return Response.ok().header("code","0").build();
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
    @Path("/list/leveltwo")
    public Response findAllLevelsforLevelTwoDirectly() {

        List<CategoryDO> categoryDOs = categoryDao.findLevelTwo();
        return Response.ok(categoryDOs).build();

    }

    @GET
    @Path("/delete/{id}")
    public Response deleteCategoryById(@PathParam("id") int id) {
        CategoryDO categoryDO = categoryDao.findCategoryByID(id);
        if (null != categoryDO) {

            if(categoryDO.getCategoryLevel()==2) {
                 List<ProductDO> productDOs = productDao.getAllProductsByCategoryId(categoryDO.getId());
                categoryDO.setCategoryDeleted(1);
                categoryDao.update(categoryDO);
                if (null != productDOs && productDOs.size() > 0) {
                    for (ProductDO productDO : productDOs) {
                        productDO.setProductCategory("");
                        productDO.setProductCategoryId(0);
                        productDao.update(productDO);
                    }
                }
            }
        }
        return Response.ok().build();
    }


}
