package com.fcfz.oas.services;

import com.fcfz.oas.common.ErrInfo;
import com.fcfz.oas.common.utils.MySqlSessionFactory;
import com.fcfz.oas.entity.User;
import com.fcfz.oas.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class login {
    public User loginUser(String username, String passWord) {
        SqlSession session = MySqlSessionFactory.getSession();

        try {
//用户

//           通过account名称 获得user 信息
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.selectByUserName(username);


//密码
//            String userPwdsStr = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

            if (passWord.equals(user.getPassword()) == false) {
                throw new ErrInfo("0002", "用户密码不正确");
            }

            return user;

        } catch (Exception e) {
            throw e;
        } finally {
            MySqlSessionFactory.closeSession();
        }

    }
}
