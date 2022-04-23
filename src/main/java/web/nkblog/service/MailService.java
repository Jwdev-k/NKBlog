package web.nkblog.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Service
@Slf4j
public class MailService {
    private final Properties prop = new Properties();

    private Session getSession() throws IOException {
        InputStream in = Resources.getResourceAsStream("mail.properties");
        prop.load(in);
        final String SMTP_USER = prop.getProperty("mail.smtp.user");
        final String SMTP_PASSWORD = prop.getProperty("mail.smtp.password");

        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASSWORD);
            }
        });
    }

    public void sendMail(String body) throws IOException {
        try {
            final String from = "nkblog.co.kr";
            final String fromName = "NKBlog";
            final String subJect = "Reqeust Your Password";
            getSession().setDebug(true); //기본값 false
            MimeMessage msg = new MimeMessage(getSession());
            msg.setFrom(new InternetAddress(from, fromName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("jungwu07@naver.com"));
            msg.setSubject(subJect);
            msg.setText("Your Password = " + body);
            msg.setSentDate(new Date());

            Transport tr = getSession().getTransport();
            tr.connect();
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
            log.info("Find Password Mail Sent");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
