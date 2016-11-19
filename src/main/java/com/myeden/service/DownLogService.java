package com.myeden.service;

import com.myeden.dao.intf.DownLogDao;
import com.myeden.entity.DownLogDO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by felhan on 11/13/2016.
 */


@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class DownLogService extends BaseService{

    @Autowired
    private DownLogDao downLogDao;

    @POST
    @Path("/save")
    public Response saveNewLog(String request) {
        try {
            DownLogDO downLogDO = OBJECT_MAPPER.readValue(request, DownLogDO.class);
            downLogDao.save(downLogDO);
            return Response.ok().header("code", "0").build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/lists")
    public Response getAllLogs() {
        return Response.ok(downLogDao.getAllDownLogs()).header("code", "0").build();
    }



}
