package com.xcode.tool;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;

public class ToolMongoDb {

    public static Properties mongoProperties = new Properties();

    public static MongoClient db = null;
    public static GridFS fs = null;

    public static void main(String[] args) throws IOException {
        initPool();
    }

    public static void initPool() throws IOException {
        // jDK 7 新特性，try() 中的资源自动释放
        try (InputStream inStream = ToolMongoDb.class.getResourceAsStream("/mongo-config.properties")) {
            mongoProperties.load(inStream);
            System.out.println(mongoProperties.get("mongo.ip"));
            System.out.println(mongoProperties.get("mongo.port"));
            System.out.println(mongoProperties.get("mongo.dbname"));
            getMongoDb();
        }
    }

    /**
     * 获取 mongodb
     * 
     * @return
     * @throws UnknownHostException
     */
    public static DB getMongoDb() throws UnknownHostException {
        String ip = (String) mongoProperties.get("mongo.ip");
        int port = Integer.parseInt((String) mongoProperties.get("mongo.port"));
        String dbName = mongoProperties.getProperty("mongo.dbname");

        if (null == db) {
            db = new MongoClient(ip, port);
        }
        return db.getDB(dbName);
    }

    /**
     * 获取 gridfs
     * 
     * @return
     */
    public static GridFS getGridfs() {
        try {
            if (null == fs) {
                fs = new GridFS(getMongoDb());
                return fs;
            }
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
        return fs;
    }
}
