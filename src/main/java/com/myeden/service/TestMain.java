package com.myeden.service;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by felhan on 11/30/2016.
 */
public class TestMain {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("local host: " + Inet4Address.getLocalHost().getHostAddress().toString());
    }
}
