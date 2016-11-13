package com.myeden.service;

import com.myeden.dao.intf.LayoutDao;
import com.myeden.entity.LayoutDO;
import com.myeden.entity.PostionEntity;
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


    @POST
    @Path("/save")
    public Response saveNew(String request) {
        logger.info("Invoke layout save service api!");
        try {
            LayoutDO layoutDO = OBJECT_MAPPER.readValue(request, LayoutDO.class);
            layoutDao.save(layoutDO);
            return Response.ok().build();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    @POST
    @Path("/updates")
    public Response updateLayouts(String request) {

        try {
            RequestPosiEntity entity = OBJECT_MAPPER.readValue(request, RequestPosiEntity.class);
            List<PostionEntity> entityList=entity.getEntities();
            if (null != entityList && entityList.size() > 0) {
                for (PostionEntity entity1 : entityList) {
                    LayoutDO layoutDO = layoutDao.findByPosition(entity1.getLayoutPosition());
                    if (null != layoutDO) {
                        layoutDO.setLayoutValue(entity1.getLayoutValue());
                        layoutDao.update(layoutDO);

                    }
                }
            }
            return  Response.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
