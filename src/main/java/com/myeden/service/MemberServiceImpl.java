package com.myeden.service;

import com.myeden.dao.intf.LayoutDao;
import com.myeden.dao.intf.MemberDao;
import com.myeden.entity.LayoutDO;
import com.myeden.entity.MemberDO;
import com.myeden.entity.reqresp.RespLoginEntity;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by felhan on 11/12/2016.
 */

@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class MemberServiceImpl extends BaseService{

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private LayoutDao layoutDao;

    @POST
    @Path("/save")
    public Response registerMember(String request) {

        try {
            MemberDO memberDO = OBJECT_MAPPER.readValue(request, MemberDO.class);
            memberDao.save(memberDO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.ok(request).build();
    }

    @POST
    @Path("/login")
    public Response login(String request) {

        RespLoginEntity entity = new RespLoginEntity();
        try {
            ObjectNode rootNode = (ObjectNode) OBJECT_MAPPER.readTree(request);
            String mobile = rootNode.get("mobile").asText();
            String pwd = rootNode.get("pwd").asText();
            MemberDO memberDO = memberDao.findMemberByMobile(mobile, pwd);
            List<LayoutDO> layoutDOs=layoutDao.findAllLayout();

            entity.setLayoutDOs(layoutDOs);
            entity.setMemberDO(memberDO);

            return Response.ok(entity).build();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;

    }


}
