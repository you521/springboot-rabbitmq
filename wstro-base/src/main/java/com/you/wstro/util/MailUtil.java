package com.you.wstro.util;

import java.io.File;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 邮件发送工具类
 * @author Administrator
 *
 */

@Slf4j
@Component
public class MailUtil
{
    /**
     * JavaMailSender在Mail 自动配置类 MailSenderAutoConfiguration 中已经导入，这里直接注入使用即可
     */
    @Autowired
    private JavaMailSender javaMailSender;
    
    /**
     * 发送简单文本邮件，并抄送给他人
     *
     * @param from  发件人
     * @param to  收件人
     * @param cc  抄送人
     * @param subject  主题
     * @param content  内容
     */
    public void sendSimpleTextMail(String from, String to, String cc, String subject, String content) throws MessagingException{
        log.info("开始发送简单文本邮件并抄送:发送给 -->{},抄送给 -->{},发送的主题 -->{},发送的内容 -->{}", to, cc, subject, content);
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        SimpleMailMessage simpleMsg = new SimpleMailMessage();
        // 邮件发送人
        simpleMsg.setFrom(from);
        // 邮件接收人
        simpleMsg.setTo(to);
        // 邮件抄送人
        simpleMsg.setCc(cc);
        // 邮件主题
        simpleMsg.setSubject(subject);
        // 邮件内容
        simpleMsg.setText(content);
        // 邮件发送
        javaMailSender.send(simpleMsg);
        log.info("发送简单文本邮件并抄送成功:发送给 -->{}", to);
    }
    
    /**
     * 发送简单文本邮件
     *
     * @param from  发件人
     * @param to  收件人
     * @param subject  主题
     * @param content  内容
     */
    public void sendSimpleTextMail(String from, String to, String subject, String content) throws MessagingException{
        log.info("开始发送简单文本邮件:发送给 -->{},发送的主题 -->{},发送的内容 -->{}", to, subject, content);
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        SimpleMailMessage simpleMsg = new SimpleMailMessage();
        // 邮件发送人
        simpleMsg.setFrom(from);
        // 邮件接收人
        simpleMsg.setTo(to);
        // 邮件主题
        simpleMsg.setSubject(subject);
        // 邮件内容
        simpleMsg.setText(content);
        // 邮件发送
        javaMailSender.send(simpleMsg);
        log.info("发送简单文本邮件成功:发送给 -->{}", to);
    }
    
    /**
     * 发送 HTML 邮件
     *
     * @param from  发件人
     * @param to  收件人
     * @param subject  主题
     * @param content  内容 
     * @throws MessagingException
     */
    public void sendHtmlMail(String from, String to, String subject, String content) throws MessagingException {
        log.info("开始发送HTML邮件:发送给 -->{},发送的主题 -->{}", to, subject);
        // 获取MimeMessage对象
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        // 邮件发送人
        messageHelper.setFrom(from);
        // 邮件接收人
        messageHelper.setTo(to);
        // 邮件主题
        messageHelper.setSubject(subject);
        // 邮件内容，true 代表是 HTML 邮件
        messageHelper.setText(content, true);
        // 邮件发送        
        javaMailSender.send(message);
        log.info("发送HTML邮件成功:发送给 -->{}", to);
    }
    
    /**
     * 带附件的邮件
     * 
     * @param from  发件人
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件地址
     */
    
    public void sendAttachmentsMail(String from, String to, String subject, String content, String filePath) throws MessagingException{
        log.info("开始发送带附件邮件:发送给 -->{},发送的主题 -->{}", to, subject);
        MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            javaMailSender.send(message);
            log.info("发送带附件邮件成功:发送给 -->{}", to);
        }
}
