package com.you.wstro;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WstroServerApplicationTest{

    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        //第二步：测试代码
        System.out.println("默认使用的数据源:" + dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println("获取的连接:" + connection);
        connection.close();
    }
}
