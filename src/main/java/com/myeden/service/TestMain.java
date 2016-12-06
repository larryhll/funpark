package com.myeden.service;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by felhan on 11/30/2016.
 */
public class TestMain {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("local host: " + Inet4Address.getLocalHost().getHostAddress().toString());

        String aa = "1,2,3,";
        String bb = "1,2,3,";
        String ids = "2";

        int num = aa.indexOf(ids);
        aa = aa.substring(0, num) + aa.substring(num, aa.length() - 1);
        System.out.println("aa= " + aa);

        int num2 = bb.indexOf(ids);
/*        if (num2 == bb.length() - 1) {
            String bb1 = bb.substring(0, num2);
            String bb2=bb.substring(num+1, bb.length());
            System.out.println("bb= " + bb1+bb2);
        }else{*/
            String bb1 = bb.substring(0, num2);
            String bb2=bb.substring(num+2, bb.length());
            System.out.println("bb= " + bb1+bb2);
       // }

    }



}
