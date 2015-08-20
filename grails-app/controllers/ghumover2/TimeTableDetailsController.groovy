package ghumover2

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import java.text.ParseException
import java.text.SimpleDateFormat


@Secured(['ROLE_TEACHER','ROLE_PARENT'])
class TimeTableDetailsController {

    /// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def springSecurityService

    User user


    def getWeekTimetable()
    {

        def gradeName = params.gradeId
        def response = [:]
        def section = params.section
        def grade = Grade.findByNameAndSection(gradeName,section)
        def timetable = TimeTable.findAllByGrade(grade)
        def days = TimeTable.executeQuery("select distinct a.day from TimeTable a where a.grade = ? " , [grade])
        JSON.use('getTimeTable')
                {
                    days.each {
						
						def timeTableSort=TimeTable.findAllByGradeAndDay(grade,it)
						 Collections.sort(timeTableSort, new Comparator<TimeTable>() {
								private SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm:a");
								@Override
								public int compare(TimeTable o1, TimeTable o2) {
									int result = -1;
									try {
		
										result =  (sdf.parse(o1.startTime).getTime()).compareTo( (sdf.parse(o2.startTime)).getTime());
									} catch (ParseException ex) {
										ex.printStackTrace();
									}
		
									return result;
								}
							});
                        response[it] = timeTableSort
                    }

                    render response as JSON
                }



    }


    def getDayTimeTable()
    {

        def day = params.day
        def section = params.section
        JSON.use('getTimeTable')
                {
                    def grade = Grade.findByNameAndSection(params.gradeId, params.section)
					def timeTableSort=TimeTable.findAllByGradeAndDay(grade, day)
					Collections.sort(timeTableSort, new Comparator<TimeTable>() {
						   private SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm:a");
						   @Override
						   public int compare(TimeTable o1, TimeTable o2) {
							   int result = -1;
							   try {
   
								   result =  (sdf.parse(o1.startTime).getTime()).compareTo( (sdf.parse(o2.startTime)).getTime());
							   } catch (ParseException ex) {
								   ex.printStackTrace();
							   }
   
							   return result;
						   }
					   });
                    def result = timeTableSort
                    render result as JSON
                }


    }







    def getTeacherWeekTimetable()
    {
        def output = [:]
        try {

            user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null
            Teacher teacher = Teacher.findByUsername(user.username)
			
			def timeTableSort=teacher.timetables
					 Collections.sort(timeTableSort, new Comparator<TimeTable>() {
								private  sdf = new java.text.SimpleDateFormat("hh:mm:a");
								@Override
								public int compare(TimeTable o1, TimeTable o2) {
									int result = -1;
									try {
		
										result =  (sdf.parse(o1.startTime).getTime()).compareTo( (sdf.parse(o2.startTime)).getTime());
									} catch (ParseException ex) {
										ex.printStackTrace();
									}
		
									return result;
								}
							});
                        
		
			
            JSON.use('teacherWeekTT')
                    {
                        output['teacherId'] = teacher.id.toString()
                        output['teacherName'] = teacher.teacherName
						
                        output['timeTable'] = timeTableSort
                        render output as JSON
                    }
        }
        catch (Exception e)
        {
            render e
        }
    }





    def getTeacherDayTimetable()
    {
        def output = [:]
        try {
            user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null
            Teacher teacher = Teacher.findByUsername(user.username)
            String day = params.day
			
			def timeTableSort=TimeTable.findAllByTeacherAndDay(teacher,day)
		
			Collections.sort(timeTableSort, new Comparator<TimeTable>() {
				private  sdf = new java.text.SimpleDateFormat("hh:mm:a");
				@Override
				public int compare(TimeTable o1, TimeTable o2) {
					int result = -1;
					try {

						result =  (sdf.parse(o1.startTime).getTime()).compareTo( (sdf.parse(o2.startTime)).getTime());
					} catch (ParseException ex) {
						ex.printStackTrace();
					}

					return result;
				}
			});
	
            output['teacherId'] = teacher.id.toString()
            output['teacherName'] = teacher.teacherName
            output['timeTable'] =  timeTableSort
            render output as JSON

        }
        catch (Exception e)
        {
            render e
        }

    }

