import com.fcfz.oas.common.utils.MySqlSessionFactory;
import com.fcfz.oas.entity.Log;
import com.fcfz.oas.entity.Role;
import com.fcfz.oas.entity.User;
import com.fcfz.oas.mapper.LogMapper;
import com.fcfz.oas.mapper.RoleMapper;
import com.fcfz.oas.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class test {

    @Test
    public void tsstMybatis(){
        System.out.println("hello");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");

        try {
            SqlSession session = MySqlSessionFactory.getSession();
            LogMapper mapper = session.getMapper(LogMapper.class);
            UserMapper userMapper = session.getMapper(UserMapper.class);
            RoleMapper roleMapper = session.getMapper(RoleMapper.class);

            Log log = mapper.selectByPrimaryKey(1);

            User user = userMapper.selectByPrimaryKey(1);

            Role role = roleMapper.selectByPrimaryKey(1);

            System.out.println("当前日志：" + log);
            System.out.println("当前用户：" + user);
            System.out.println("当前权限：" + role);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            MySqlSessionFactory.closeSession();
        }
    }
}
