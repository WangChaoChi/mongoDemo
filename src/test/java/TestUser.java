import com.wcc.mogon.dao.UserDao;
import com.wcc.mogon.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mongo.xml")
public class TestUser {

    @Resource
    private UserDao userDaoImpl;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String collectionName = "userCollection";

    @Test
    public void testAdd() {
        for (int i = 0; i < 100; i++) {
            User user = new User(i + "", "wcc" + i, i++, "wccpwd" + i);
            userDaoImpl.insert(user, collectionName);
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "50");
        User user = userDaoImpl.findOne(params, collectionName);
        logger.info("user={}",user);
    }
    @Test
    public void testFindOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "50");
        User user = userDaoImpl.findOne(params, collectionName);
        //System.out.println(user.toString());
        logger.info("user={}",user);
    }

    @Test
    public void testFindAll(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("maxAge", 30);
        List<User> users = userDaoImpl.findAll(params, collectionName);
        logger.info("total={}",users.size());
    }

    @Test
    public void testUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "20");
        params.put("name", "cqq");
        logger.info("beforeName={}",userDaoImpl.findOne(params,collectionName).getName());
        userDaoImpl.update(params, collectionName);
        logger.info("afterName={}",userDaoImpl.findOne(params,collectionName).getName());
    }

    @Test
    public void testRemove(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "52");
        logger.info("user={}",userDaoImpl.findOne(params,collectionName));
        userDaoImpl.remove(params, collectionName);
        logger.info("user={}",userDaoImpl.findOne(params,collectionName));

    }


}
