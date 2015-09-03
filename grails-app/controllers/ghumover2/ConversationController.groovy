package ghumover2

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import static grails.async.Promises.task




class ConversationController {
	static allowedMethods = [getUserConversations	: "GET" , getSentConversations:"GET"  , newMail :"POST" , replyMsg : "POST"]
	def springSecurityService

	User user

	static responseFormats = ['json', 'xml']


	def getUserConversations()
	{
		def output = [:]
		try {

			user  =    (params.userId)? ( (params.userId.isNumber()) ? (User.findById(Long.parseLong(params.userId))) : User.findByUsername(params.userId) )   : user;


			JSON.use('msgList'){
			    def inbox = Conversation.findAllByToIdAndToFlag(user.username, true)

				output ['numberOfConversations'] = inbox.size()
				output ['conversations'] =  inbox
				render output as JSON
			}






		}
		catch (NullPointerException ne)
		{
			output['status'] = "error"
			output['message'] = "User Id "+ params.userId +" not found"
			output['data'] = "NULL"
			render output as JSON
		}
		catch (Exception e)
		{
			output['status'] = "error"
			output['message'] = "Error occured "
			output['data'] = e
			render output as JSON
		}
	}


	


	def getSentConversations()
	{
		def output = [:]
		try {

			user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null

			JSON.use('msgList'){
				def sentbox = Conversation.findAllByFromIdAndFromFlag(user.username, true)

				output ['numberOfConversations'] = sentbox.size().toString()
				output ['conversations'] =  sentbox
				render output as JSON
			}






		}
		catch (NullPointerException ne)
		{
			output['status'] = "error"
			output['message'] = "User Id "+ params.userId +" not found"
			output['data'] = "NULL"
			render output as JSON
		}
		catch (Exception e)
		{
			output['status'] = "error"
			output['message'] = "Error occured "
			output['data'] = e
			render output as JSON
		}
	}


















	def getCurUserConversations()
	{
		user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null
		if (user) {
			getUserConversations()

		}
		else
		{
			def output = [:]
			output['status'] = "error"
			output['message'] = "Not logged in"
			output['data'] = "NULL"
			render output as JSON

		}

	}


	def getConversationFromUser()
	{
		def output = [:]
		try{
			user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null
			User fromUser =  (params.userId)? ( (params.userId.isNumber()) ? (User.findById(Long.parseLong(params.userId))) : User.findByUsername(params.userId) )   : null;

	//		def conv = Conversation.findAllByFromIdAndToId(fromUser.username,user.username)
			def conv = []
			def conv1 = Conversation.findAllByFromIdAndFromFlag(fromUser.username,true)
			def conv2 = Conversation.findAllByToIdAndToFlag(user.username, true)
			conv.addAll(conv1)
			conv.addAll(conv2)
			JSON.use('msgList'){
				output ['numberOfConversations'] = conv.size()
				output ['conversations'] =  conv
				render output as JSON
			}
		}
		catch (NullPointerException ne)
		{
			output['status'] = "error"
			output['message'] = "User Id "+ params.userId +" not found"
			output['data'] = "NULL"
			render output as JSON
		}
		catch (Exception e)
		{
			output['status'] = "error"
			output['message'] = "User Id "+ params.userId +" not found"
			output['data'] = "NULL"
			render output as JSON

		}
	}





