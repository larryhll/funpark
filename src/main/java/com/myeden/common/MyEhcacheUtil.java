package com.myeden.common;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

/**
 * Created by felhan on 1/7/2017.
 */
public class MyEhcacheUtil {

    private static final String PATH = "/ehcache.xml";
    private static final String CACHE_NAME = "productEhcache";

    private CacheManager manager;
    private static MyEhcacheUtil ehCache;

    private MyEhcacheUtil(String path) {
        URL url = getClass().getResource(path);
        manager = CacheManager.create(url);
    }

    public static MyEhcacheUtil getInstance() {
        if (ehCache == null) {
            ehCache = new MyEhcacheUtil(PATH);
        }
        return ehCache;
    }

    public void put(String key, Object value) {
        put(CACHE_NAME, key, value);
    }

    public void put(String cacheName, String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        System.out.println("cache name: " + cache.getName());
        cache.acquireWriteLockOnKey(key);
        try{
            Element element = new Element(key, value);
            cache.put(element);
        }finally {
            cache.releaseWriteLockOnKey(key);
        }
    }

    public Object get(String name, String key) {
        Cache cache = manager.getCache(name);
        cache.acquireReadLockOnKey(key);
        Element element=null;
        try{
            element = cache.get(key);
        }finally {
            cache.releaseReadLockOnKey(key);
        }
        return element==null?null:element.getObjectValue();
    }

    public Object get(String key) {
        return get(CACHE_NAME, key);
    }

    public void remove(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }

    public void remove(String key) {
        remove(CACHE_NAME, key);
    }






}
