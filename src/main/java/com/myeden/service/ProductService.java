package com.myeden.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.myeden.dao.intf.ProductDao;
import com.myeden.entity.ProductDO;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */

@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class ProductService extends BaseService {

    @Autowired
    private ProductDao productDao;


    @POST
    @Path("/add")
    public Response addAndUpdate(String request) {
        try {
            ProductDO productDO = OBJECT_MAPPER.readValue(request, ProductDO.class);
            productDao.saveandupdate(productDO);
            return Response.ok().build();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @POST
    @Path("/update")
    public Response update(String request) {
        try {
            ProductDO productDO = OBJECT_MAPPER.readValue(request, ProductDO.class);
            productDao.update(productDO);
            return Response.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @GET
    @Path("/opes/{operations}/{id}")
    public Response operationsPro(@PathParam("operations") String operations, @PathParam("id") int id) {
    // recommend, unrecommend, publish, unpublish
        try {
            ProductDO productDO = productDao.findProductById(id);
            switch (operations) {
                case "recommend":
                    productDO.setProductRecommend(0);
                    productDao.update(productDO);
                    break;
                case "unrecommend":
                    productDO.setProductRecommend(1);
                    productDao.update(productDO);
                    break;
                case "publish":
                    productDO.setPublishState(0);
                    productDao.update(productDO);
                    break;
                case "unpublish":
                    productDO.setPublishState(1);
                    productDao.update(productDO);
                    break;
                default:
                    throw new Exception("Unsupported opeations for product!");
            }

            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //  "SELECT * FROM PRODUCT WHERE PRODUCT_CATEGORY= :arg1 AND PRODUCT_TYPE= :arg2 AND PRODUCT_PUBLISH_STATE= :arg3 AND PRODUCT_RECOMMEND= :arg4 AND PRODUCT_DELETED=0";

    @POST
    @Path("/lists")
    public Response findProductDetail(String request) {

        try {
            org.codehaus.jackson.JsonNode rooNode = OBJECT_MAPPER.readTree(request);
            String category=rooNode.get("productCategory").asText();
            int type=Integer.valueOf(rooNode.get("type").asText());
            int recommed=Integer.valueOf(rooNode.get("productRecommend").asText());
            int state=Integer.valueOf(rooNode.get("publishState").asText());

            List<ProductDO> lists = productDao.getAllProducts(category, type, state, recommed);

            return Response.ok(lists).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @GET
    @Path("/latest/{publishs}")
    public Response getLatestProducts(@PathParam("publishs") String publishs) {
        try {
            if (StringUtils.isEmpty(publishs)) {
                throw new Exception("The request parametere publishs cannot be null!");
            }




           /* SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
            String curMonth = format.format(new Date());

            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -1);
            String lastMonth = format.format(c.getTime());*/

            /*switch (publishs) {
                case "publishs":{
                    RespLatestEntity entity = new RespLatestEntity();
                    entity.setCurrentMonth(productDao.getCurrentMonthPulbished());
                    entity.setLastMonth(productDao.getLastMonthPublished());
                    return Response.ok(entity).build();
                }
                case "access":{

                    break;
                }

            }*/

            List<ProductDO> productDOs=productDao.getLatestProducts();
            return Response.ok(productDOs).build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    class RespLatestEntity{
        private List<ProductDO> currentMonth;
        private List<ProductDO> lastMonth;

        public List<ProductDO> getCurrentMonth() {
            return currentMonth;
        }

        public void setCurrentMonth(List<ProductDO> currentMonth) {
            this.currentMonth = currentMonth;
        }

        public List<ProductDO> getLastMonth() {
            return lastMonth;
        }

        public void setLastMonth(List<ProductDO> lastMonth) {
            this.lastMonth = lastMonth;
        }
    }

}
