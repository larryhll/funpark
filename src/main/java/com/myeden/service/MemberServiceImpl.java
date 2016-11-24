package com.myeden.service;

import com.myeden.common.PropertiesDAO;
import com.myeden.common.StringUtil;
import com.myeden.dao.intf.CategoryDao;
import com.myeden.dao.intf.LayoutDao;
import com.myeden.dao.intf.MemberDao;
import com.myeden.entity.CategoryDO;
import com.myeden.entity.LayoutDO;
import com.myeden.entity.MemberDO;
import com.myeden.entity.reqresp.RespLoginEntity;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.cxf.common.util.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by felhan on 11/12/2016.
 */

@Produces({ "application/json;charset=UTF-8", "application/xml" })
@Consumes({ "application/json;charset=UTF-8", "application/xml" })
public class MemberServiceImpl extends BaseService{

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private LayoutDao layoutDao;

    @Autowired
    private CategoryDao categoryDao;

    @POST
    @Path("/save")
    public Response registerMember(String request) {

        try {
            MemberDO memberDO = OBJECT_MAPPER.readValue(request, MemberDO.class);

             // TODO: 11/19/2016   verify mobile whether register?
            String mobile =memberDO.getMemberMobile();
            MemberDO member = memberDao.findMemberByMobile(mobile);
            if (!memberDO.getVericode().equalsIgnoreCase(member.getVericode())) {
                return Response.ok().header("code", "807").build();
            }
            memberDO.setId(member.getId());
            memberDao.update(memberDO);
           // memberDO = memberDao.findMemberByMobile(mobile);
            return Response.ok(memberDO).header("code", "0").header("msg",  PropertiesDAO.readValue("", "0")).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @POST
    @Path("/{update}")
    public Response updateMembere(@PathParam("update") String update,  String request) {

        try {
            MemberDO memberDO = OBJECT_MAPPER.readValue(request, MemberDO.class);
            String mobile =memberDO.getMemberMobile();
            MemberDO member = memberDao.findMemberByMobile(mobile);
            if (null == member) {
                return Response.ok().header("code", "801").build();
            }
            if ("update".equalsIgnoreCase(update)) {
                if (!memberDO.getVericode().equalsIgnoreCase(member.getVericode())) {
                    return Response.ok().header("code", "807").build();
                }
                if (StringUtils.isEmpty(memberDO.getPwd())) {
                    member.setPwd("123456");
                }else{
                    member.setPwd(memberDO.getPwd());
                }

                memberDao.update(member);
                // memberDO = memberDao.findMemberByMobile(mobile);
                return Response.ok(memberDO).header("code", "0").header("msg",  PropertiesDAO.readValue("", "0")).build();
            }else if("edit".equalsIgnoreCase(update)){
                    member.setMemberBirthday(memberDO.getMemberBirthday());
                member.setMemberBabyGender(memberDO.getMemberBabyGender());
                memberDao.update(member);
                return Response.ok().header("code", "0").build();
            }else {
                Response.ok().header("code", "804").build();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取新的随机的4位验证码
     * @return
     */
    private String getRamdom4Number() {
        Double a = Math.random() * 9000 + 1000;
        return String.valueOf(a.intValue());
    }
    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    @GET
    @Path("/sms/{action}/{mobile}")
    public Response sendSms(@PathParam("action") String action, @PathParam("mobile") String mobile) throws IOException, DocumentException {

        if ("register".equalsIgnoreCase(action)) {

            MemberDO memberDO = memberDao.findMemberByMobile(mobile);
       /*     if (memberDO != null) {
                return Response.ok().header("code", "806").build();
            }*/

            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(Url);

            client.getParams().setContentCharset("utf-8");
            method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=utf-8");

            String mobile_code = getRamdom4Number();
            System.out.println("send code: "+mobile_code);




            String content = new String("您的验证码是：注册 " + mobile_code + "。请不要把验证码泄露给其他人。");

            NameValuePair[] data = {//提交短信
                    new NameValuePair("account", PropertiesDAO.readValue("", "sms.username")),
                    // new NameValuePair("password", "密码"), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                    new NameValuePair("password", StringUtil.MD5Encode(PropertiesDAO.readValue("", "apikey"))),
                    new NameValuePair("mobile", mobile),
                    new NameValuePair("content", content),
            };
            method.setRequestBody(data);

            try {
                client.executeMethod(method);

                String SubmitResult =method.getResponseBodyAsString();

                //System.out.println(SubmitResult);

                Document doc = DocumentHelper.parseText(SubmitResult);
                Element root = doc.getRootElement();

                String code = root.elementText("code");
                String msg = root.elementText("msg");
                String smsid = root.elementText("smsid");

                System.out.println(code);
                System.out.println(msg);
                System.out.println(smsid);

                if("2".equals(code)){
                    System.out.println("短信提交成功");
                    VericodeEntity vericodeEntity=new VericodeEntity();
                    vericodeEntity.setVerifyCode(mobile_code);
                    if (null == memberDO) {
                        memberDO=new MemberDO();
                        memberDO.setMemberMobile(mobile);
                        memberDO.setVericode(mobile_code);
                        memberDao.save(memberDO);
                    }else{
                        memberDO.setVericode(mobile_code);
                        memberDao.update(memberDO);
                    }

                   return Response.ok(vericodeEntity).header("code", "0").build();
                }else{
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }

            } catch (HttpException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            }


        }else if("reset".equalsIgnoreCase(action)){

            MemberDO memberDO = memberDao.findMemberByMobile(mobile);
            if (memberDO == null) {
                return Response.ok().header("code", "801").build();
            }

            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(Url);

            client.getParams().setContentCharset("utf-8");
            method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=utf-8");

            String mobile_code = getRamdom4Number();
            System.out.println("send code: "+mobile_code);


            memberDO.setVericode(mobile_code);


            String content = new String("您的验证码是：重置 "  + mobile_code + "。请不要把验证码泄露给其他人。");

            NameValuePair[] data = {//提交短信
                    new NameValuePair("account", PropertiesDAO.readValue("", "sms.username")),
                    // new NameValuePair("password", "密码"), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                    new NameValuePair("password", StringUtil.MD5Encode(PropertiesDAO.readValue("", "apikey"))),
                    new NameValuePair("mobile", mobile),
                    new NameValuePair("content", content),
            };
            method.setRequestBody(data);

            try {
                client.executeMethod(method);

                String SubmitResult =method.getResponseBodyAsString();

                //System.out.println(SubmitResult);

                Document doc = DocumentHelper.parseText(SubmitResult);
                Element root = doc.getRootElement();

                String code = root.elementText("code");
                String msg = root.elementText("msg");
                String smsid = root.elementText("smsid");

                System.out.println(code);
                System.out.println(msg);
                System.out.println(smsid);

                if("2".equals(code)){
                    System.out.println("短信提交成功");
                    VericodeEntity vericodeEntity=new VericodeEntity();
                    vericodeEntity.setVerifyCode(mobile_code);
                    memberDao.update(memberDO);
                    return Response.ok(vericodeEntity).header("code", "0").build();
                }

            } catch (HttpException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            }


        }

        return null;
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
            if (null == memberDO) {
                //return Response.noContent().build();
                return Response.ok().header("code", "801").header("msg",  PropertiesDAO.readValue("", "801")).build();
            }

            List<LayoutDO> layoutDOs=layoutDao.findAllLayout();
            List<CategoryDO> categoryDOs = categoryDao.findLevelTwo();
            entity.setLayoutDOs(layoutDOs);
            entity.setMemberDO(memberDO);
            entity.setCategoryDOs(categoryDOs);

            return Response.ok(entity).header("code", "0").header("msg",  PropertiesDAO.readValue("", "0")).build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;

    }

    @GET
    @Path("/unlogin")
    public Response unlogin() {

        RespLoginEntity entity = new RespLoginEntity();
        try {

            List<LayoutDO> layoutDOs=layoutDao.findAllLayout();
            List<CategoryDO> categoryDOs = categoryDao.findLevelTwo();
            entity.setLayoutDOs(layoutDOs);
            entity.setCategoryDOs(categoryDOs);
            if (layoutDOs == null) {
                return Response.ok(entity).header("code", "802").build();
            }
            return Response.ok(entity).header("code", "0").header("msg",  PropertiesDAO.readValue("", "0")).build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;

    }

    @GET
    @Path("/lists")
    public Response getAllMembs() {
        List<MemberDO> memberDOs=memberDao.getAllMembs();
        return Response.ok(memberDOs).build();
    }


}

class VericodeEntity{
    private String verifyCode;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
