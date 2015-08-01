package ghumover2

import grails.async.Promise
import static grails.async.Promises.task
import grails.converters.JSON
import grails.rest.RestfulController
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonBuilder

import java.sql.Array
import java.text.SimpleDateFormat;
import java.util.concurrent.Callable
import java.util.concurrent.Executors;
import java.util.concurrent.Future


@Secured(['ROLE_TEACHER','ROLE_PARENT'])
class HomeworkController extends RestfulController
{
	static allowedMethods = [saveHomework: "POST"]
	static responseFormats = ['json', 'xml']
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	HomeworkController() {
		super(Homework)
	}

	def getStudentHomeworkByDate()
	{
 
		try{
 
 
 
			def student =  Student.findByStudentId(Long.parseLong(params.studentId))
			Date date = formatter.parse(params.dateAssigned)
			def grade   =    student.grade
			def output  = [:]
			output['StudentId'] = student.studentId
			output['studentName'] = student.studentName
 
			JSON.use('studentHomework')
					{
						def homeworks = Homework.findAll("from  Homework as h where ((h.grade = ? and h.gradeFlag = 'g') or h.student = ?) and h.dateCreated = ?  order by h.dateCreated desc ", [grade, student,date])
						output['number_of_homeworks'] = homeworks.size()
						output['homeworks'] = homeworks
						render output as JSON
					}
 
		}
		catch (Exception e)
		{
			render e as JSON
		}
 
	}

   def getClassHomework()
   {
			def gradeName = Integer.parseInt(params.gradeId)
			def section = params.section
			def grade = Grade.findByNameAndSection(gradeName,section)
			def response = Homework.findAllByGradeAndDateCreated(grade,params.dateAssigned)
			render response as JSON

   }

   def getClassHomeworkBySubject()
   {
	   def gradeName = Integer.parseInt(params.gradeId)
	   def section = params.section

	   def subject = params.subject
	   def grade = Grade.findByNameAndSection(gradeName,section)
	   def response = Homework.findAllByGradeAndSubjectAndDateCreated(grade,subject,params.dateAssigned)
	   render response as JSON
   }
   
