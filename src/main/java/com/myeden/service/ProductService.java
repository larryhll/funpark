package com.myeden.service;

import com.myeden.dao.intf.ProductDao;
import com.myeden.entity.ProductDO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

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
            int id=productDO.getId();
            productDO = productDao.findProductById(id);
            productDao.update(productDO);
            return Response.ok().build();

        } catch (IOException e) {
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

    @GET
    @Path("/{id}")
    public Response findProductDetail(@PathParam("id") int id) {
        ProductDO productDO = productDao.findProductById(id);
        return Response.ok(productDO).build();

    }

}
