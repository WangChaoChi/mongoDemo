package com.wcc.mogon.dao.impl;

import com.wcc.mogon.dao.UserDao;
import com.wcc.mogon.pojo.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Resource
    private MongoTemplate mongoTemplate;

    public void insert(User obj, String collectionName) {
        mongoTemplate.insert(obj, collectionName);
    }

    public User findOne(Map<String, Object> params, String collectionName) {
        return mongoTemplate.findOne(
                new Query(Criteria.where("id").is(params.get("id"))),User.class,collectionName);
    }

    public List<User> findAll(Map<String, Object> params, String collectionName) {
        List<User> result = mongoTemplate.find(
                new Query(Criteria.where("age").lt(params.get("maxAge"))), User.class, collectionName);
        return result;
    }

    public void update(Map<String, Object> params, String collectionName) {
            mongoTemplate.upsert(new Query(Criteria.where("id").is(params.get("id"))),
                new Update().set("name", params.get("name")), User.class,collectionName);

    }

    public void createCollection(String collectionName) {
        mongoTemplate.createCollection(collectionName);
    }

    public void remove(Map<String, Object> params, String collectionName) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(params.get("id"))), User.class, collectionName);
    }
}
