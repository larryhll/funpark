package com.myeden.service;

import com.myeden.dao.intf.MemberDao;
import com.myeden.dao.intf.SystemDao;
import com.myeden.entity.MemberDO;
import com.myeden.entity.SystemDO;
import com.myeden.entity.reqresp.MyInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by felhan on 11/13/2016.
 */
@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class SystemService extends BaseService {

    @Autowired
    private SystemDao systemDao;

    @Autowired
    private MemberDao memberDao;


    @POST
    @Path("/save")
    public Response addNew(String request) {
        try {
            SystemDO versionDo = systemDao.findSystemByType("version");
            SystemDO systemDO = OBJECT_MAPPER.readValue(request, SystemDO.class);
            if (null == versionDo) {
                systemDO.setSystemTypeName("version");
                systemDao.save(systemDO);
            }else{

                versionDo.setSystemCurrentVersion(versionDo.getSystemLatestVersion());
                versionDo.setSystemLatestVersion(systemDO.getSystemLatestVersion());
                versionDo.setSystemApkDownUrl(systemDO.getSystemApkDownUrl());
                systemDao.update(versionDo);
            }

            return Response.ok().header("code", "0").build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            SystemDO systemDO = systemDao.findSystemDoByID(id);
            if (null != systemDO) {
                systemDO.setSystemDeleted(1);
                systemDao.update(systemDO);
                return Response.ok().header("code", "0").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GET
    @Path("/myinfo/{mobile}")
    public Response getMyinfos(@PathParam("mobile") String mobile) {

        try {
            if ("0".equalsIgnoreCase(mobile)) {


                SystemDO versionDo = systemDao.findSystemByType("version");

                if (null == versionDo) {
                    return Response.ok().header("code", "802").header("msg", "no data!").build();
                }

                MyInfoEntity infoEntity=new MyInfoEntity();
                //infoEntity.setMemberDO(memberDO);
                infoEntity.setSystemDO(versionDo);
                return Response.ok(infoEntity).header("code", "0").header("msg", "successful").build();
            }else{
                MemberDO memberDO = memberDao.findMemberByMobile(mobile);

                SystemDO versionDo = systemDao.findSystemByType("version");

                if (null == versionDo) {
                    return Response.ok().header("code", "802").header("msg", "no data!").build();
                }

                MyInfoEntity infoEntity=new MyInfoEntity();
                infoEntity.setMemberDO(memberDO);
                infoEntity.setSystemDO(versionDo);
                return Response.ok(infoEntity).header("code", "0").header("msg", "successful").build();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }





}