	def getConversationFromAndTo()
	{
		def output = [:]
		try{
			User toUser =  (params.toId)? ( (params.toId.isNumber()) ? (User.findById(Long.parseLong(params.toId))) : User.findByUsername(params.toId) )   : null;
			User fromUser =  (params.fromId)? ( (params.fromId.isNumber()) ? (User.findById(Long.parseLong(params.fromId))) : User.findByUsername(params.fromId) )   : null;

	//		def conv = Conversation.findAllByFromIdAndToId(fromUser.username,toUser.username)
			def conv = []
			def conv1 = Conversation.findAllByFromIdAndFromFlag(fromUser.username,true)
			def conv2 = Conversation.findAllByToIdAndToFlag(toUser.username, true)
			conv.addAll(conv1)
			conv.addAll(conv2)
			JSON.use('msgList'){
				output ['numberOfConversations'] = conv.size()
				output ['conversations'] =  conv
				render output as JSON
			}
		}
		catch (NullPointerException ne)
		{
			output['status'] = "error"
			output['message'] = "User Id "+ params.userId +" not found"
			output['data'] = "NULL"
			render output as JSON
		}
		catch (Exception e)
		{
			output['status'] = "error"
			output['message'] = "Some error occured"
			output['data'] = "NULL"
			render output as JSON

		}

	}






/*
	def saveMessage()
	{
		def output = [:]
		try{


			User toUser =  (params.toId)? ( (params.toId.isNumber()) ? (User.findById(Long.parseLong(params.toId))) : User.findByUsername(params.toId) )   : null;
			User fromUser =  (params.fromId)? ( (params.fromId.isNumber()) ? (User.findById(Long.parseLong(params.fromId))) : User.findByUsername(params.fromId) )   : null;
			Long threadId = Long.parseLong(params.threadId)
			String msg = params.messageText
			Conversation conversation = Conversation.findByThreadId(threadId)

			Message newMsg = new Message(messageText: msg , messageTime: new Date() , fromId: fromUser.username , toId: toUser.username)
			conversation.addToMessages(newMsg).save()
			output['status'] = "success"
			output['message'] = "message sent"
								   def savedMsg  = [:]
								   savedMsg['messageText'] = newMsg.messageText
								   savedMsg['messageTime'] = newMsg.messageTime
			output['data'] = savedMsg
			render output as JSON
			}

			

		catch (NullPointerException ne)
		{
			output['status'] = "error"
			output['message'] = "User Id  '"+ params.toId +"'  or  '"+params.fromId+"' not found. check Ids"
			output['data'] = "NULL"
			render output as JSON
		}
		catch (Exception e)
		{
			output['status'] = "error"
			output['message'] = "Some error occured"
			output['data'] = "NULL"
			render output as JSON

		}

	}

*/




	def newMail()
	{
		def output = [:]
		try{


			User toUser =  (params.toId)? ( (params.toId.isNumber()) ? (User.findById(Long.parseLong(params.toId))) : User.findByUsername(params.toId) )   : null;
			User fromUser =  (params.fromId)? ( (params.fromId.isNumber()) ? (User.findById(Long.parseLong(params.fromId))) : User.findByUsername(params.fromId) )   : null;
			String msg = params.messageText
			String title = params.title
			//DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z");
			//Date date = new Date();
			//sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		  //  Date today=sdf.format(date)
			Conversation conversation = new Conversation(fromId: fromUser.username , toId: toUser.username ,fromName:fromUser.name,toName:toUser.name, title: title , inTrash: false,isRead: false ,toDate: new Date(), toFlag:true, fromFlag: true )
										.addToMessages(new Message(messageText: msg , messageTime: new Date() , fromId: fromUser.name , toId: toUser.name))
										.save()


			fromUser.addToConversations(conversation).save()
			toUser.addToConversations(conversation).save()
			


			output['status'] = "success"
			output['message'] = "message sent"

			output['data'] = conversation
			test("new email from "+fromUser.username,toUser.username)
			render output as JSON
		}



		catch (NullPointerException ne)
		{
			output['status'] = "error"
			output['message'] = "User Id  '"+ params.toId +"'  or  '"+params.fromId+"' not found. check Ids"
			output['data'] = "NULL"
			render output as JSON
		}
		catch (Exception e)
		{
			output['status'] = "error"
			output['message'] = "Some error occured"
			output['data'] = "NULL"
			render e as JSON

		}

	}
	
	


	def test(String msg1,String username){
	try{
		String tag="check"
		def req2=[:]
		req2["type"]=="mail_box"
		def req=[:]
		req["sound"]="ding"
		req["platform"] = ["0", "1"]
		req["tags"]=[username] 
		req["msg"]=msg1
		req["payload"]=req2
		req["badge"]="187192"
		
	
			def p=  //tagList.collect{ z ->
		   task {
				def rest = new RestBuilder()
			 def resp = rest.post("https://api.pushbots.com/push/all"){
			 header 'x-pushbots-appid', '550e9e371d0ab1de488b4569'
			 header 'x-pushbots-secret', 'e68461d7755b0d3733b4b36717aea77d'
		  header 'Content-Type', 'application/json'
		  json req
			  
		  }
			 System.out.print("resp val : "+resp.json)
			  }
			  
	}
		catch(Exception e){
		}
		   
			
		}
	












	def replyMsg()
	 {
		 def output = [:]
		 try {

			 Long threadId = Long.parseLong(params.threadId)

			 String messageText = params.messageText
			 String fromId = params.fromId
			 String toId = params.toId

			 if(new Message(threadId: threadId , messageText: messageText , fromId: fromId , toId: toId , messageTime: new Date() ).save())
			 {
				 output['status'] = "success"
				 output['message'] = "Message sent"
				 output['data'] = messageText
				 render output as JSON
			 }
			 else
			 {
				 output['status'] = "failed"
				 output['message'] = "Message not sent"
				 output['data'] = messageText
				 render output as JSON
			 }

		 }
		 catch (Exception e)
		 {
			 render e
		 }

	}

