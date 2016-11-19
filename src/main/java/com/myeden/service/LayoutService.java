package com.myeden.service;

import com.myeden.dao.intf.LayoutDao;
import com.myeden.dao.intf.ProductDao;
import com.myeden.entity.LayoutDO;
import com.myeden.entity.PostionEntity;
import com.myeden.entity.ProductDO;
import com.myeden.entity.RequestPosiEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */

@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class LayoutService extends BaseService {

    private static Log logger = LogFactory.getLog(LayoutService.class);

    @Autowired
    private LayoutDao layoutDao;

    @Autowired
    private ProductDao productDao;


    @POST
    @Path("/save")
    public Response saveNew(String request) {
        logger.info("Invoke layout save service api!");
        try {
            LayoutDO layoutDO = OBJECT_MAPPER.readValue(request, LayoutDO.class);
            LayoutDO layoutDO1 = layoutDao.findByPosition(layoutDO.getLayoutPosition());
            if (null != layoutDO1) {
                return Response.ok().header("code", "803").build();
            }
            layoutDao.save(layoutDO);
            return Response.ok(layoutDO).header("code", "0").build();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    @POST
    @Path("/updates")
    public Response updateLayouts(String request) {

        try {
            System.out.println("request: " + request);
            RequestPosiEntity entity = OBJECT_MAPPER.readValue(request, RequestPosiEntity.class);
            List<PostionEntity> entityList=entity.getEntities();
            if (null != entityList && entityList.size() > 0) {
                for (PostionEntity entity1 : entityList) {
                    LayoutDO layoutDO = layoutDao.findByPosition(Integer.valueOf(entity1.getLayoutPosition()));
                    if (null != layoutDO) {
                        ProductDO productDO=productDao.findProductById(Integer.valueOf(entity1.getLayoutValue()));
                        layoutDO.setLayoutValue(Integer.valueOf(entity1.getLayoutValue()));
                        if (null == productDO) {
                           return Response.ok().header("code", "805").build();
                        }
                        layoutDO.setLayoutProdUrl(productDO.getProductPlayAddr());
                        layoutDao.update(layoutDO);

                    }
                }
            }
            return  Response.ok().header("code", "0").build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
