package com.myeden.service;

import com.myeden.dao.intf.VideoDao;
import com.myeden.entity.VideoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by felhan on 11/13/2016.
 */

@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class VideoService extends BaseService {

    @Qualifier("vidoDao")
    @Autowired
    private VideoDao videoDao;

    @POST
    @Path("/save")
    public Response addNewVideo(String request) {

        try {
            VideoDO videoDO = OBJECT_MAPPER.readValue(request, VideoDO.class);
            videoDao.save(videoDO);
            return Response.ok().build();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/delete/{id}")
    public Response deleteVideo(@PathParam("id") int id) {
        VideoDO videoDO = videoDao.findVideoById(id);
        if (null != videoDO) {
            videoDO.setVideoDeleted(1);
            videoDao.update(videoDO);
        }
        return Response.ok().build();
    }

}
