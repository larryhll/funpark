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
import java.util.Calendar;
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
                            collectDO.setState(0);
                            collectDO.setCollectDate(Calendar.getInstance());
                            collectDao.save(collectDO);
                        }else{
                            collectDO.setType(1);
                            collectDO.setCollectDate(Calendar.getInstance());
                            collectDO.setState(0);
                            collectDao.save(collectDO);
                        }

                        return Response.ok(collectDO).header("code", "0").build();
                    case "update":
                        CollectDO collectDO2 = collectDao.getMemberCollectStateByType(collectDO.getMobile(), collectDO.getProductId(), type);
                        if(null!=collectDO2){
                            collectDO2.setDeleted(1);
                            collectDO2.setState(1);
                            collectDao.update(collectDO2);
                            return Response.ok().header("code", "0").build();
                        }else{
                            return Response.ok().header("code", "0").build();
                        }
                }


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

    @GET
    @Path("/{mobile}/{id}")
    public Response getCollectState(@PathParam("mobile") String mobile, @PathParam("id") int id) {

        List<CollectDO> collectDOs = collectDao.getMemberCollectState(mobile, id);
        EntityState entityState=new EntityState();
        if(null==collectDOs || collectDOs.size()==0){
            entityState.setState(1);
            return Response.ok(entityState).header("code", "0").build();
        }else{
            entityState.setState(0);
            return Response.ok(entityState).header("code", "0").build();
        }

    }


}

class EntityState{
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}