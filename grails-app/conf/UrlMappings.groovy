class UrlMappings {
	
		static mappings = {
			"/$controller/$action?/$id?(.$format)?"{
				constraints {
					// apply constraints here
		}
			}

			"/"(view:"/index")
			"500"(view:'/error')
"/Parent/username/$username"{
					controller = "parent"
					action = "getParentDetails"
			}

			// TIME TABLE
			"/app/timetable/$gradeId/$section"
					{
						controller = "TimeTableDetails"
						action = "getWeekTimetable"

					}

			"/app/timetable/$gradeId/$section/$day"
					{
						controller = "TimeTableDetails"
						action =  "getDayTimeTable"
					}


					"/app/conversations/get/v1"
					{
						controller = "conversation"
						action = "getCurUserConversationsV1"
	
					}
	
	
	
		"/app/teacher/timetable/week/v1"
					{
						controller = "TeacherDetails"
						action = "getTeacherWeekTimetableVer1"
					}
		"/app/admin/timetable/update"(controller: "TimeTableDetails" , action: "updateTimeTable" , parseRequest: true);
		"/app/admin/timetable/delete"(controller: "TimeTableDetails" , action: "deleteTimeTable" , parseRequest: true);




			// PARENT ACCOUNT DETAILS
			"/app/parent/accountInfo/$id"
					{
						controller = "Guardian"
						action = "getAccountInfo"
					}
			"/app/parent/$id/getChildren"
					{
						controller = "Guardian"
						action = "getAllChildren"

					}


			"/Parent/exam/$classid"{
				controller = "parent"
				action = "getExamDetails"
			}

			"/Parent/child/schedule/$examId"{
				controller = "parent"
				action = "getExamSchedule"
			}





			//HOMEWORK

			"/app/getHomework/student/$studentId"
					{

						controller = "Homework"
						action = "getStudentHomework"
					}




	
		  
					"/app/getHomework/student/$studentId/$dateAssigned"

					{

						controller = "Homework"
						action = "getStudentHomeworkByDate"
					}

			"/app/teacher/homework/save"(controller: "Homework", action: "saveHomework" ,parseRequest: true)
			
			


			//TEACHER

		

	"/Teacher/studentList/$gradeId"{
					controller = "teacherDetails"
					action = "getStudentList"
			}

			"/Teacher/$studentList/$grade/$section"
					  {
						  controller = "teacherDetails"
						  action = "getStudentListByGradeSection"
					  }
			"/Teacher/id/$userId"{
				controller = "teacherDetails"
				action = "getTeacherDetails"
			}
			"/app/subject/$grade/$section"{
				controller = "teacherDetails"
				action = "getSubject"
			}


		
	"/app/teacher/getAllSubjectsInAllGrades"
					{
						controller = "teacherDetails"
						action = "getAllSubjectsInAllGrades"
					}

	




		"/app/teacher/sendMail/$grade/$section"(controller: "teacherDetails", action: "sendMailToParents" ,parseRequest: true)
		"/app/admin/addTeacher"(controller: "teacherDetails", action: "addTeacher" ,parseRequest: true)
		

			//apis for conversations


			"/app/conversations/get"
					{
						controller = "conversation"
						action = "getCurUserConversations"

					}
					
					"/app/conversations/get/v1"
					{
						controller = "conversation"
						action = "getUserConversationsUpdated"

					}
					


				 "/app/conversations/get/$userId"
						 {
							 controller = "conversation"
							 action = "getUserConversations"
						 }


				"/app/conversations/getFrom/$userId"
						{
								controller = "conversation"
								action = "getConversationFromUser"

						}

			"/app/conversations/getFromTo/$fromId/$toId"
					{
						controller = "conversation"
						action = "getConversationFromAndTo"

					}

			 "/app/conversations/new"(controller: "conversation", action: "newMail" ,parseRequest: true)


			"/app/conversations/reply"(controller: "conversation" , action: "replyMsg" , parseRequest: true)



			"/app/conversations/sent"
			{
				controller = "conversation"
				action = "getSentConversations"

			}

		   //Get user list

			"/app/parent/getUsers/$studentId"
					{
						controller = "guardian"
						action = "getTeacherList"
					}


					
					
				
	//Attendance
					
						

	"/app/attendance/grade/$grade/$section/$date"
									{
					
										controller = "attendance"
										action = "getGradeAttendance"
					
									}
									
									"/app/attendance/grade/v1/$grade/$section/$date"
									{
					
										controller = "attendance"
										action = "getGradeAttendanceV1"
					
									}
							"/app/teacher/attendance/save"(controller: "attendance" , action: "saveAttendance" , parseRequest: true)
					
					
						   "/app/attendance/student/$studentId/from/$fromDate/to/$toDate"
								   {
									   controller = "attendance"
									   action = "getStudentAttendanceBetween"
					
								   }
					
							"/app/attendance/student/$studentId/month/$month/$year"
									{
										 controller = "attendance"
										action = "getAttendanceOfMonth "
									}
						

			
			
	
















	





	"/app/teacher/timetable/"
			{
				controller = "TimeTableDetails"
				action = "getTeacherWeekTimetable"
			}
	       "/app/teacher/timetable/$day"
			 {
				 controller = "TimeTableDetails"
				 action = "getTeacherDayTimetable"

			 }



			//Student list Api

	/*  "/app/get/students/$grade/$section"
			  {
				  controller = "student"
				  action = "getStudentsOfClass"

			  }
*/

			 
			 "/app/get/students/$grade/$section"
					 {
						 controller = "StudentDetails"
						 action = "getStudentsOfClass"
	  
		 }

			//mail pendings

			"/app/conversations/sent"
					{
						controller = "conversation"
						action = "getSentConversations"

					}




			//  Teacher events

			 "/app/events/teacher/$month/$year"
					 {
						 controller = "teacherDetails"
						 action = "getTeacherMonthEvents"

					 }
			"/app/events/teacher/$date"
					{
						controller = "teacherDetails"
						action = "getTeacherEvents"

					}

            // student events
             "/app/events/student/$studentId/$month/$year"
					 {

						 controller = "guardian"
						 action = "getStudentMonthEvents"

					 }

			"/app/events/student/$studentId/$date"
					{
						controller = "guardian"
						action = "getStudentClassEvents"

					}
					
					"/app/teacher/timetable/week"
					{
						controller = "teacherDetails"
						action = "getTeacherWeekTimetable"
					}

					


			/*
			Admin Apis
			 */

			"/app/admin/grades"(resources:"grade" ,  includes:['index', 'show' , 'save' , 'update' , 'delete','create','patch'])

			"/app/admin/teachers"(resources:"teacher" , includes:['index', 'show' , 'save' , 'update' , 'delete','create','patch'])
			"/app/admin/students"(resources: "student", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
			
			"/app/admin/addresses"(resources: "Address", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
			
				   "/app/admin/events"(resources: "event", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
			
				   "/app/admin/guardian"(resources: "guardian", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
			
				   "/app/admin/departments"(resources: "department", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
			



			"/app/admin/schoolclasses"(resources: "SchoolClass" , includes:['index', 'show' , 'save' , 'update' , 'delete','create','patch'])

			"/app/exam/results"(resources: "ExamResult" , includes:['index', 'show' , 'save' , 'update' , 'delete','create','patch'])

			"/app/exams"(resources:"Exam" ,includes:['index', 'show' , 'save' , 'update' , 'delete','create','patch'])


			//mail pendings
			
						"/app/conversations/sent"
								{
									controller = "conversation"
									action = "getSentConversations"
			
								}
			
			
			
			
						//  Teacher events
			
						 "/app/events/teacher/$month/$year"
								 {
									 controller = "teacherDetails"
									 action = "getTeacherMonthEvents"
			
								 }
						"/app/events/teacher/$date"
								{
									controller = "teacherDetails"
									action = "getTeacherEvents"
			
								}
			
						// student events
						 "/app/events/student/$studentId/$month/$year"
								 {
			
									 controller = "guardian"
									 action = "getStudentMonthEvents"
			
								 }
			
						"/app/events/student/$studentId/$date"
								{
									controller = "guardian"
									action = "getStudentClassEvents"
			
								}
								
								"/app/teacher/timetable/week"
								{
									controller = "teacherDetails"
									action = "getTeacherWeekTimetable"
								}
			
								
			

								// TIME TABLE
								"/app/getuserAlbums"
										{
											controller = "FileManagerRead"
											action = "getAlbums"
					
										}
             
		

"/app/timetables"(resources: "TimeTable", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
re


 "/app/admin/exam/newExam"(controller: "ExamDetails" , action: "addExams" , parseRequest: true)

"/app/exams/newExamSyllabus"(controller: "ExamDetails", action: "newExamSyllabus", parseRequest: true)


"/app/exams/newExamSchedule"(controller: "ExamDetails", action: "newExamSchedule", parseRequest: true)


"/app/exams/newExamResult"(controller: "ExamDetails", action: "newExamResult", parseRequest: true)

//new
"/app/admin/teacherGradeSubjects"(controller: "TeacherDetails" , action: "teacherGradesSubjects" , parseRequest: true)

"/app/exams/result/$studentId"
		{
			controller = "ExamDetails"
			action = "studentResult"

		}
"/app/users"(resources: "User", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])

"/app/admin/assignDepartment"(controller: "TeacherDetails" , action: "assignDepartment" , parseRequest: true)
"/app/admin/getTeachers/$departmentName"
		{
			controller = "TeacherDetails"
			action = "getDeptTeachers"

		}
"/app/admin/setClassTeacher"(controller: "TeacherDetails" , action: "setClassTeacher" , parseRequest: true)

"/app/admin/subjects"(resources: "subject", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])

"/app/admin/teacherTimetables"
		{

			controller = "TeacherDetails"
			action = "getTeacherTimeTableList"
		}
"/app/admin/classTimetables"
		{

			controller = "TimeTableDetails"
			action = "getclassTimetableList"
		}
		
		 "/app/admin/addGradeClass"(controller: "TeacherDetails", action: "addGradeClass" ,parseRequest: true)
		

 "/app/admin/GradeTeacherSubject"(resources: "GradeTeacherSubject", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])

"/app/admin/subjectTeacherList"
		{
			controller = "TeacherDetails"
			action = "classSubjectTeacherList"
		}

"/app/exams/syllabus"(resources: "ExamSyllabus", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])

"/app/admin/classFees"(resources: "ClassFees", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
"/app/admin/FeesTypes"(resources: "FeesType", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
"/app/admin/FeeSchedule"(resources: "FeeSchedule", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
"/app/admin/FeesTypeInterval"(resources: "FeesTypeInterval", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
"/app/admin/FeeMasterSchedule"(resources: "FeeMasterSchedule", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
"/app/admin/FeePaid"(resources: "FeePaid", includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])
"/app/teacherEval/upload"(controller: "TeacherDetails" , action: "teacherEvaluationUpload" , parseRequest: true)
"/app/student/ExcelUpload"(controller: "Mydata" , action: "studentExcelData" ,parseRequest: true)
"/app/student/addStudent"(controller: "Mydata" , action: "addStudent" ,parseRequest: false)
"/app/saveAlbums"(controller: "FileManagerWrite" , action: "saveAlbumFiles" ,parseRequest: false)





"/app/exams/teacher/$teacherId"
		{
			controller = "ExamDetails"
			action = "teacherExams"

			}
		
		"/app/exams/teacher"
 	{
					controller = "ExamDetails"
 	action = "teacherExams"
 
				}
		
		"/app/conversations/get/v1"
		{
			controller = "conversation"
			action = "getCurUserConversationsV1"

		}


"/app/teacher/timetable/week/v1"
		{
			controller = "TeacherDetails"
			action = "getTeacherWeekTimetableVer1"
		}

              
										
										"/app/exams/result/$studentId"
										{
											controller = "ExamDetails"
											action = "studentResult"
						
										}	
										
									
										
										"/app/uploadData"(controller: "mydata", action: "test" ,parseRequest: true)
										
										"/app/uploadImage"(controller: "FileManagerRead", action: "read" ,parseRequest: true)
										
										"/app/addSchool"{
											controller = "School"
											action = "addSchool"
									}
										"/app/uploadImageM"{
											controller = "fileManagerRead"
											action = "read"
									}
										

									
										"/app/testSMS"{
											controller = "fileManagerRead"
										   action = "testSMS"
								   }
										
									
										
										"/app/exams/teacherExamsSchedule"
										{
											controller = "ExamDetails"
											action = "teacherExamsSchedule"
						
						
										}
										
	
		
     	"/app/testEmailsend"{
											controller = "mydata"
											action = "upload2"
									}
		 
		 "/app/notice"{
			 controller = "noticeDetails"
			 action = "getnotices"
	 }
		 
		 "/app/registerForpush"(controller: "mydata", action: "registerForpushApp" ,parseRequest: true)
		
		
		/* "/app/registerForpush"{
			 controller = "mydata"
			 action = "registerForpushApp"
	 }
*/

		 "/app/changePassword"(controller: "StudentDetails", action: "updatePassword" ,parseRequest: true)
		 
			"/app/admin/notice"(resources: "notice",  includes: ['index', 'show', 'save', 'update', 'delete', 'create', 'patch'])

			"/app/admin/saveEvent"(controller: "EventDetails" , action: "saveEvent" , parseRequest: true)
			"/app/admin/userRole"(controller: "UserRoleDetails" , action: "addRole" , parseRequest: true)

			"/app/admin/timetable/save"(controller: "TimeTableDetails" , action: "saveTimeTable" , parseRequest: true)

			"/app/admin/student/save"(controller: "StudentDetails" , action: "saveStudent" , parseRequest: true)

          //"mychild/changePwd"(controller: "StudentDetails" , action: "studentSave" , parseRequest: true)
		  
		  "/app/admin/teacher/subjectsInGrade"(controller: "TeacherDetails" , action: "gradeSubjectList" , parseRequest: true)
		 
		  "/api/guest/forgotPwd"(controller: "TeacherDetails", action: "forgetPassword" ,parseRequest: true)
		  
		  
		  "/api/guest/fPwd"(controller: "StudentDetails", action: "forgetPassword" ,parseRequest: true)
		  
		  
		  "/app/admin/exam/newExam"(controller: "ExamDetails" , action: "addExams" , parseRequest: true)
		  
		  "/app/chngPwd"(controller: "TeacherDetails", action: "changePasswordAdmin" ,parseRequest: true)
		  
		  "/app/updateProfilePic"(controller: "FileManagerWrite", action: "updateProfilePic" ,parseRequest: true)
		  
		  
		  
		  
		 
		  
		
			
		}
}