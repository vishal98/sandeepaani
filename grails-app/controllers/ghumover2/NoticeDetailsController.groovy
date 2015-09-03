package ghumover2

import grails.converters.JSON

class NoticeDetailsController {
	static responseFormats = ['json', 'xml']
	def springSecurityService
	User user

	def index() { }
	def getnotices(){
		
		def ntc=new Notice()
		ntc.setNoticeId(1)
		ntc.setDate(new Date())
		ntc.setDay("Monday")
		ntc.setDesignation("Class Teacher 	")
		ntc.setMessage("Welcome to a new school year. I am so excited to have your child in my class this year. I would like to get to know a little more about your child and what I can do to help your child have a meaningful year. Please answer the following questions and return to me as soon as possible. Your response is very important to me . Together we can make a team. I am looking forward to a great year. ")
		ntc.setSalutation("Dear Parents ")
		ntc.setSignoffWord("Yours Sincerly")
		ntc.setTopic("Notice from class Teacher")
		ntc.setSenderName(" ")
		ntc.setCreateBy("Principle")
		ntc.setClassId(0)
		
		try{
			def noticesList = [] ;
			noticesList.add(ntc);
			user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null ;
			long uId=user.id;
			
			def r = UserRole.findByUser(user).role ;
			String uRole = r.getAuthority();
			def schoolNotice = Notice.findAllByClassId(0);
			noticesList.addAll(schoolNotice);
			if(uRole.equalsIgnoreCase("ROLE_PARENT")){
				Guardian g = Guardian.findByUsername(user.username) ;
				Student stu = GuardianChildren.findByGuardian(g).getStudent() ;
				long sId = stu.studentId ;
				Grade gr = stu.grade ;
				long stuClassId = stu.grade.gradeId ;  //Student.findByStudentId(sId).gradeId ;
				def classNotice = Notice.findAllByClassId(stuClassId) ;
				if(classNotice != null){
					noticesList.addAll(classNotice) ;
					}
				
			}
			else if(uRole.equalsIgnoreCase("ROLE_TEACHER")){
				Teacher teach = Teacher.findByUsername(user.username) ;
				def tGrades =  GradeTeacherSubject.findAllByTeacher(teach).collect {it.grade}
				def tNotices = [];
				tGrades.each {
					long gId = it.gradeId;
					def tGradeNotice = Notice.findAllByClassId(gId);
					if(tGradeNotice != null){
						tNotices.addAll(tGradeNotice);
						}
					}
				if(tNotices != null){
					noticesList.addAll(tNotices);
					}
				
			}
			else if(uRole.equalsIgnoreCase("ROLE_ADMIN")){
				def admNotices = Notice.getAll();
				admNotices.removeAll(noticesList);
				if(admNotices != null){
					noticesList.addAll(admNotices);
					}
				
			}
				
			def output = [:]
		
		
		
			output['Notices'] = noticesList.collect{[
				'noticeId': it.noticeId.toString(),
				  'topic':it.getTopic(),
				  'salutation':it.getSalutation(),
				  'message':it.getMessage(),
				  'senderName':it.getSenderName(),
				  'classId':it.getClassId(),
				  'signOff':it.signoffWord,
				  'designation':it.getDesignation(),
				  'day':it.getDay(),
				  'date':it.getDate()? it.getDate().format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") :'date not' ]}
		
		render output as JSON
	
	
		}catch(Exception e){ e.printStackTrace();}
	}


}
