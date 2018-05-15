package com.wcc.mogon;

import com.mongodb.DB;
import com.mongodb.Mongo;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //连接到mongodb服务
        Mongo mongo = new Mongo("127.0.0.1", 27017);
        //获取数据库对象
        DB db = mongo.getDB("test");
        Set<String> collectionNames = db.getCollectionNames();
        for (String dbName: collectionNames) {
            System.out.println(dbName);
        }
    }
}
