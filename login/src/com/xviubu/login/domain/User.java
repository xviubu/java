package com.xviubu.login.domain;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
public class User
{
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}
	public  void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	public boolean isActive()
	{
		return active;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}

	public void activate()
	{
		if(active == true)
		{
			return;
		}
		
		active = true;
		sendActivationEmail();
	}

	private void sendActivationEmail()
	{
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.host","localhost");

			Session mailConnection = Session.getInstance(props,null);
			Message msg  = new MimeMessage(mailConnection);

			Address from  = new InternetAddress("root@JJZZ.com");
			Address to = new InternetAddress(this.getEmail());

			msg.setContent("This is an activate mail from www.JJZZ.com ,Congratulations ,You are our member now !","text/plain");
			msg.setFrom(from);
			msg.setRecipient(Message.RecipientType.TO,to);
			msg.setSubject("Congratulations !"+this.getUsername());

			Transport.send(msg);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void sendForgetEmail()
	{
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.host","localhost");

			Session mailConnection = Session.getInstance(props,null);
			Message msg  = new MimeMessage(mailConnection);

			Address from  = new InternetAddress("root@JJZZ.com");
			Address to = new InternetAddress(this.getEmail());

			String emailContent ="This is a find password email from JJZZ.com " + "<a href='http://localhost:8080/JJZZ/user/save?" + 
				"username="+this.getUsername() + "&email=" + this.getEmail()+"'>Click here to reset password </a>" ;
			msg.setContent(emailContent,"text/html");
			msg.setFrom(from);
			msg.setRecipient(Message.RecipientType.TO,to);
			msg.setSubject("Warning "+this.getUsername());

			Transport.send(msg);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public String toString()
	{
		return "Id = " + this.getId() +" Username = " + this.getUsername() + " Password = " + this.getPassword() + " Email = " + this.getEmail() + " isActive = " + this.isActive() ;
	}

	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	private MailSender mailSender;
	private int id;

	@Size(min=3,max=20,message="Username must be between 3 and 20 characters.")
  	@Pattern(regexp="^[a-zA-Z0-9]+$",message="Username must be alphanumeric with no spaces")  
	private String username;

  	@Size(min=6, max=20,message="The password must be at least 6 characters long.") 
	private String password;
  @Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", 
          message="Invalid email address.") 
	private String email;
	private boolean active = false;
}
