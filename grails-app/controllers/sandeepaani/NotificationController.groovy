package sandeepaani

import ghumover2.User;
import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonBuilder

import java.lang.reflect.Array
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NotificationController {

    def index() { }
	
	public void sentNotificationToSchool(){
		ExecutorService ex=Executors.newFixedThreadPool(20)
		
		List<User> users=User.findAll()
		
		List<String> tagList
		String msg1
		
		List<List<String>> tags=new ArrayList<String>()
		for(User user:users){
			int i=0
		
	while(i<20){
				tagList<<user.username
				i++
				if(i==20){
					tags.add(tagList)
					tagList=new ArrayList<String>()
				}
				
	}	
		}	
	
	for(List tag:tags){
		NotifyThread nt =new  NotifyThread( msg1, tag)
	  ex.submit(nt)
			
	}

			
			
		
	}
	
	
	
	
	
	public void sentNotificationToParents(){
	
}
	
	public void sentNotificationToTeachers(){
	
}
	
	 class NotifyThread implements Runnable{
		 String msg
		 def tagList
		 NotifyThread(String msg1,def tagList){
			 msg=msg1
			 this.tagList=tagList
		 }
		 
		@Override
		public void run() {
			testNotification(msg,tagList)
			
		}
		 
	 }
	 
	 def testNotification(String msg1,def tagList){
		 
		 if(tagList!=null&& tagList.size>0){
			 String [] tagi=tagList as Array
			 String tag="check"
			JsonBuilder json = new JsonBuilder ()
			def req2=[:]
			req2["msg"]="notice"
			def req=[:]
			req["sound"]="ding"
			req["platform"] = ["0", "1"]
			req["tags"]= tagi
			req["msg"]=msg1
			req["payload"]=req2
		
	  
		  

		
		  def rest = new RestBuilder()
		  def resp = rest.post("https://api.pushbots.com/push/all"){
				 header 'x-pushbots-appid', '550e9e371d0ab1de488b4569'
			 header 'x-pushbots-secret', 'e68461d7755b0d3733b4b36717aea77d'
			  json tagsM
		
	  }
}

}}