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
    @Path("/delete/{id}")
    public Response deleteCategoryById(@PathParam("id") int id) {
        CategoryDO categoryDO = categoryDao.findCategoryByID(id);
        if (null != categoryDO) {
            // TODO: 12/6/2016  handle products
                 List<ProductDO> productDOs = productDao.getAllDefaultProds();
            if (null != productDOs) {
                for (ProductDO productDO : productDOs) {
                    //productDO.setProductCategory();
                    productDO.setProductCategory(updateProductCategory(productDO.getProductCategory(), categoryDO.getId()));
                    productDao.update(productDO);
                }

            }

                categoryDO.setCategoryDeleted(1);
                categoryDao.update(categoryDO);

            return Response.ok().header("code", "0").build();
        }
        return Response.ok().header("code", "0").build();
    }


}