	 def getCurUserConversationsV1()
	 {
		 def output = [:]
		 user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null
		 if (user) {
			 user  =    (params.userId)? ( (params.userId.isNumber()) ? (User.findById(Long.parseLong(params.userId))) : User.findByUsername(params.userId) )   : user;
 
 
			 JSON.use('conversationVer1'){
				 def inbox = Conversation.findAllByToIdAndToFlag(user.username, true)
 
				 output ['numberOfConversations'] = inbox.size()
				 output ['conversations'] =  inbox
				 render output as JSON
			 }
 
 
		 }
		 else
		 {
 
			 output['status'] = "error"
			 output['message'] = "Not logged in"
			 output['data'] = "NULL"
			 render output as JSON
 
		 }
 
	 }
	 
	/* def deleteConversation(){
		 def con = Conversation.findAllByToFlagAndFromFlag(false,false)
		 Iterator itr = con.iterator()
		 while(itr.hasNext()){
			 Conversation c = itr.next()
			long tId = c.threadId
			String fU = c.fromId
			String tU = c.toId
			User fromUser = User.findByUsername(fU)
			User toUser = User.findByUsername(tU)
			toUser.removeFromConversations(con)
			fromUser.removeFromConversations(con)
			Message.findAllByThreadId(con).each {
				long mId = it.messageId
				Message.executeUpdate("Alter Message delete row where messageId = msgToDel",[msgToDel :mId])
			}
			Conversation.executeUpdate("Alter Conversation delete row where threadId = convToDel",[convToDel :tId])
		 }
		 user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null ;
		 String uId=user.username;
		 String toId, fromId
		  Long thId = Long.parseLong(params.threadId)
		  Conversation.findByThreadId(thId).each{
			 toId= it.toId
			 fromId= it.fromId
		  }
		  if(uId.equalsIgnoreCase(toId)){
   			   Conversation.executeUpdate("UPDATE Conversation SET toFlag=false WHERE threadId = :convToDelete ",[convToDelete : thId]).save()
			  
		  }else if(uId.equalsIgnoreCase(fromId)){
			   Conversation.executeUpdate("UPDATE Conversation SET fromFlag=false WHERE threadId = :convToDelete ",[convToDelete : thId]).save()
		  }
		  
		 
		  
	 }*/
	 def deleteConversation(){
		 user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null ;
		String uId=user.username;
		String toId, fromId
		 Long thId = Long.parseLong(params.threadId)
		 try{
			 Conversation.findByThreadId(thId).each{
			 toId= it.toId
			 fromId= it.fromId
				 }
			 if(uId.equalsIgnoreCase(toId)){
				 Conversation.executeUpdate("UPDATE Conversation SET toFlag=false WHERE threadId = :convToDelete ",[convToDelete : thId])
			 
			 }else if(uId.equalsIgnoreCase(fromId)){
			  Conversation.executeUpdate("UPDATE Conversation SET fromFlag=false WHERE threadId = :convToDelete ",[convToDelete : thId])
			 }
		 
			 def con = Conversation.findAllByToFlagOrFromFlag(false,false)
			 Iterator itr = con.iterator()
			 while(itr.hasNext()){
				 Conversation c = itr.next()
				 long tId = c.threadId
				 def convMsgz = c.messages
			
				 String fUser = c.fromId
				 String tUser = c.toId
				 User fromUser = User.findByUsername(fUser)
				 User toUser = User.findByUsername(tUser)
				 def convFrom = fromUser.conversations.findAll{it.fromFlag==false}
				 def convTo = toUser.conversations.findAll{it.toFlag==false}
				 fromUser.conversations.removeAll(convFrom)
				 toUser.conversations.removeAll(convTo)
				 if( c.toFlag==false && c.fromFlag==false){
					 Iterator itt = convMsgz.iterator()
					 while(itt.hasNext()){
						 Message msz = itt.next()
						 long mszId= msz.messageId
						 Message.executeUpdate("Delete Message where messageId ="+ mszId +" ")
						 }
				 
				 Conversation.executeUpdate("Delete Conversation where threadId ="+ tId +" ")
				 }
			 }
		 }catch(Exception ex){
			 ex.printStackTrace()
			 }
		 
	}
	 
 

}
