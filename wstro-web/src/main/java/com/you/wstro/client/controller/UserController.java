package com.you.wstro.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.you.wstro.bean.User;

@RestController
@RequestMapping(value="/api/v1/client/user")
public class UserController
{
      private static Logger logger = LoggerFactory.getLogger(User.class);
      
      
      @RequestMapping(value="/query", method = RequestMethod.GET, produces = "application/json")
      public Object getUserInfo(@RequestParam(value="userId")String userId) {
          logger.info("客户端查询用户信息的接口连接成功，请求参数为：{}",userId);
          return userId;
      }
}
