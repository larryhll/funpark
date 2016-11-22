package com.myeden.service;

import com.myeden.dao.intf.CollectDao;
import com.myeden.dao.intf.ProductDao;
import com.myeden.entity.CollectDO;
import com.myeden.entity.ProductDO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felhan on 11/17/2016.
 */
@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class CollectService extends BaseService{

    @Autowired
    private CollectDao collectDao;

    @Autowired
    private ProductDao productDao;

    @POST
    @Path("/{type}/{action}")
    public Response addNewEntity(@PathParam("type") int type, @PathParam("action") String action, String request) {

        try {
            CollectDO collectDO = OBJECT_MAPPER.readValue(request, CollectDO.class);


                switch (action) {
                    case "save":
                        if (0 == type) {
                            collectDO.setType(0);
                            collectDao.save(collectDO);
                        }else{
                            collectDO.setType(1);
                            collectDao.save(collectDO);
                        }

                      break;
                    case "update":
                        CollectDO collectDO2 = collectDao.findById(collectDO.getId());
                        collectDO2.setDeleted(1);
                        collectDO2.setState(1);
                        collectDao.update(collectDO2);
                      break;
                }

            return Response.ok(collectDO).header("code", "0").build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    @GET
    @Path("/{type}/{mobile}/lists")
    public Response getListByType(@PathParam("type") int type, @PathParam("mobile") String mobile) {

        try {

            List<CollectDO> collectDOs = collectDao.getMmeberCollects(mobile, type);
            if (null == collectDOs) {
                return Response.ok().header("code", "802").header("msg", "no data!").build();
            }

            List<ProductDO> productDOs = new ArrayList<ProductDO>();
            for (CollectDO collectDO : collectDOs) {
                ProductDO pp=productDao.findProductById(collectDO.getProductId());
                pp.setProductUploadDate(collectDO.getCollectDate());
                productDOs.add(pp);
            }
            return Response.ok(productDOs).header("code", "0").header("msg", "successful").build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


}
