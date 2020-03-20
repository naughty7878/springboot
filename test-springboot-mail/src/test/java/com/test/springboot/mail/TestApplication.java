package com.test.springboot.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    JavaMailSenderImpl javaMailSenderImpl;

    @Test
    public void test01(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("邮件测试主题");
        simpleMailMessage.setText("邮件测试内容");
        simpleMailMessage.setTo("78447815@qq.com");
        simpleMailMessage.setFrom("naughty7878@163.com");

        javaMailSenderImpl.send(simpleMailMessage);
    }

    @Test
    public void test02() throws MessagingException {

        // 创建一个复杂邮件
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        // 第二个参数为true表示支持附件
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        // 邮件设置
        mimeMessageHelper.setSubject("邮件测试主题");
        // 第二个参数为true表示内容为html
        mimeMessageHelper.setText("<b style=\"color:red\">邮件测试内容</b>",true);
        mimeMessageHelper.setTo("78447815@qq.com");
        mimeMessageHelper.setFrom("naughty7878@163.com");

        // 添加附件
        mimeMessageHelper.addAttachment("1.jpg", new File("/Users/h__d/Downloads/1.jpg"));
        mimeMessageHelper.addAttachment("2.jpg", new File("/Users/h__d/Downloads/2.jpg"));

        javaMailSenderImpl.send(mimeMessage);
    }
}
