package com.you.wstro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.you.wstro.service.MailService;
import com.you.wstro.util.MailUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailServiceImpl implements MailService
{
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private MailUtil mailUtil;
    
    /**
     * 发送简单文本邮件
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content)
    {
        try
        {
            mailUtil.sendSimpleTextMail(from, to, subject, content); 
        } catch (Exception e)
        {
            log.info("简单文本邮件发送失败------>", e);
        }
    }

    /**
     * 发送HTML邮件
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content)
    {
        try
        {
            mailUtil.sendHtmlMail(from, to, subject, content); 
        } catch (Exception e)
        {
            log.info("HTML邮件发送失败------>", e);
        }
    }
}
