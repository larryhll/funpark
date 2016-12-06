package com.myeden.service;

import org.apache.cxf.common.util.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by felhan on 11/13/2016.
 */
public class BaseService {


    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {

        OBJECT_MAPPER.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, false);
        OBJECT_MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String updateProductCategory(String strs, int id) {
        String ids = String.valueOf(id).trim();
        String reStr="";
        if (!StringUtils.isEmpty(strs) && !StringUtils.isEmpty(ids)) {
            int num2 = strs.indexOf(ids);
            String bb1 = strs.substring(0, num2).trim();
            String bb2=strs.substring(num2+2, strs.length()).trim();
            System.out.println("strs= " + bb1+bb2);
            reStr=bb1+bb2;
            return reStr;
        }else {
            return strs;
        }

    }

    public boolean containsCategory(String strs, int id) {
        String ids = String.valueOf(id).trim();
        String reStr="";
        if (!StringUtils.isEmpty(strs) && !StringUtils.isEmpty(ids)) {
            int num2 = strs.indexOf(ids);
            if (num2 != -1) {
                return true;
            }

        }
        return false;
    }

}