	def getclassTimetableList() {
		
				def classTT = [:]
				def output = new ArrayList()
				def timetables = new ArrayList()
				def temp = [:]
		
				ArrayList<TimeTable> timeTableSort = new ArrayList<TimeTable>();
		
				Grade grade
				try {
					def days = TimeTable.executeQuery("select distinct a.day from TimeTable a ")
		
						Grade.findAll().each {
						grade = it
						classTT['gradeId'] = it.gradeId.toString()
						classTT['gradeName'] = it.name.toString()
						classTT['section'] = it.section
						days.each {
							temp['day'] = it
		
							timeTableSort = TimeTable.findAllByGradeAndDay(grade, it)
							 Collections.sort(timeTableSort, new Comparator<TimeTable>() {
								private  sdf = new java.text.SimpleDateFormat("hh:mm:a");
								@Override
								public int compare(TimeTable o1, TimeTable o2) {
									int result = -1;
									try {
		
										result =  (sdf.parse(o1.startTime).getTime()).compareTo( (sdf.parse(o2.startTime)).getTime());
									} catch (ParseException ex) {
										ex.printStackTrace();
									}
		
									return result;
								}
							});
							temp['hours'] = timeTableSort;
							timetables.push(temp)
							temp = [:]
		
						}
						classTT['timetables'] = timetables
						output.push(classTT)
						classTT = [:]
						timetables = new ArrayList()
						temp = [:]
					}
					render output as JSON
				}
				catch (Exception e) {
					render e as JSON
				}
			}
	
	
	
  




      def saveTimeTable()
      {
          try{

              Teacher teacher;
              Grade grade = Grade.get(Integer.parseInt(params.gradeId))
              String day = params.day
              Subject subject;
              int subjectId;
              int teacherId;

              def timetables = params.timetables


              Subject interval = Subject.findOrSaveWhere(subjectName: "Interval");
             // render interval as JSON
              timetables.each{

                  subjectId = Integer.parseInt(it.subject);
                  teacherId = Integer.parseInt(it.teacherId)

                  subject = (Subject.get(subjectId)) ? Subject.get(subjectId) : interval;

                  teacher = (teacherId == 0)?  null : Teacher.get(teacherId);
                  if(teacher)
                  {
                      new TimeTable(day: day , grade: grade ,teacher: teacher , subject: subject , startTime:it.start , endTime: it.end).save()
                  }
                  else {
                      new TimeTable(day: day , grade: grade  , subject: subject , startTime:it.start , endTime: it.end).save()
                  }

              }
               render "OK"



          }
          catch (Exception e)
          {
              render e

          }

      }



    def updateTimeTable()
    {
        try {

               Long id = Long.parseLong(params.id);
               TimeTable timetable = TimeTable.get(id)
               if(params.start)
                  {
                     timetable.startTime = params.start
                  }
               if(params.end)
                 {
                     timetable.endTime = params.end
                 }
               if(params.teacherId)
               {
                   Teacher teacher = Teacher.get(Integer.parseInt(params.teacherId))
                   timetable.teacher = teacher
               }
              if(params.subject)
                {
                  Subject subject  = Subject.get(Integer.parseInt(params.subject))
                   timetable.subject = subject;

                }

               timetable.save()


               render timetable as JSON


            }

        catch (Exception e)
        {

            render e;
        }


    }


    def deleteTimeTable()
    {
        try
        {

            Long id = Long.parseLong(params.id);
            TimeTable timetable = TimeTable.get(id)
            def output = [:]
            timetable.delete()
                output['status'] = "sucess"
                output['message'] = 'Entry deleted'


            render output as JSON

        }
        catch (Exception e)
        {
             render e
        }


    }







}
