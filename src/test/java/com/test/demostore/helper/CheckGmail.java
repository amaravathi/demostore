package com.test.demostore.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;


public class CheckGmail implements CheckEmail {
	private static Session session;
	private static Properties props;
	private boolean textIsHtml = false;
	boolean val = false;
	
	public CheckGmail() {
		
	}
	static {
		props = System.getProperties();
		props.setProperty("mail.store.protocol", SeleniumConfig.userEmailClient);
		props.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback", "false");
		
		session = Session.getDefaultInstance(props, null);
	}
	
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
	private Message[] getRegEmailMessages(Folder[] folderList ) throws MessagingException, InterruptedException {
		Message[] messages = null;
		
		for( Folder folder : folderList) {
        	if (folder.getName().compareToIgnoreCase(SeleniumConfig.userSpamName) == 0 ||
        			folder.getName().compareToIgnoreCase(SeleniumConfig.userInboxName) == 0) {
        	folder.open(Folder.READ_ONLY);
        	Log.info("Searching Folder : "+ folder.getName());
        	for (int i = 0; i < 5; i++) {
        		messages = folder.search(
            		new SubjectTerm(SeleniumConfig.useRegisterEmailSubject),folder.getMessages());
                //Wait for 10 seconds
                if (messages.length == 0) {
                    Thread.sleep(10000);
                }
        	}
         }
        	else
        		continue;
        }
		return messages;
	}
	
	@Override
	public void SearchEmail(String userName, String userPwd, String fromEmailId, String subject) throws Exception {
		           
            Store store = session.getStore(SeleniumConfig.userEmailClient);
            store.connect(SeleniumConfig.userEmailStore, SeleniumConfig.gmailUserId, SeleniumConfig.gmailUserPwd);
            
            Folder[] folderList = store.getDefaultFolder().list("*");
            		            
            Message[] messages = getRegEmailMessages(folderList);
            boolean isMailFound = false;
            Message regEmail = null;
            
            //Search for unread mail 
            //This is to avoid using the mail for which 
            //Registration is already done
            for (Message storeEmail : messages) {
                if (!storeEmail.isSet(Flags.Flag.SEEN)) {
                	regEmail = storeEmail;
                    System.out.println("Message Count is: "+ regEmail.getMessageNumber());
                    isMailFound = true;
                }
            }
            if (!isMailFound) {
                throw new Exception(
                        "Not Found Email regarding user Registration process");
            
            //Read the content of mail and launch registration URL                
            } else {
                String line;
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(regEmail.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                System.out.println(buffer);
                Log.info("Registration Email Content : " + buffer);
                          
          }
   }
	}        
	

