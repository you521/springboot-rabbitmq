package com.you.wstro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.you.wstro.service.MailService;

/**
 * spring boot 邮件发送测试类
 * @author Administrator
 *
 */

@RestController
@RequestMapping(value="/api/v1")
public class MailController
{
       private static final String TO = "majun_fly@163.com";
    
       @Autowired
       private MailService mailService;
       
       @RequestMapping(value = "/sendtext", method = RequestMethod.GET)
       public void sendTextMail() {
           String subject = "Springboot 发送简单文本邮件";
           String content = "普通文本邮件内容";
           mailService.sendSimpleMail(TO, subject, content);
       }
       
       @RequestMapping(value = "/sendhtml", method = RequestMethod.GET)
       public void sendHtmlMail() {
           String subject = "Springboot 发送 HTML 邮件";
           String content = "<a href='https://www.cnblogs.com/rookiemzl/p/10887876.html'>点击进入</a>";
           mailService.sendHtmlMail(TO, subject, content);
       }
}
