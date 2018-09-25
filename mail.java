package javamail;
import java.util.*;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;

public class mail extends Authenticator {
	 private static String to ="xxx";//�ռ��˵�����
     private static String from="xxxx";//�����˵�����
     private static String password="xxx";
     public static String host="smtp.163.com";
    
    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException
    {
    	 Properties properties=System.getProperties();//��ȡϵͳ����
    	 properties.setProperty("mail.transport.protocol", "smtp");//ʹ��Э��
    	 properties.setProperty("mail.smtp.host", host);//������֤
    	 properties.setProperty("mail.smtp.port", "465");//ssl�˿�
    	 properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	 properties.setProperty("mail.smtp.socketFactory.fallback", "false");
    	 properties.put("mail.smtp.auth", "true");
    	 MyAuthenticator ma=new MyAuthenticator(from,password);
         Session session=Session.getDefaultInstance(properties,ma);//��ȡ�ʼ�������
         session.setDebug(true);
         try
         {
        	 
        	 
        	 MimeMessage message = createMimeMessage(session, from, to);
        	 Transport transport = session.getTransport();//����session����ʼ��������
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



