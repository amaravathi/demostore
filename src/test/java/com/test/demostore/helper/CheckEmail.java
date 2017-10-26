package com.test.demostore.helper;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;


public class CheckEmail {
	public static void main( String[] args) throws Exception {
		

				        Properties props = System.getProperties();
				        //props.setProperty("mail.imap.ssl.enable", "true");
				        props.setProperty("mail.store.protocol", "imaps");
				        /*props.setProperty("https.proxyHost", "192.168.192.210");
			            props.setProperty("https.proxyPort", "8080");
			            props.setProperty("http.proxyHost", "192.168.192.210");
			            props.setProperty("http.proxyPort", "8080");*/


		            Session session = Session.getDefaultInstance(props, null);
		            Store store = session.getStore("imaps");
		            store.connect("imap.gmail.com", "store.test.demo.1@gmail.com", "Test@123");
		            
		            
		            store.getPersonalNamespaces();
		            System.out.println("Folder List : "+ store.getDefaultFolder().list("*") );
		            
		            
		            Folder[] folderList = store.getDefaultFolder().list("*");
		            
		            for( Folder folder : folderList) {
		            	System.out.println("Folder Name : "+ folder.getName());
		            }
		            
		         /*//   Folder folder = store.getFolder("Inbox");
		            folder.open(Folder.READ_ONLY);

		            System.out.println("Total Message:" + folder.getMessageCount());
		            System.out.println("Unread Message:"
		                    + folder.getUnreadMessageCount());*/
		            
		            Message[] messages = null;
		            boolean isMailFound = false;
		            Message mailFromGod= null;

		            for( Folder folder : folderList) {
		            	if (folder.getName().compareToIgnoreCase("Spam") == 0 ||
		            			folder.getName().compareToIgnoreCase("Inbox") == 0) {
		            	folder.open(Folder.READ_ONLY);
		            	System.out.println("Current Folder : "+ folder.getName());
		            	for (int i = 0; i < 5; i++) {
		            		messages = folder.search(
		                		new SubjectTerm("[ONLINE STORE] Your username and password info"),folder.getMessages());
			                //Wait for 10 seconds
			                if (messages.length == 0) {
			                    Thread.sleep(10000);
			                }
		            	}
		             }
		            	else
		            		continue;
		            }
		            //Search for unread mail from God
		            //This is to avoid using the mail for which 
		            //Registration is already done
		            for (Message mail : messages) {
		                if (!mail.isSet(Flags.Flag.SEEN)) {
		                    mailFromGod = mail;
		                    System.out.println("Message Count is: "
		                            + mailFromGod.getMessageNumber());
		                    isMailFound = true;
		                }
		            }

		            //Test fails if no unread mail was found from God
		            if (!isMailFound) {
		                throw new Exception(
		                        "Could not find new mail from God :-(");
		            
		            //Read the content of mail and launch registration URL                
		            } else {
		                String line;
		                StringBuffer buffer = new StringBuffer();
		                BufferedReader reader = new BufferedReader(
		                        new InputStreamReader(mailFromGod
		                                .getInputStream()));
		                while ((line = reader.readLine()) != null) {
		                    buffer.append(line);
		                }
		                System.out.println(buffer);

		                //Your logic to split the message and get the Registration URL goes here
		                /*String registrationURL = buffer.toString().split("&amp;gt;http://www.god.de/members/?")[0]
		                        .split("href=")[1];
		                System.out.println(registrationURL);*/                            
		            }
		    }
		}        
	
/*
* Copyright @ QA All rights Reserved.
* CONFIDENTIAL. Use is subject to license terms.
* @version 1.0, May 26, 2014
* @author Vishwas Kumar
*
* Description: This piece of code will test the Gmail Account for Email verification with the help of Javax API
* This is the scenario which will be used to test the gmail functionality in the Overall Application
*
* This Code/Scenario will be able to test if Email received in Gmail with specific Email Address, Subject line, Some search in the Body to Test with in 3 minutes of time.
*
*/

/*import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;
public class CheckEmail {
    *//**
* Searches for e-mail messages containing the specified keyword in
* Subject field.
* @param host
* @param port
* @param userName
* @param password
* @param keyword
* @throws IOException
*//*
@SuppressWarnings("unused")
private boolean textIsHtml = false;
    *//**
* Return the primary text content of the message.
*//*
    private String getText(Part p) throws MessagingException,IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }
        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }
        return null;
    }
    public boolean searchEmail(String userName,String password, final String subjectKeyword, final String fromEmail,
    		final String bodySearchText) throws IOException {
        Properties properties = new Properties();
        boolean val = false;
        // server setting
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", 993);
        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port",String.valueOf(993));
        Session session = Session.getDefaultInstance(properties);
        
        //SubjectTerm subTerm = new SubjectTerm("");
        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);
            System.out.println("Connected to Email server….");
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);
            //create a search term for all "unseen" messages
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, true);
            //create a search term for all recent messages
            Flags recent = new Flags(Flags.Flag.RECENT);
            FlagTerm recentFlagTerm = new FlagTerm(recent, false);
            SearchTerm searchTerm = new OrTerm(unseenFlagTerm,recentFlagTerm);
            
            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchTerm);
            System.out.println("Total Messages Found :"+foundMessages.length);
            for (int i= foundMessages.length-1; i >= foundMessages.length-10; i--) {            
                    Message message = foundMessages[i];
                    Address[] froms = message.getFrom();
			String email = froms == null ? null : ((InternetAddress)froms[0]).getAddress();
			if(message.getSubject()==null){
			continue;
			}
			Date date = new Date();//Getting Present date from the system
			long diff = date.getTime()-message.getReceivedDate().getTime();//Get The difference between two dates
			long diffMinutes = diff / (60 * 1000) % 60; //Fetching the difference of minute
			// if(diffMinutes>2){
			// diffMinutes=2;
			// }
			System.out.println("Difference in Minutes b/w present time & Email Recieved time :" +diffMinutes);
			try {
			if(message.getSubject().contains(subjectKeyword) &&email.equals(fromEmail) && getText(message).contains(bodySearchText) && diffMinutes<=3){
			String subject = message.getSubject();
			// System.out.println(getText(message));
			System.out.println("Found message #" + i + ": ");
			System.out.println("At "+ i + " :"+ "Subject:"+ subject);
			System.out.println("From: "+ email +" on : "+message.getReceivedDate());
			if(getText(message).contains(bodySearchText)== true){
			System.out.println("Message contains the search text "+bodySearchText);
			val=true;
			}
			else{
			val=false;
			}
			break;
			}
			} catch (NullPointerException expected) {
			// TODO Auto-generated catch block
			expected.printStackTrace();
			}
			System.out.println("Searching.…" +"At "+ i );
            }
            // disconnect
            folderInbox.close(false);
            store.close();
	        } catch (NoSuchProviderException ex) {
	            System.out.println("No provider.");
	            ex.printStackTrace();
	        } catch (MessagingException ex) {
	            System.out.println("Could not connect to the message store.");
	            ex.printStackTrace();
	        }
        	return val;
    }
    *//**
* Test this program with a Gmail’s account
* @throws IOException
*//*
    public static void main(String[] args) throws IOException {
        String userName = "qaautomation@gmail.com";
        String password = "passwrd";
        CheckEmail searcher = new CheckEmail();
        String subjectKeyword = "New  Registration – Successful";
        String fromEmail="senderqa@google.com";
        String bodySearchText ="You have successfully register to our services";
        searcher.searchEmail(userName, password,subjectKeyword,fromEmail, bodySearchText);
    }
}

*/