   def test(String msg1){
	   
		
		def rest = new RestBuilder()
		def resp = rest.post("https://api.pushbots.com/push/all"){
			header 'x-pushbots-appid', '550e9e371d0ab1de488b4569'
			header 'x-pushbots-secret', 'e68461d7755b0d3733b4b36717aea77d'
	json {
		  sound = "ding"
		 platform = ["0", "1"]
		alias= ""
	tags= tagList
	msg= msg1
	except_active= []
	 except_tags= []
	  badge= "187192"
	payload={
		type="homework"
	}
	active= []
	}
	  
	}
		
		
		   System.out.print("resp val : "+resp.json)
	   }
   
   
   def sendNotification(){
	   
	   
	   def myClosure = {alias -> notifyUser(alias)}
	   def threadPool = Executors.newFixedThreadPool(4)
		try {
		 List<Future> futures = (tagList).collect{alias->
		   threadPool.submit({->
		   myClosure alias } as Callable);
		 }
		 // recommended to use following statement to ensure the execution of all tasks.
		 futures.each{it.get()}
	   }finally {
		 threadPool.shutdown()
	   }
   }
  
   
   def sendNotfication(String msg1){
	 def p=  tagList.collect{ z ->
	task {
		 def rest = new RestBuilder()
	   def resp = rest.post("https://api.pushbots.com/push/one"){
		   header 'x-pushbots-appid', '550e9e371d0ab1de488b4569'
		   header 'x-pushbots-secret', 'e68461d7755b0d3733b4b36717aea77d'
		  header 'Content-Type', 'application/json'
   json {
		platform =  "1"
	   token= z
	   msg= msg1
	   sound = "ding"
	 badge= "187192"
   payload={
	   msg="homework"
   }
	   
   }
	   }
	   System.out.print("resp val : "+resp.json)
	
		   
	   }
	//return "success"
	}
	 
	   
   
   }
  
   
   def testNotification(String msg1,def tagsM){
	   
		
		def rest = new RestBuilder()
		def resp = rest.post("https://api.pushbots.com/push/all"){
			   header 'x-pushbots-appid', '550e9e371d0ab1de488b4569'
		   header 'x-pushbots-secret', 'e68461d7755b0d3733b4b36717aea77d'
			json tagsM
	  
	}
		
		
		   System.out.print("resp val : "+resp.json)
	   }
   
def tagsM
   ArrayList<String> tagList =[]
   String homeWorkMsg
	def saveHomework() {
		tagList =[]
		try {

			def gradeFlag = params.gradeFlag

			Grade grade = Grade.findByNameAndSection(params.grade, params.section)
			def subject = params.subject
			Date date = formatter.parse(params.dueDate);
			Student tempStudent

			def output = [:]
			def data = []
			
			
			 if (gradeFlag == 's') {
				params.studentList.each { studentId ->
					tempStudent = Student.get(studentId)
					data << new Homework(grade: grade, subject: subject, homework: params.homework, student: tempStudent, message: params.message, dueDate: date, gradeFlag: "s").save(flush: true)
					if(tempStudent!=null&&tempStudent.getFather()!=null)
					if(tempStudent.getFather().username!=null){
					tagList<<tempStudent.getFather().username
					}
					if(tempStudent.getMother().username!=null){
					tagList<<tempStudent.getMother().username
					}
					tagList<<"check"
					
				}
				output['status'] = 'success'
				output['message'] = 'Homework details for ' + data.size() + ' students successfully stored'
				output['data'] = data
				
				homeWorkMsg ="Homework for class "+ params.grade+ " added"
				
			} else if (gradeFlag == 'g') {
			
		
				data << new Homework(grade: grade, subject: subject, homework: params.homework, message: params.message, dueDate: date, gradeFlag: "g").save(flush: true)
				output['status'] = 'success'
				output['message'] = 'Homework details for class  successfully stored'
				output['data'] = data
				
				
				//if(grade.gradetags!=null){
					//tagList<<grade.gradetags
				//}else{
				grade.students.each{
						tagList<<it.getFather().username
							tagList<<it.getMother().username
				
				}
				//calling code
				
				homeWorkMsg ="Homework for class "+ params.grade+ " added"
				

			} else {
				output = [status: "error", message: "invalid gradeflag '" + gradeFlag + "'", data: "NULL"]
				// render output as JSON
			}
		
			
			if(tagList!=null&& tagList.size>0){
			 String [] tagi=tagList as Array
			 String tag="check"
			JsonBuilder json = new JsonBuilder ()
			def req2=[:]
			req2["msg"]="homework"
			def req=[:]
			req["sound"]="ding"
			req["platform"] = ["0", "1"]
			req["tags"]= tagi
			req["msg"]=homeWorkMsg
			req["payload"]=req2
		
	  
		  

				testNotification(homeWorkMsg,req)
		}
			
			render output as JSON
		}
		catch (Exception e) {
			render e
		}


	}








	  def getStudentHomework()
			 {
				  try{



					  def student =  Student.findByStudentId(Long.parseLong(params.studentId))
					  def grade   =    student.grade
					  def output  = [:]
					  output['StudentId'] = student.studentId
					  output['studentName'] = student.studentName

					 JSON.use('studentHomework')
							 {
								 def homeworks = Homework.findAll("from  Homework as h where (h.grade = ? and h.gradeFlag = 'g') or h.student = ? order by h.dateCreated desc ", [grade, student])
								 output['number_of_homeworks'] = homeworks.size()
								 output['homeworks'] = homeworks
								 render output as JSON
							 }

				  }
				  catch (Exception e)
				  {
					  render e as JSON
				  }

			 }
			 
			 





}