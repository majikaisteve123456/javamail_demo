package javamail;
import java.util.*;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;

public class mail extends Authenticator {
	 private static String to ="xxx";//收件人的邮箱
     private static String from="xxxx";//发件人的邮箱
     private static String password="xxx";
     public static String host="smtp.163.com";
    
    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException
    {
    	 Properties properties=System.getProperties();//获取系统属性
    	 properties.setProperty("mail.transport.protocol", "smtp");//使用协议
    	 properties.setProperty("mail.smtp.host", host);//请求认证
    	 properties.setProperty("mail.smtp.port", "465");//ssl端口
    	 properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	 properties.setProperty("mail.smtp.socketFactory.fallback", "false");
    	 properties.put("mail.smtp.auth", "true");
    	 MyAuthenticator ma=new MyAuthenticator(from,password);
         Session session=Session.getDefaultInstance(properties,ma);//获取邮件服务器
         session.setDebug(true);
         try
         {
        	 
        	 
        	 MimeMessage message = createMimeMessage(session, from, to);
        	 Transport transport = session.getTransport();//根据session获得邮件传输对象
        	 transport.connect(from, password);
        	 transport.send(message, message.getAllRecipients());
        	 transport.close();
         }catch(MessagingException mex)
         {
        	 
        	 mex.printStackTrace();
         }
        
    }
    private static MimeMessage createMimeMessage(Session session,String sendAccount,String receiveAccount) throws MessagingException, UnsupportedEncodingException{
    	MimeMessage mime = new MimeMessage(session);
    	mime.setFrom(sendAccount);
    	mime.setRecipient(RecipientType.TO, new InternetAddress(receiveAccount,"hello","UTF-8"));
    	mime.setSubject("hello","UTF-8");
    	mime.setContent("test mail", "text/html; charset=UTF-8");
    	mime.setSentDate(new Date());
    	mime.saveChanges();
    	return mime;
    	}


}
class MyAuthenticator extends javax.mail.Authenticator {
   private String strUser;
   private String strPwd;
   public MyAuthenticator(String user, String password) {
   this.strUser = user;
   this.strPwd = password;
}

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(strUser, strPwd);
     }
}



