import com.wcc.mogon.dao.UserDao;
import com.wcc.mogon.pojo.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class TestUser2 {
    private static UserDao userDaoImpl;
    private static ClassPathXmlApplicationContext app;
    private static String collectionName;

    @BeforeClass
    public static void initSpring() {
        try {
            app = new ClassPathXmlApplicationContext(
                    new String[] { "classpath:applicationContext-mongo.xml",});
            userDaoImpl = (UserDao) app.getBean("userDaoImpl");
            collectionName ="users";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        for (int i = 0; i < 100; i++) {
            User user = new User(i + "", "wcc" + i, ++i, "wccpwd" + i);
            userDaoImpl.insert(user, collectionName);
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "50");
        User user = userDaoImpl.findOne(params, collectionName);
        System.out.println(user.toString());
    }

    @Test
    public void testFindOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "50");
        User user = userDaoImpl.findOne(params, collectionName);
        System.out.println(user.toString());
    }
}
