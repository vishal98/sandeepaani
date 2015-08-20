import java.text.SimpleDateFormat
import java.util.Date

import ghumover2.*
import grails.converters.JSON
import groovy.sql.Sql


class BootStrap {

	private static final String ROLE_TEACHER = 'ROLE_TEACHER'
	private static final String ROLE_PARENT = 'ROLE_PARENT'
	private static final String ROLE_ADMIN = 'ROLE_ADMIN'




	def dataSource
	def init = { servletContext ->

		/*// BOOTSTRAPING DATES
		def createQuery = "CREATE TABLE IF NOT EXISTS ints ( i tinyint unique );"
		def insertQuery = "INSERT INTO ints (i) VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9)   ON DUPLICATE KEY UPDATE i = VALUES(i);"
		def insertCalenderDates = """\

										INSERT INTO calendar_date (calendar_date)
										SELECT DATE('2010-01-01') + INTERVAL a.i*10000 + b.i*1000 + c.i*100 + d.i*10 + e.i DAY
										FROM ints a JOIN ints b JOIN ints c JOIN ints d JOIN ints e
										WHERE (a.i*10000 + b.i*1000 + c.i*100 + d.i*10 + e.i) <= 11322
										ORDER BY 1;
                                    """
		def updateCalenderDates = """\
															UPDATE calendar_date
															SET is_weekday = CASE WHEN dayofweek(calendar_date) IN (1,7) THEN 0 ELSE 1 END,
															is_holiday = 0,
															is_payday = 0,
															year = YEAR(calendar_date),
															quarter = quarter(calendar_date),
															month = MONTH(calendar_date),
															day_of_month = dayofmonth(calendar_date),
															day_of_week = dayofweek(calendar_date),
															month_name = monthname(calendar_date),
															day_name = dayname(calendar_date),
															week_of_year = week(calendar_date),
															holiday_description = '';
"""

		def sql = new Sql(dataSource)

		sql.executeUpdate(createQuery)
		sql.executeUpdate(insertQuery)

		sql.executeUpdate(insertCalenderDates)
		sql.executeUpdate(updateCalenderDates)

		//ADDED AUGUST 15 AS HOLIDAY
		CalendarDate.executeUpdate("update CalendarDate c set c.holiday_description='Independance Day' , is_holiday = true " +
				"where c.month=8 and c.day_of_month = 15")



		CalendarDate.executeUpdate("update CalendarDate c set c.isHoliday = true , c.holiday_description='Sunday' where c.dayName like ? " , ['Sunday']);


		// END OF BOOTSTRAPING DATES



		Role roleTeacher;
		Role roleParent;
		Teacher teacher;
		Guardian parent;


		roleTeacher = new Role(authority: ROLE_TEACHER)
		roleTeacher.save()

		roleParent = new Role(authority: ROLE_PARENT)
		roleParent.save()

		def roleAdmin = new Role(authority: ROLE_ADMIN)
		roleAdmin.save()

		def admin = new User(username: "admin" , password: '123' ).save()
		new UserRole(user: admin, role: roleAdmin).save()
		
		///Houses obj initialize
		Houses Y=new Houses(hCode:"Y",hName:"YELLOW").save();
		Houses G=new Houses(hCode:"G",hName:"GREEN").save();
		Houses B=new Houses(hCode:"B",hName:"BLUE").save();
		Houses R=new Houses(hCode:"R",hName:"RED").save();
		Houses houses = new Houses();
		
		// OtherLanguage obj initialization
		OtherLanguage E= new OtherLanguage("E", "ENGLISH").save();
		OtherLanguage H= new OtherLanguage("H", "HINDI").save();
		OtherLanguage K= new OtherLanguage("K", "KANNADA").save();
		OtherLanguage T= new OtherLanguage("T", "TELUGU").save();
		OtherLanguage oLangs= new OtherLanguage();

		def school = new School()
		school.schoole_name = "Sandeepaani"
		school.address = "bangalore"
		school.landmark = " bekary"
		school.landline = "0806754566"
		school.save(flush:true);

		
		 Subject english , hindi , chemistry , physics , computerScience , history , socialScience , biology , maths
		 english = new Subject(subjectName: "English").save()
		 hindi = new Subject(subjectName: "Hindi").save()
		 chemistry = new Subject(subjectName: "Chemistry").save()
		 computerScience = new Subject(subjectName: "Computer Science").save()
		 history = new Subject(subjectName: "History").save()
		 socialScience = new Subject(subjectName: "Social Studies").save()
		 biology = new Subject(subjectName: "Biology").save()
		 maths = new Subject(subjectName: "Maths").save()
		 physics = new Subject(subjectName: "Physics").save()
		 Subject ILanguage = new Subject(subjectName: "I Language").save()
		 Subject IILanguage = new Subject(subjectName: "II Language").save()
		 Subject science = new Subject(subjectName: "Science").save()
		 Subject computers = new Subject(subjectName: "Computers").save()
		 Subject  gk = new Subject(subjectName: "G.K").save()
		 Subject clubAct = new Subject(subjectName: "Club Activities").save()
		 Subject pt = new Subject(subjectName: "PT").save()
		 Subject activities = new Subject(subjectName: "Activities").save()
		 
		 SchoolClass cl5 = new SchoolClass()
		 cl5.className = "5"
		 cl5.school = school
		 cl5.classTags = school.tags +",\""+cl5.className+"\""
		 cl5.save(flush:true);
		 SchoolClass cl6 = new SchoolClass()
		 cl6.className = "6"
		 cl6.school = school
		 cl6.classTags = school.tags +",\""+cl6.className+"\""
		 cl6.save(flush:true);
		 SchoolClass cl7 = new SchoolClass()
		 cl7.className = "NurSery"
		 cl7.school = school
		 cl7.classTags = school.tags +",\""+cl7.className+"\""
		 cl7.save(flush:true);
		 SchoolClass cl8 = new SchoolClass()
		 cl8.className = "8"
		 cl8.school = school
		 cl8.classTags = school.tags +",\""+cl8.className+"\""
		 cl8.save(flush:true);
		 SchoolClass cl9 = new SchoolClass()
		 cl9.className = "9"
		 cl9.school = school
		 cl9.classTags = school.tags +",\""+cl9.className+"\""
		 cl9.save(flush:true);
		 SchoolClass cl10 = new SchoolClass()
		 cl10.className = "10"
		 cl10.school = school
		 cl10.classTags = school.tags +",\""+cl10.className+"\""
		 cl10.save(flush:true);
		 Grade cl5A , cl5B , cl6A , cl6B , cl7A ,cl7B , cl8A , cl8B , cl9A , cl9B , cl10A , cl10B
		 cl5A = new Grade(name: cl5.className , section: "A" , classTeacherId: 1,schoolClass:cl5).save()
		 cl5B = new Grade(name: cl5.className , section: "B" , classTeacherId: 2,schoolClass:cl5).save()
		 cl6A = new Grade(name: cl6.className , section: "A" , classTeacherId: 3,schoolClass:cl6).save()
		 cl6B = new Grade(name: cl6.className , section: "B" , classTeacherId: 4,schoolClass:cl6).save()
		 cl7A = new Grade(name: cl7.className , section: "A" , classTeacherId: 5,schoolClass:cl7).save()
		 cl7B = new Grade(name: cl7.className , section: "B" , classTeacherId: 6,schoolClass:cl7).save()
		 cl8A = new Grade(name: cl8.className , section: "A" , classTeacherId: 7,schoolClass:cl8).save()
		 cl8B = new Grade(name: cl8.className , section: "B" , classTeacherId: 8,schoolClass:cl8).save()
		 cl9A = new Grade(name: cl9.className , section: "A",schoolClass:cl9).save()
		 cl9B = new Grade(name: cl9.className , section: "B",schoolClass:cl9).save()
		 cl10A = new Grade(name: cl10.className , section: "A",schoolClass:cl10).save()
		 cl10B = new Grade(name: cl10.className , section: "B",schoolClass:cl10).save()
		 Teacher mathew , sibi , satheesh , raji , robin , binu , hari , shyam , anil , maya , vinod
		 mathew = new Teacher(username: "mathew" , password: "123" ,teacherName: "Mathew" , teacherEmailId: "mathew@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 sibi = new Teacher(username: "sibi" , password: "123" ,teacherName: "Sibi" , teacherEmailId: "sibi@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 satheesh = new Teacher(username: "satheesh" , password: "123" ,teacherName: "Satheesh Kumar" , teacherEmailId: "satheesh@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 raji =  new Teacher(username: "raji" , password: "123" ,teacherName: "Raji" , teacherEmailId: "raji@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 robin = new Teacher(username: "robin" , password: "123" ,teacherName: "Robin" , teacherEmailId: "robin@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 binu = new Teacher(username: "binu" , password: "123" ,teacherName: "Binu" , teacherEmailId: "binu@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 hari = new Teacher(username: "hari" , password: "123" ,teacherName: "Hari" , teacherEmailId: "hari@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 shyam = new Teacher(username: "shyam" , password: "123" ,teacherName: "Shyam" , teacherEmailId: "shyam@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 anil = new Teacher(username: "anil" , password: "123" ,teacherName: "Anil" , teacherEmailId: "anil@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 maya = new Teacher(username: "maya" , password: "123" ,teacherName: "Maya" , teacherEmailId: "maya@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 vinod = new Teacher(username: "vinod" , password: "123" ,teacherName: "Vinod" , teacherEmailId: "vinod@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 new UserRole(user: mathew , role: roleTeacher).save()
		 new UserRole(user: sibi , role: roleTeacher).save()
		 new UserRole(user: satheesh , role: roleTeacher).save()
		 new UserRole(user: raji, role: roleTeacher).save()
		 new UserRole(user: robin, role: roleTeacher).save()
		 new UserRole(user: binu, role: roleTeacher).save()
		 new UserRole(user: hari, role: roleTeacher).save()
		 new UserRole(user: shyam, role: roleTeacher).save()
		 new UserRole(user: anil, role: roleTeacher).save()
		 new UserRole(user: maya, role: roleTeacher).save()
		 new UserRole(user: vinod, role: roleTeacher).save()
		 def archana = new Teacher(username: "archana" , password: "123" , teacherName: "Archana" , teacherEmailId: "archana@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 def vanajakshi = new Teacher(username: "vanajakshi" , password: "123" , teacherName: "Vanajakshi" , teacherEmailId: "vanajakshi@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 def sheetal = new Teacher(username: "sheetal" , password: "123" , teacherName: "Sheetal" , teacherEmailId: "sheetal@test.com", teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 def kiran = new Teacher(username: "kiran" , password: "123" , teacherEmailId:"kiran@test.com" , teacherName: "Kiran", teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 def naresh =  new Teacher(username: "naresh" , password: "123" , teacherEmailId: "naresh@test.com" , teacherName: "Naresh", teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 def ptTeacher = new Teacher(username: "pt" , password: "123" , teacherName: "PT Teacher" , teacherEmailId: "pt@test.com" , teacherPhoto: "teacher.jpg" ,phoneNo: "984700000" ).save()
		 [archana,vanajakshi,sheetal,kiran,naresh,ptTeacher].each {
		 new UserRole(user: it, role: roleTeacher).save()
		 }
		 archana.addToGradeSubject(cl5A,maths)
		 vanajakshi.addToGradeSubject(cl5A,ILanguage)
		 vanajakshi.addToGradeSubject(cl5A,clubAct)
		 sheetal.addToGradeSubject(cl5A,science)
		 kiran.addToGradeSubject(cl5A,IILanguage)
		 naresh.addToGradeSubject(cl5A,socialScience)
		 ptTeacher.addToGradeSubject(cl5A,pt)
		 mathew.addToGradeSubject(cl5A,activities)
		 mathew.addToGradeSubject(cl5A,english)
		 mathew.addToGradeSubject(cl5A,hindi)
		 mathew.addToGradeSubject(cl5A,computerScience)
		 mathew.addToGradeSubject(cl5A,hindi)
		 mathew.addToGradeSubject(cl5B,history)
		 mathew.addToGradeSubject(cl9A,english)
		 mathew.addToGradeSubject(cl9B,english)
		 sibi.addToGradeSubject(cl5A,hindi)
		 sibi.addToGradeSubject(cl5B,hindi)
		 sibi.addToGradeSubject(cl7A,english)
		 sibi.addToGradeSubject(cl7B,english)
		 sibi.addToGradeSubject(cl9A,maths)
		 sibi.addToGradeSubject(cl9B,maths)
		 satheesh.addToGradeSubject(cl5A,chemistry)
		 satheesh.addToGradeSubject(cl5B,chemistry)
		 satheesh.addToGradeSubject(cl7A,hindi)
		 satheesh.addToGradeSubject(cl7B,hindi)
		 raji.addToGradeSubject(cl5A,physics)
		 raji.addToGradeSubject(cl5B,physics)
		 raji.addToGradeSubject(cl6A,english)
		 raji.addToGradeSubject(cl6B,english)
		 robin.addToGradeSubject(cl7A,biology)
		 robin.addToGradeSubject(cl7B,biology)
		 robin.addToGradeSubject(cl9A,biology)
		 robin.addToGradeSubject(cl9B,biology)
		 binu.addToGradeSubject(cl8A,hindi)
		 binu.addToGradeSubject(cl8B,hindi)
		 hari.addToGradeSubject(cl8A,computerScience)
		 hari.addToGradeSubject(cl8B,computerScience)
		 shyam.addToGradeSubject(cl6A,computerScience)
		 shyam.addToGradeSubject(cl6B,computerScience)
		 shyam.addToGradeSubject(cl8A,maths)
		 shyam.addToGradeSubject(cl8B,maths)
		 shyam.addToGradeSubject(cl9A,computerScience)
		 shyam.addToGradeSubject(cl9B,computerScience)
		 anil.addToGradeSubject(cl8A,english)
		 anil.addToGradeSubject(cl8B,english)
		 maya.addToGradeSubject(cl6A,hindi)
		 maya.addToGradeSubject(cl6B,hindi)
		 maya.addToGradeSubject(cl9A,hindi)
		 maya.addToGradeSubject(cl9B,hindi)
		 vinod.addToGradeSubject(cl6A,history)
		 vinod.addToGradeSubject(cl6B,history)
		 vinod.addToGradeSubject(cl7A,socialScience)
		 vinod.addToGradeSubject(cl7B,socialScience)
		 def father , mother , local_guardian , s1 , s2 , s3
		 
		 //route details
		 
		 def rt=new Route( deviceNo:"1244",routeName:"test", schoolId:"1").save()
		 def loc=new BusLocation(	locationData:"test",longitute:"12.9789659",latitude:"77.6981664", driverName:"ram", driverPhonenumber:"2324424",lastUpdated:new Date(),
	  creationDate:new Date(),route:rt ).save()
	  
		 // FIRST STUDENT DETAILS
		 s1 =  new Student(grade:cl5A,routeId:rt.routeId  , registerNumber: "ST100" ,studentName: "Rohith" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Father" , present_address: (new Address(address: "Test Address" , landmark: "Karnatka" , place: "Kerala" ).save()) ).save()
		 s1.setAsFather( new Guardian(name: "Ravi" , username: "ravi@test.com" , password: "123" , educational_qualification: "MBA" , designation: "Manager" , profession: "Private Employee" , emailId: "father@user.com" , officeNumber: "04868699000" , mobileNumber: "98470000" ).save() )
		 s1.setAsMother( new Guardian(name:"Raani" , username: "raani@test.com" , password: "123" , educational_qualification: "Bcom" , designation: "College Professor" , profession: "Lecturer" , emailId: "mother@user.com" ,officeNumber: "0489898989" , mobileNumber: "94466797979"  ).save() )
		 father = Guardian.findByUsername("ravi@test.com")
		 mother = Guardian.findByUsername("raani@test.com")
		 s2 =  new Student(grade: cl5B ,routeId:rt.routeId, registerNumber: "ST101" ,studentName: "Renjith" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Father" , present_address: (new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()) ).save()
		 s2.setAsFather( father )
		 s2.setAsMother( mother )
		 s3 =  new Student(grade: cl6A ,routeId:rt.routeId,  registerNumber: "ST102" ,studentName: "Rohan" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Father"  , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()).save()
		 s3.setAsFather( father )
		 s3.setAsMother( mother )
		 new UserRole(user:father , role:roleParent).save(flush: true)
		 new UserRole(user:mother , role:roleParent).save(flush: true)
		 s1 =  new Student( grade:cl5A  , registerNumber: "ST106" ,studentName: "Seeya" , gender: "Female" , dob:"12-12-2000" , studentPhoto: "https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/006.JPG", no_of_siblings: 2 , present_guardian: "Mother" , present_address: (new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save())   ).save()
		 s1.setAsFather( new Guardian(name: "Shashi" , username: "shashi@test.com" , password: "123" , educational_qualification: "MBA" , designation: "Manager" , profession: "Private Employee" , emailId: "father@user.com" , officeNumber: "04868699000" , mobileNumber: "98470000" ).save() )
		 s1.setAsMother( new Guardian(name:"Nanditha" , username: "nanditha@test.com" , password: "123" , educational_qualification: "Bcom" , designation: "College Professor" , profession: "Lecturer" , emailId: "mother@user.com" ,officeNumber: "0489898989" , mobileNumber: "94466797979"  ).save() )
		 father = Guardian.findByUsername("shashi@test.com")
		 mother = Guardian.findByUsername("nanditha@test.com")
		 s2 =  new Student(grade: cl7A , registerNumber: "ST107" ,studentName: "Sidhu" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/004.JPG", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()).save()
		 s2.setAsFather( father )
		 s2.setAsMother( mother )
		 s3 =  new Student( grade: cl5A ,  registerNumber: "ST108" ,studentName: "Nikhitha" , gender: "Female" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()).save()
		 s3.setAsFather( father )
		 s3.setAsMother( mother )
		 new UserRole(user:father , role:roleParent).save(flush: true)
		 new UserRole(user:mother , role:roleParent).save(flush: true)
		 // SECOND STUDENT DETAILS
		 s1 =  new Student( grade:cl5B  , registerNumber: "ST103" ,studentName: "Midhun" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Local Guardian" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()  ).save()
		 s1.setAsFather( new Guardian(name: "Mahadev" , username: "mahadev@test.com" , password: "123" , educational_qualification: "MBA" , designation: "Manager" , profession: "Private Employee" , emailId: "father@user.com" , officeNumber: "04868699000" , mobileNumber: "98470000" ).save() )
		 s1.setAsMother( new Guardian(name:"Malini" , username: "malini@test.com" , password: "123" , educational_qualification: "Bcom" , designation: "College Professor" , profession: "Lecturer" , emailId: "mother@user.com" ,officeNumber: "0489898989" , mobileNumber: "94466797979"  ).save() )
		 s1.setAsLocalGuardian((new Guardian(name:"Manish" , username: "manish@test.com" , password: "123" , educational_qualification: "MCA" , designation: "Software Engineer" , profession: "IT Professional" , emailId: "local_guard@test.com" ,officeNumber: "0489898989" , mobileNumber: "94466797979" )).save())
		 father = Guardian.findByUsername("mahadev@test.com")
		 mother = Guardian.findByUsername("malini@test.com")
		 local_guardian = Guardian.findByUsername("manish@test.com")
		 // SECOND STUDENT DETAILS
		 s2 =  new Student(grade: cl5B , registerNumber: "ST104" ,studentName: "Manoj" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Local Guardian" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()).save()
		 s2.setAsFather( father )
		 s2.setAsMother( mother )
		 s2.setAsLocalGuardian( local_guardian )
		 s3 =  new Student(grade: cl5A ,  registerNumber: "ST105" ,studentName: "Mohith" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Local Guardian", present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save() ).save()
		 s3.setAsFather( father )
		 s3.setAsMother( mother )
		 s3.setAsLocalGuardian( local_guardian )
		 new UserRole(user:father , role:roleParent).save(flush: true)
		 new UserRole(user:mother , role:roleParent).save(flush: true)
		 // third group STUDENT DETAILS
		 s1 =  new Student( grade:cl5A  , registerNumber: "ST109" ,studentName: "Akhil" , gender: "male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()  ).save()
		 s1.setAsFather( new Guardian(name: "Jacob" , username: "jacob@test.com" , password: "123" , educational_qualification: "MBA" , designation: "Manager" , profession: "Private Employee" , emailId: "father@user.com" , officeNumber: "04868699000" , mobileNumber: "98470000" ).save() )
		 s1.setAsMother( new Guardian(name:"Reena" , username: "reena@test.com" , password: "123" , educational_qualification: "Bcom" , designation: "College Professor" , profession: "Lecturer" , emailId: "mother@user.com" ,officeNumber: "0489898989" , mobileNumber: "94466797979"  ).save() )
		 father = Guardian.findByUsername("jacob@test.com")
		 mother = Guardian.findByUsername("reena@test.com")
		 s2 =  new Student(grade: cl5A , registerNumber: "ST110" ,studentName: "Abhijith" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()).save()
		 s2.setAsFather( father )
		 s2.setAsMother( mother )
		 s3 =  new Student( grade: cl5B ,  registerNumber: "ST111" ,studentName: "Ashiq" , gender: "male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()).save()
		 s3.setAsFather( father )
		 s3.setAsMother( mother )
		 new UserRole(user:father , role:roleParent).save(flush: true)
		 new UserRole(user:mother , role:roleParent).save(flush: true)
		 s1 =  new Student( grade:cl5A  , registerNumber: "ST112" ,studentName: "Bony" , gender: "male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()  ).save()
		 s1.setAsFather( new Guardian(name: "Joy" , username: "joy@test.com" , password: "123" , educational_qualification: "MBA" , designation: "Manager" , profession: "Private Employee" , emailId: "father@user.com" , officeNumber: "04868699000" , mobileNumber: "98470000" ).save() )
		 s1.setAsMother( new Guardian(name:"Molly" , username: "molly@test.com" , password: "123" , educational_qualification: "Bcom" , designation: "College Professor" , profession: "Lecturer" , emailId: "mother@user.com" ,officeNumber: "0489898989" , mobileNumber: "94466797979"  ).save() )
		 father = Guardian.findByUsername("joy@test.com")
		 mother = Guardian.findByUsername("molly@test.com")
		 s2 =  new Student(grade: cl5A , registerNumber: "ST113" ,studentName: "Binil" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()).save()
		 s2.setAsFather( father )
		 s2.setAsMother( mother )
		 new UserRole(user:father , role:roleParent).save(flush: true)
		 new UserRole(user:mother , role:roleParent).save(flush: true)
		 s1 =  new Student( grade:cl5B  , registerNumber: "ST114" ,studentName: "Nijo" , gender: "male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()  ).save()
		 s1.setAsFather( new Guardian(name: "Raju" , username: "raju@test.com" , password: "123" , educational_qualification: "MBA" , designation: "Manager" , profession: "Private Employee" , emailId: "father@user.com" , officeNumber: "04868699000" , mobileNumber: "98470000" ).save() )
		 s1.setAsMother( new Guardian(name:"Geetha" , username: "geetha@test.com" , password: "123" , educational_qualification: "Bcom" , designation: "College Professor" , profession: "Lecturer" , emailId: "mother@user.com" ,officeNumber: "0489898989" , mobileNumber: "94466797979"  ).save() )
		 father = Guardian.findByUsername("raju@test.com")
		 mother = Guardian.findByUsername("geetha@test.com")
		 s2 =  new Student(grade: cl5A , registerNumber: "ST115" ,studentName: "Nithin" , gender: "Male" , dob:"12-12-2000" , studentPhoto: "photo.jpg", no_of_siblings: 2 , present_guardian: "Mother" , present_address: new Address(address: "Sample Address" , landmark: "Cochin" , place: "Kerala" ).save()).save()
		 s2.setAsFather( father )
		 s2.setAsMother( mother )
		 new UserRole(user:father , role:roleParent).save(flush: true)
		 new UserRole(user:mother , role:roleParent).save(flush: true)
		 //Homeworks for students
		 new Homework(grade: cl5A , subject: "english" , homework: "English homework", dueDate: "31-12-2016"  ,student: Student.findByStudentId(1) , message: "English Homework for Student , 5 A ", gradeFlag: '0').save(flush: true)
		 // Add exam entries Date startTime
		 new Department(dept_name: "English").addToTeachers(mathew).save(flush: true)
		 new Department(dept_name: "Mathematics").addToTeachers(sibi).save(flush: true)
		 Exam entries
		 def modelExam = new Exam(examName : "First Term Model Examination for Class 5" ,examType:"Internal Examination" , schoolclass: SchoolClass.findByClassName("5")).save()
		 def englishSyllabus = new ExamSyllabus(exam: modelExam , subject: english ,syllabus: "Chapter 1-7 " ).save()
		 def chemistrySyllabus = new ExamSyllabus(exam: modelExam , subject: chemistry,syllabus: "Oraganic Chem Chapter 1-3").save()
		 def physicsSyllabus = new ExamSyllabus(exam: modelExam , subject: physics , syllabus: "Chapter 1-3 newton laws").save()
		 def hindiSyllabus = new ExamSyllabus(exam: modelExam , subject: hindi , syllabus: "Poems and  Chapter 1-3").save()
		 new ExamSchedule(exam: modelExam  ,subjectSyllabus: englishSyllabus,  subject: english ,teacher :mathew,startTime: "11-02-2014 09:30",endTime: "11-02-2014 10:00" ).save()
		 new ExamSchedule(exam: modelExam  ,subjectSyllabus: physicsSyllabus, subject: physics ,teacher :mathew,startTime: "11-02-2014 09:30",endTime: "11-02-2014 10:30").save(flush: true)
		 new ExamSchedule(exam: modelExam ,subjectSyllabus: hindiSyllabus , subject: hindi ,teacher :sibi,startTime: "11-02-2014 09:30",endTime: "11-02-2014 10:30").save(flush: true)
		 new ExamSchedule(exam: modelExam  ,subjectSyllabus: chemistrySyllabus, subject: chemistry ,teacher :mathew,startTime: "11-02-2014 09:30",endTime: "11-02-2014 10:30").save(flush: true)
		 new ExamResult(exam: modelExam , student: Student.findByStudentName("Rohith") , subject:english , marks: 70 , maxMarks: 100 ).save()
		 new ExamResult(exam: modelExam , student: Student.findByStudentName("Rohith") , subject:hindi , marks: 80 , maxMarks: 100 ).save()
		 new ExamResult(exam: modelExam , student: Student.findByStudentName("Rohith") , subject:chemistry , marks: 60 , maxMarks: 100 ).save()
		 def classTest5B =  new Exam(examName : "Class test for class 5 B" ,examType:" Class Test" , grade: Grade.findByNameAndSection("5","B")  ).save()
		 englishSyllabus = new ExamSyllabus(exam: classTest5B , subject: english ,syllabus: "First chapter complete " ).save()
		 chemistrySyllabus = new ExamSyllabus(exam: classTest5B , subject: chemistry ,syllabus: " Upto third chapter  " ).save()
		 new ExamSchedule(exam: classTest5B , subjectSyllabus: englishSyllabus , subject: english , teacher: mathew , startTime: "12-05-2015 11:00" , endTime:  "12-05-2015 11:30"  ).save()
		 new ExamSchedule(exam: classTest5B , subjectSyllabus: chemistrySyllabus , subject: chemistry , teacher: satheesh , startTime: "12-05-2015 13:30" , endTime:  "12-05-2015 14:30"  ).save()
		 new ExamResult(exam: classTest5B , student: Student.findByStudentName("Ashiq") , subject:english , marks: 50 , maxMarks: 100 ).save()
		 new ExamResult(exam: classTest5B , student: Student.findByStudentName("Nijo") , subject:english , marks: 60 , maxMarks: 100 ).save()
		 new ExamResult(exam: classTest5B , student: Student.findByStudentName("Ashiq") , subject:chemistry , marks: 50 , maxMarks: 100 ).save()
		 new ExamResult(exam: classTest5B , student: Student.findByStudentName("Nijo") , subject:chemistry , marks: 60 , maxMarks: 100 ).save()
		 cl5B.addToExams(classTest5B).save(flush: true)
		 def publicExam = new Exam(examName: "5th Grade public exam May 2015 " , examType: "Public Exam" , schoolclass: SchoolClass.findByClassName("5") ).save()
		 new ExamSchedule(exam: publicExam  ,subjectSyllabus: englishSyllabus,  subject: english ,teacher :mathew,startTime: "11-02-2014 09:30",endTime: "11-02-2014 10:00" ).save()
		 new ExamSchedule(exam: publicExam  ,subjectSyllabus: physicsSyllabus, subject: physics ,teacher :raji,startTime: "11-02-2014 09:30",endTime: "11-02-2014 10:30").save(flush: true)
		 new ExamSchedule(exam: publicExam ,subjectSyllabus: hindiSyllabus , subject: hindi ,teacher :mathew,startTime: "11-02-2014 09:30",endTime: "11-02-2014 10:30").save(flush: true)
		 new ExamSchedule(exam: publicExam  ,subjectSyllabus: chemistrySyllabus, subject: chemistry ,teacher :mathew ,startTime: "11-02-2014 09:30",endTime: "11-02-2014 10:30").save(flush: true)
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Rohith") , subject:english , marks: 70 , maxMarks: 100 ).save()
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Rohith") , subject:hindi , marks: 80 , maxMarks: 100 ).save()
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Rohith") , subject:chemistry , marks: 60 , maxMarks: 100 ).save()
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Neha") , subject:english , marks: 70 , maxMarks: 100 ).save()
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Neha") , subject:hindi , marks: 80 , maxMarks: 100 ).save()
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Neha") , subject:chemistry , marks: 60 , maxMarks: 100 ).save()
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Bony") , subject:english , marks: 70 , maxMarks: 100 ).save()
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Bony") , subject:hindi , marks: 80 , maxMarks: 100 ).save()
		 new ExamResult(exam: publicExam , student: Student.findByStudentName("Bony") , subject:chemistry , marks: 60 , maxMarks: 100 ).save()
		 cl6A.addToTimetable(new TimeTable(grade: cl6A , day: "Monday" , teacher:mathew , subject: maths , startTime: "07:30:AM" , endTime: "08:00:AM")).save()
		 cl7A.addToTimetable(new TimeTable(grade: cl7A , day: "Monday" , teacher:mathew , subject: ILanguage , startTime: "08:00:AM" , endTime: "09:00:AM")).save()
		 cl8A.addToTimetable(new TimeTable(grade: cl8A , day: "Monday" , teacher:mathew , subject: science , startTime: "09:00:AM" , endTime: "10:00:AM")).save()
		 cl9A.addToTimetable(new TimeTable(grade: cl9A , day: "Monday" , teacher:mathew , subject: IILanguage , startTime: "10:30:AM" , endTime: "11:30:AM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Monday" , teacher:archana , subject: maths , startTime: "07:30:AM" , endTime: "08:00:AM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Monday" , teacher:vanajakshi , subject: ILanguage , startTime: "08:00:AM" , endTime: "09:00:AM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Monday" , teacher:sheetal , subject: science , startTime: "09:00:AM" , endTime: "10:00:AM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Monday" , teacher:kiran , subject: IILanguage , startTime: "10:30:AM" , endTime: "11:30:AM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Monday" , teacher:naresh , subject: socialScience , startTime: "11:30:AM" , endTime: "12:30:PM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Monday" , teacher:archana , subject: maths , startTime: "01:00:PM" , endTime: "02:00:PM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Monday" , teacher:ptTeacher , subject: pt , startTime: "02:00:PM" , endTime: "03:00:PM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Monday" , teacher:mathew , subject: activities , startTime: "03:00:PM" , endTime: "03:30:PM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Tuesday" , teacher:kiran , subject: IILanguage , startTime: "07:30:AM" , endTime: "08:00:AM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Tuesday" , teacher:archana , subject: maths , startTime: "08:00:AM" , endTime: "09:00:AM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Tuesday" , teacher:naresh , subject: socialScience , startTime: "09:00:AM" , endTime: "10:00:AM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Tuesday" , teacher:vanajakshi , subject: ILanguage , startTime: "10:30:AM" , endTime: "11:30:AM")).save()
		// cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Tuesday" , teacher:archana , subject: maths , startTime: "11:30:AM" , endTime: "12:30:PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Tuesday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 PM")).save()
		 //l5A.addToTimetable(new TimeTable(grade: cl5A , day: "Tuesday" , teacher:ptTeacher , subject: pt , startTime: "02:00 :PM" , endTime: "03:00 ::PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Tuesday" , teacher:mathew , subject: activities , startTime: "03:00:PM" , endTime: "03:30:PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Wednesday" , teacher:archana , subject: maths , startTime: "07:30 :AM" , endTime: "08:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Wednesday" , teacher:vanajakshi , subject: ILanguage , startTime: "08:00 :AM" , endTime: "09:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Wednesday" , teacher:sheetal , subject: science , startTime: "09:00 :AM" , endTime: "10:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Wednesday" , teacher:kiran , subject: IILanguage , startTime: "10:30 :AM" , endTime: "11:30 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Wednesday" , teacher:naresh , subject: socialScience , startTime: "11:30 :AM" , endTime: "12:30 :PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Wednesday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 :PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Wednesday" , teacher:sheetal , subject: science , startTime: "02:00 :PM" , endTime: "03:00 :PM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Wednesday" , teacher:mathew , subject: activities , startTime: "03:00:PM" , endTime: "03:30:PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Thursday" , teacher:archana , subject: IILanguage , startTime: "07:30 :AM" , endTime: "08:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Thursday" , teacher:vanajakshi , subject: maths , startTime: "08:00 :AM" , endTime: "09:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Thursday" , teacher:sheetal , subject: pt , startTime: "09:00 :AM" , endTime: "10:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Thursday" , teacher:kiran , subject: ILanguage , startTime: "10:30 :AM" , endTime: "11:30 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Thursday" , teacher:naresh , subject: maths , startTime: "11:30 :AM" , endTime: "12:30 :PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Thursday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 :PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Thursday" , teacher:sheetal , subject: pt , startTime: "02:00 :PM" , endTime: "03:00 :PM")).save()
		 cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Thursday" , teacher:mathew , subject: activities , startTime: "03:00:PM" , endTime: "03:30:PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Friday" , teacher:sheetal , subject: science , startTime: "07:30 :AM" , endTime: "08:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Friday" , teacher:vanajakshi , subject: ILanguage , startTime: "08:00 :AM" , endTime: "09:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Friday" , teacher:vanajakshi , subject: clubAct , startTime: "09:00 :AM" , endTime: "10:00 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Friday" , teacher:kiran , subject: IILanguage , startTime: "10:30 :AM" , endTime: "11:30 :AM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Friday" , teacher:naresh , subject: socialScience , startTime: "11:30 :AM" , endTime: "12:30 :PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Friday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 :PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Friday" , teacher:vanajakshi , subject: clubAct , startTime: "02:00 :PM" , endTime: "03:00 :PM")).save()
		 //cl5A.addToTimetable(new TimeTable(grade: cl5A , day: "Friday" , teacher:mathew , subject: activities , startTime: "03:00 :PM" , endTime: "03:30 :PM")).save()
		 // 5b
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Monday" , teacher:archana , subject: maths , startTime: "07:30 :AM" , endTime: "08:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Monday" , teacher:vanajakshi , subject: ILanguage , startTime: "08:00 :AM" , endTime: "09:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Monday" , teacher:sheetal , subject: science , startTime: "09:00 :AM" , endTime: "10:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Monday" , teacher:kiran , subject: IILanguage , startTime: "10:30 :AM" , endTime: "11:30 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Monday" , teacher:naresh , subject: socialScience , startTime: "11:30 :AM" , endTime: "12:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Monday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Monday" , teacher:ptTeacher , subject: pt , startTime: "02:00 :PM" , endTime: "03:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Monday" , teacher:mathew , subject: activities , startTime: "03:00 :PM" , endTime: "03:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Tuesday" , teacher:kiran , subject: IILanguage , startTime: "07:30 :AM" , endTime: "08:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Tuesday" , teacher:archana , subject: maths , startTime: "08:00 :AM" , endTime: "09:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Tuesday" , teacher:naresh , subject: socialScience , startTime: "09:00 :AM" , endTime: "10:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Tuesday" , teacher:vanajakshi , subject: ILanguage , startTime: "10:30 :AM" , endTime: "11:30 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Tuesday" , teacher:archana , subject: maths , startTime: "11:30 :AM" , endTime: "12:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Tuesday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Tuesday" , teacher:ptTeacher , subject: pt , startTime: "02:00 :PM" , endTime: "03:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Tuesday" , teacher:mathew , subject: activities , startTime: "03:00 :PM" , endTime: "03:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Wednesday" , teacher:archana , subject: maths , startTime: "07:30 :AM" , endTime: "08:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Wednesday" , teacher:vanajakshi , subject: ILanguage , startTime: "08:00 :AM" , endTime: "09:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Wednesday" , teacher:sheetal , subject: science , startTime: "09:00 :AM" , endTime: "10:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Wednesday" , teacher:kiran , subject: IILanguage , startTime: "10:30 :AM" , endTime: "11:30 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Wednesday" , teacher:naresh , subject: socialScience , startTime: "11:30 :AM" , endTime: "12:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Wednesday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Wednesday" , teacher:sheetal , subject: science , startTime: "02:00 :PM" , endTime: "03:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Wednesday" , teacher:mathew , subject: activities , startTime: "03:00 :PM" , endTime: "03:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Thursday" , teacher:archana , subject: IILanguage , startTime: "07:30 :AM" , endTime: "08:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Thursday" , teacher:vanajakshi , subject: maths , startTime: "08:00 :AM" , endTime: "09:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Thursday" , teacher:sheetal , subject: pt , startTime: "09:00 :AM" , endTime: "10:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Thursday" , teacher:kiran , subject: ILanguage , startTime: "10:30 :AM" , endTime: "11:30 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Thursday" , teacher:naresh , subject: maths , startTime: "11:30 :AM" , endTime: "12:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Thursday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Thursday" , teacher:sheetal , subject: pt , startTime: "02:00 :PM" , endTime: "03:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Thursday" , teacher:mathew , subject: activities , startTime: "03:00 :PM" , endTime: "03:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Friday" , teacher:sheetal , subject: science , startTime: "07:30 :AM" , endTime: "08:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Friday" , teacher:vanajakshi , subject: ILanguage , startTime: "08:00 :AM" , endTime: "09:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Friday" , teacher:vanajakshi , subject: clubAct , startTime: "09:00 :AM" , endTime: "10:00 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Friday" , teacher:kiran , subject: IILanguage , startTime: "10:30 :AM" , endTime: "11:30 :AM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Friday" , teacher:naresh , subject: socialScience , startTime: "11:30 :AM" , endTime: "12:30 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Friday" , teacher:archana , subject: maths , startTime: "01:00 :PM" , endTime: "02:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Friday" , teacher:vanajakshi , subject: clubAct , startTime: "02:00 :PM" , endTime: "03:00 :PM")).save()
		 cl5B.addToTimetable(new TimeTable(grade: cl5B , day: "Friday" , teacher:mathew , subject: activities , startTime: "03:00 :PM" , endTime: "03:30 :PM")).save()
		 User ravi , rani , manish , malini , mahadev , shashi
		
		 ravi = User.findByUsername("ravi@test.com")
		 rani = User.findByUsername("raani@test.com")
		 malini = User.findByUsername("malini@test.com")
		 mahadev = User.findByUsername("mahadev@test.com")
		 shashi = User.findByUsername("shashi@test.com")
		 manish = User.findByUsername("manish@test.com")
		 Conversation testconv = new Conversation(fromId:ravi.username , toId: rani.username ,fromName:ravi.name, toName:rani.name, title: "Test Conversation between ravi and raani" , inTrash: false,isRead: false,toDate: new Date(),fromDate: new Date())
		 testconv.addToMessages(new Message(fromId: "ravi" , toId: "rani" , messageText: "Haai raani" , messageTime:new Date() ))
		 .addToMessages(new Message(fromId: "raani" , toId: "ravi" , messageText: "Haai raviii" , messageTime: new Date()))
		 .save()
		 ravi.addToConversations(testconv)
		 rani.addToConversations(testconv)
		 ravi.save()
		 rani.save()
		 testconv = new Conversation(fromId:ravi.username , toId: rani.username ,fromName:ravi.name, toName:rani.name, title: "Test Conversation between ravi and raani" , inTrash: false,isRead: false,toDate: new Date(),fromDate: new Date())
		 testconv.addToMessages(new Message(fromId: "ravi" , toId: "rani" , messageText: "Again Haai raani" , messageTime:new Date() ))
		 .addToMessages(new Message(fromId: "raani" , toId: "ravi" , messageText: "Again Haai raviii" , messageTime: new Date()))
		 .save()
		 ravi.addToConversations(testconv)
		 rani.addToConversations(testconv)
		 ravi.save()
		 rani.save()
		 testconv = new Conversation(fromId: mahadev.username , toId: malini.username ,fromName:mahadev.name, toName:malini.name, title: "Message from Mahadev to Malini" , inTrash: false , isRead: false , toDate: new Date() , fromDate: new Date() )
		 .addToMessages(new Message(fromId: "Mahadev" , toId: "Malini" ,messageTime: new Date() , messageText: "Haai malini"))
		 .addToMessages(new Message(fromId: "Malini" , toId: "Mahadev" , messageText: "Hello mahadev" , messageTime: new Date()))
		 .save()
		 malini.addToConversations(testconv).save()
		 mahadev.addToConversations(testconv).save()
		 testconv = new Conversation(fromId: manish.username , toId: mathew.username ,fromName:manish.name, toName:mathew.name, title: "Message from Manish" , inTrash: false , isRead: false , toDate: new Date() , fromDate: new Date())
		 .addToMessages(new Message(fromId: "Manish" , toId: "Mathew" , messageText: "Hai this is Manush" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "Manish" , messageText: "Yes manish , mathew here" , messageTime:  new Date()))
		 .addToMessages(new Message(fromId: "Manish" , toId: "Mathew" , messageText: "How are you maathew" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "Manish" , messageText: "I am fine manish How are you" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Manish" , toId: "Mathew" , messageText: "I am also fine mathew , Good night" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "Manish" , messageText: "Good night manish...", messageTime: new Date()))
		 .save()
		 mathew.addToConversations(testconv).save()
		 manish.addToConversations(testconv).save()
		 testconv = new Conversation(fromId: shashi.username , toId: mathew.username ,fromName:shashi.name, toName:mathew.name, title: "Message from shashi" , inTrash: false , isRead: false , toDate: new Date() , fromDate: new Date())
		 .addToMessages(new Message(fromId: "shashi" , toId: "Mathew" , messageText: "Hai this is shashi" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "shashi" , messageText: "Yes shashi , mathew here" , messageTime:  new Date()))
		 .addToMessages(new Message(fromId: "shashi" , toId: "Mathew" , messageText: "How are you maathew" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "shashi" , messageText: "I am fine shashi How are you" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "shashi" , toId: "Mathew" , messageText: "I am also fine mathew , Good night" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "shashi" , messageText: "Good night shashi...", messageTime: new Date()))
		 .save()
		 mathew.addToConversations(testconv).save()
		 shashi.addToConversations(testconv).save()
		 testconv = new Conversation(fromId: malini.username , toId: mathew.username ,fromName:malini.name, toName:mathew.name, title: "Message from Malini" , inTrash: false , isRead: false , toDate: new Date() , fromDate: new Date())
		 .addToMessages(new Message(fromId: "Malini" , toId: "Mathew" , messageText: "Hai this is Manush" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "Malini" , messageText: "Yes malini , mathew here" , messageTime:  new Date()))
		 .addToMessages(new Message(fromId: "Malini" , toId: "Mathew" , messageText: "How are you maathew" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "Malini" , messageText: "I am fine malini How are you" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Malini" , toId: "Mathew" , messageText: "I am also fine mathew , Good night" , messageTime: new Date()))
		 .addToMessages(new Message(fromId: "Mathew" , toId: "Malini" , messageText: "Good night malini...", messageTime: new Date()))
		 .save()
		 mathew.addToConversations(testconv).save()
		 malini.addToConversations(testconv).save()
	
		 new Event(calendar_date: CalendarDate.today() ,grade: Grade.findByNameAndSection("5","A") , title: "Class PTA Meeting" , description: "Parents meeting of all 5 A students" , startTime: "Evening 3.30 " , endTime: "Evening 5.30"  , flag: "GRADE" ).save(flush: true)
		 new Event(calendar_date: CalendarDate.today() , title: "Sports Day" , description: "Annual Sports day" , startTime: "Morning 9.30 " , endTime: "Evening 3.00"  , flag: "SCHOOL" ).save()
		 new Event(calendar_date: CalendarDate.today() ,grade: Grade.findByNameAndSection("5","B") , title: "Class PTA Meeting" , description: "Parents meeting of all 5 B students" , startTime: "Evening 3.30 " , endTime: "Evening 5.30"  , flag: "GRADE" ).save(flush: true)
		 new Event(calendar_date: CalendarDate.today() , title: "Arts Day" , description: "Annual Arts day" , startTime: "Morning 11.30 " , endTime: "Evening 4.00"  , flag: "SCHOOL" ).save()
		 new Event(calendar_date: CalendarDate.getDate("08-04-2015") ,grade: Grade.findByNameAndSection("6","A") , title: "Class PTA Meeting" , description: "Parents meeting of all 6 A students" , startTime: "Evening 3.30 " , endTime: "Evening 5.30"  , flag: "GRADE" ).save(flush: true)
		 def monthly = new FeesTypeInterval(feesTypeInterval: "Monthly").save();
		 def quarterly = new FeesTypeInterval(feesTypeInterval: "Quarterly").save();
		 def yearly = new FeesTypeInterval(feesTypeInterval: "Yearly").save();
		 new FeesType(feesType: "Tution Fee").save();
		 new FeesType(feesType: "School Bus Fare").save()
		 feeSchedule: new FeesSchedule(interval:FeesTypeInterval.findByFeesTypeInterval("Yearly")).save()
		 feeSchedule: new FeesSchedule(interval: FeesTypeInterval.findByFeesTypeInterval("Monthly")).save()
		 def fs1 = FeesSchedule.get(1)
		 def fs2 = FeesSchedule.get(2)
		 new ClassFees(schoolClass: SchoolClass.findByClassName("5") , type: FeesType.get(1) , totalFee: 5000 , feeSchedule: fs1, percentage: 25) .save()
		 new ClassFees(schoolClass: SchoolClass.findByClassName("5") , type: FeesType.get(2) , totalFee: 500 , feeSchedule: fs2, percentage: 100) .save()
		 


	
   






















*/













































		JSON.createNamedConfig('thin') {
			it.registerObjectMarshaller( Grade ) { Grade grade ->

				def output = [:]
				output['grade'] = grade.name.toString()
				output['section'] = grade.section

				return output
			}
		}

		JSON.registerObjectMarshaller( Grade ) { Grade grade ->

			[
				gradeId: grade.gradeId.toString(),
				gradeName : grade.name?grade.name:(grade.students ?grade.students[0].grade?.name:""),
				section : grade.section ,
				classTeacher : (grade.classTeacherId) ?  Teacher.findById(grade.classTeacherId)?.teacherName  : 'None' ,
				classTeacherId: (grade.classTeacherId)? (grade.classTeacherId).toString() : null ,
				student : (grade.students) ?  grade.students?.collect{ Student std ->
					[
						studentId: std.studentId.toString(),
						registerNumber : std.registerNumber,
						studentName : std.studentName ,
						gender : std.gender ,
						present_address : std.present_address ,
						no_of_siblings : std.no_of_siblings.toString() ,
						dob : std.dob ,
						studentPhoto :servletContext.getContextPath()+ std.studentPhoto ,
						present_guardian : std.present_guardian ,
						grade : std.grade?.name.toString() ,
						section : std.grade?.section ,
						modeOfTransport:std.modeOfTransport,
						bloodGroup:std.bloodGroup,
						medicalCondition:std.medicalCondition,
						feeType:std.feeType,
						admDate:std.admDate,
						category:std.category,
						religion:std.religion,
						house:std.house,
						otherLang:std.otherLang,
						'father' :   [

							'name' : std.getFather()?.name ,
							'email':std.getFather()?.emailId ,
							'mob':std.getFather()?.mobileNumber ,
						],
						'mother' :   [

							'name' : std.getMother()?.name ,
							'email':std.getMother()?.emailId ,
							'mob':std.getMother()?.mobileNumber ,
						],
						'local_guardian' :   [

							'name' : (std.getLocalGuardian())? std.getLocalGuardian()?.name : "No local guardian" ,
							'email' : (std.getLocalGuardian())? std.getLocalGuardian()?.emailId :"No local guardia emailId",
							'mob' : (std.getLocalGuardian())? std.getLocalGuardian()?.emailId :"No local guardia Mobile No",					]
					] } : []]

		}

		JSON.createNamedConfig('homework') {
			it.registerObjectMarshaller( Homework ) { Homework home ->



				def output = [:]
				output['subject'] = home.subject
				output['dueDate'] = home.dueDate.format("dd-MM-yyyy")
				output['homeGivenDate'] = home.dateCreated.format("dd-MM-yyyy")
				output['details'] = home.message

				return output
			}
		}


		JSON.createNamedConfig('student') {
			it.registerObjectMarshaller( Student ) { Student student ->

				def output = [:]
				output['studentId'] = student.studentId.toString()
				output['studentName'] = student.studentName

				return output
			}
		}




		JSON.registerObjectMarshaller(Student) { Student student ->

			def output = [:]
			output['studentId'] = student.studentId.toString()
			output['studentName'] = student.studentName
			output['grade']=student.grade.name.toString()
			output['section']=student.grade.section
			output['classTeacherName']=student.grade.classTeacherName



			return output

		}
		JSON.registerObjectMarshaller(Subject) { Subject subject ->

			def output = [:]
			output['subjectId'] = subject.subjectId.toString()
			output['subjectName'] = subject.subjectName



			return output

		}

		JSON.createNamedConfig('msg') {
			it.registerObjectMarshaller( Message ) { Message msg ->

				return ['code': msg.code,
					'value': msg.value]
			}
		}


		JSON.registerObjectMarshaller(ExamSyllabus) { ExamSyllabus examSyllabus ->


			return  ['syllabus':  examSyllabus.syllabus,
				'subjectName': examSyllabus.subject.subjectName,


			]


		}
		/*	JSON.registerObjectMarshaller( Exam )
		 {
		 Exam e ->
		 return[
		 examId : e.examId.toString() ,
		 examName : e.examName ,
		 examType : e.examType ,
		 class : e.schoolclass?.className.toString() ,
		 grade : [gradeId : e.grade?.gradeId.toString() , gradeName: e.grade?.name , section : e.grade?.section] ,
		 examSchedule : e.examSubjectSchedule.collect()    { ExamSchedule es -> [ subject:[ subjectId:  es.subject?.subjectId.toString() ,
		 subjectName: es.subject?.subjectName ] ,
		 syllabus : [ id:es.subjectSyllabus?.id.toString() , syllabus: es.subjectSyllabus.syllabus] ,
		 examDate : es.startTime.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") ,
		 startTime : es.startTime.format("KK:mm a") ,
		 endTime: es.endTime.format("KK:mm a")]
		 } ,
		 results : e.results.collect() {
		 [ resultId : it.resultId.toString() , subjectId : it.subject?.subjectId.toString() , subjectName: it.subject?.subjectName , studentId: it.student?.studentId.toString() , studentName: it.student?.studentName , maxMarks: it.maxMarks.toString() , marks: it.marks.toString()]
		 }
		 ]
		 }*/







		JSON.registerObjectMarshaller(Message) { Message m ->
			return ['messageId' : m.messageId.toString() ,
				'messageText' : m.messageText ,
				'messageTime' : m.messageTime.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") ,
				'from' : m.fromId ,
				'to' : m.toId


			]
		}

		JSON.createNamedConfig('exam') {
			it.registerObjectMarshaller( Grade ) { Grade msg ->

				return ['exams': msg.exams
				]
			}
		}

		JSON.createNamedConfig('exams') {
			it.registerObjectMarshaller( Exam ) { Exam subject ->

				return  ['examId':  subject.examId?subject.examId.toString():'',
					'examType': subject.examType,
					'schedule':subject.examSubjectSchedule,


				]

			}
		}

		JSON.registerObjectMarshaller(Exam) { Exam subject ->


			return  ['examId':  subject.examId?subject.examId.toString():'',
				'examType': subject.examType,
				'schedule':subject.examSubjectSchedule,


			]

		}





		JSON.createNamedConfig('teacherC') {
			it.registerObjectMarshaller( Teacher ) { Teacher teach ->



				return  ['teacher':['teacherId': teach.id.toString(),
						'teacherName': teach.teacherName,
						'emailId':teach.teacherEmailId,
						'grades' : teach.grades.sort{it.classTeacherId}
					]]
			}
		}

		JSON.createNamedConfig('teacherSub') {
			it.registerObjectMarshaller( Teacher ) { Teacher teach ->



				return  [
					'subjects' : teach.subject
				]
			}
		}
		JSON.createNamedConfig('Success') {
			it.registerObjectMarshaller( Success ) { Success success ->

				def output = [:]
				output['success'] = 0
				output['failure'] = 1


				return output
			}
		}
		// Marshellers for classes

		JSON.registerObjectMarshaller( Guardian ) { Guardian g ->
			return [

				name : g.name,
				educational_qualification : g.educational_qualification,
				profession : g.profession,
				username : g.designation,
				mobileNumber : g.mobileNumber,
				emailId : g.emailId,
				officeNumber : g.officeNumber,
				children : g.getChildren().collect(){
					['studentId': it.studentId?.toString(),
						'registerNumber': it.registerNumber,
						'studentName' : it.studentName ,
						'grade' : it.grade?.name.toString(),
						'section' : it.grade?.section,
						'gender' : it.gender?it.gender:'',
						'no_of_siblings' : it.no_of_siblings.toString() ,
						'dob' : it.dob.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
						'studentPhoto'  :it.studentPhoto?it.studentPhoto:'',
						  'bloodGroup' :it.bloodGroup?it.bloodGroup : "No Data" 
						
						]

				}


			]
		}

		JSON.createNamedConfig('ParentAccInfo') {
			it.registerObjectMarshaller( Guardian ) { Guardian g ->



				return  ['accountInfo':['username': g.username,
						'name': g.name,
						'educational_qualification' : g.educational_qualification ,
						'profession' : g.profession,
						'designation' : g.designation ,
						'mobileNumber' : g.mobileNumber ,
						'emailId' : g.emailId,
						'officeNumber' : g.officeNumber,
						'numberOfChildren' : g.getChildren()?.size().toString(),

					]

				]


			}
		}


		JSON.createNamedConfig('getChildren') {
			it.registerObjectMarshaller( Student ) { Student s ->



				return                  [
					'studentId': s.studentId?.toString(),
					'registerNumber': s.registerNumber,
					'studentName' : s.studentName ,
					'grade' : s.grade?.name.toString(),
					'section' : s.grade?.section,
					'gender' : s.gender,
					'present_address' : s.present_address ,
					'no_of_siblings' : s.no_of_siblings.toString() ,
					'dob' : s.dob.format("dd-MM-yyyy"),
					'age' : s.getAge() ,
					'present_guardian' : s.present_guardian


				]
			}
		}


		JSON.createNamedConfig('studentDetail') {
			it.registerObjectMarshaller( Student ) { Student s ->



				return                  [
					'studentId': s.studentId?.toString(),
					'registerNumber': s.registerNumber,
					'studentName' : s.studentName ,
					'grade' : s.grade?.name.toString(),
					'section' : s.grade?.section,
					'gender' : s.gender,
					'present_address' : s.present_address ,
					'no_of_siblings' : s.no_of_siblings.toString() ,
					'dob' : (String)s.dob,
					'age' : s.getAge() ,
					'house': s.house,
					'otherLang': s.otherLang,
					'present_guardian' : s.present_guardian,
					'father' :   [
						'id' : s.getFather()?.id.toString(),
						'name' : s.getFather()?.name ,
					],
					'mother' :   [
						'id' : s.getMother()?.id.toString(),
						'name' : s.getMother()?.name ,
					],
					'local_guardian' :   [
						'id' : (s.getLocalGuardian())? s.getLocalGuardian()?.id.toString() : "No local guardian" ,
						'name' : (s.getLocalGuardian())? s.getLocalGuardian()?.name : "No local guardian" ,
					]

				]
			}
		}





		JSON.registerObjectMarshaller( Student ) { Student s ->
			return [

				studentId : s.studentId.toString() ,
				registerNumber : s.registerNumber,
				studentName : s.studentName ,
				gender : s.gender ,
				present_address : s.present_address ,
				no_of_siblings : s.no_of_siblings.toString() ,
				dob : (String)s.dob ,
				studentPhoto : s.studentPhoto ,
				present_guardian : s.present_guardian ,
				grade : s.grade?.name.toString() ,
				section : s.grade?.section ,
				father_id:s.father?.id.toString(),
				father: s?.getFather() ,
				mother_id:s.mother?.id.toString(),
				mother: s?.getMother() ,
				local_guardian_id:s.localGuardian?.id.toString(),
				local_guardian: (s.getLocalGuardian()) ? s.getLocalGuardian() : "No local guardian" ,
				modeOfTransport:s.modeOfTransport,
				bloodGroup:s.bloodGroup,
				medicalCondition:s.medicalCondition,
				feeType:s.feeType,
				category:s.category,
				religion:s.religion,
				admDate:s.admDate,
				house:s.house,
				otherLang: s.otherLang
			]
		}

		JSON.registerObjectMarshaller( TimeTable ) { TimeTable t ->
			return [
				subject : t.subject?.subjectName ,
				day : t.day ,
				teacher: t.teacher?.teacherName ,
				grade: t.grade?.name.toString() ,
				section: t.grade?.section



			]}

		JSON.registerObjectMarshaller( Address ) { Address a ->
			return [
				id : a.id.toString(),
				address : a.address ,
				place : a.place ,
				landmark: a.landmark

			]}


		JSON.registerObjectMarshaller( Homework ) { Homework h ->
			return [

				'homeworkList' : [

					homeworkId: h.homeworkId.toString(),
					grade : h.grade?.name.toString(),
					section : h.grade?.section ,
					subject: h.subject ,
					dueDate : h.dueDate.format("dd-MM-yyyy") ,
					homework: h.homework ,
					dateCreated : h.dateCreated.format("dd-MM-yyyy") ,
					student : h.student?.studentName ,
					studentId : h.student?.studentId.toString() ,
					message : h.message ,
					gradeFlag : h.gradeFlag


				]]
		}



		JSON.createNamedConfig('getTimeTable') {
			it.registerObjectMarshaller( TimeTable ) { TimeTable t ->



				return  [
					subject: t.subject?.subjectName,
					teacher: t.teacher?.teacherName ,
					teacherId: t.teacher?.teacherId.toString(),
					teacherPhoto: t.teacher?.teacherPhoto,
					startTime : t.startTime ,
					endTime : t.endTime
				]
			}
		}


		JSON.createNamedConfig('studentHomework') {
			it.registerObjectMarshaller( Homework ) { Homework h ->



				return  [
					'homeworkId' : h.homeworkId.toString() ,
					'subject'    : h.subject ,
					'dueDate'    : h.dueDate.format("dd-MM-yyyy") ,
					'dateCreated': h.dateCreated.format("dd-MM-yyyy") ,
					'message'    : h.message,
					'homework'   : h.homework
				]
			}
		}


		JSON.registerObjectMarshaller( Conversation  )
		{   cnv ->


			 return [
						 'threadId': cnv.threadId.toString(),
						 'numberOfMessages' : cnv.messages.size(),
						 //'fromDate': cnv.fromDate?.format('EEEE, dd MMMM yyyy, hh:mm:ss a'),
						 'fromId': cnv.fromId ,
						 'senderName': cnv.senderName ,
						 'receiverName': cnv.receiverName ,

						 'toId': cnv.toId,
						 'inTrash': cnv.inTrash,
						 'isRead': cnv.isRead,
						 'title': cnv.title,
						 'toDate': cnv.toDate?.format('EEEE, dd MMMM yyyy, hh:mm:ss a'),
						 'messages' : cnv.messages


					]

		}


		JSON.registerObjectMarshaller( Conversation  )
		{   cnv ->



			return [
				'threadId': cnv.threadId.toString(),
				'numberOfMessages' : cnv.messages.size(),
				//'fromDate': cnv.fromDate?.format('EEEE, dd MMMM yyyy, hh:mm:ss a'),
				'fromId': cnv.fromId ,
				'toId': cnv.toId,
				'inTrash': cnv.inTrash,
				'isRead': cnv.isRead,
				'title': cnv.title,
				'toDate': cnv.toDate?.format('EEEE, dd MMMM yyyy, hh:mm:ss a'),
				'messages' : cnv.messages



			]

		}
		JSON.createNamedConfig('msgList')
		{
			it.registerObjectMarshaller( Message ) { Message m ->
				return [
					'messageId' : m.messageId.toString(),
					'fromId' : m.fromId,
					'toId' : m.toId ,
					'messageText' : m.messageText,
					'messageTime' : m.messageTime?.format('EEEE, dd MMMM yyyy, hh:mm:ss a')

				]}
		}









		JSON.registerObjectMarshaller( Event )
		{ e ->


			[
				'eventId' : e.eventId.toString(),
				'title' : e.title ,
				'description' : e.description ,
				'eventDate' : e.calendar_date?.calendar_date.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") ,
				'startTime' : e.startTime ,
				'endTime' : e.endTime,
				'eventType': (e.flag == 'SCHOOL') ? "School Event" : "Class Event of class  "+e.grade.name+" "+e.grade.section

			]





		}



			JSON.createNamedConfig('TeacherListForParent')
		{
			it.registerObjectMarshaller(GradeTeacherSubject) { GradeTeacherSubject g ->

				return [ 'gradeId' : g.grade?.gradeId.toString() ,
					'gradename': g.grade?.name.toString(),
					'username' :g.teacher?.username,
					'displayName':g.subject?.subjectName +' : '+ g.grade?.name.toString()+ ' : ' + g.grade?.section +': :'+g.teacher.teacherName
					


				]
		}
		}

		JSON.createNamedConfig('ParentListForTeacher')
		{
			it.registerObjectMarshaller(Guardian) { Guardian t ->

				return [ 'id' : t.id.toString() ,
					'username' : t.username,
					'displayName' : t.name,
					'EmailId' : t.emailId ,
					'phoneNo' : t.mobileNumber ,

				]


			}
		}


		JSON.createNamedConfig('TeachersSubjects')
		{
			it.registerObjectMarshaller(GradeTeacherSubject) { GradeTeacherSubject g ->

				return [ 'gradeId' : g.grade?.gradeId.toString() ,
					'gradename': g.grade?.name.toString(),
					'section':g.grade?.section,
					'subjectName':g.subject?.subjectName ,
					'subjectId':g.subject?.subjectId.toString()


				]

			}
		}





		JSON.registerObjectMarshaller( Attendance ) { Attendance a ->
			return [



				'attendanceId' : a.attendanceId.toString(),
				'date' : a.date.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
				'grade' : a.grade?.name.toString() ,
				'section' : a.grade?.section,
				'total_students' : a.grade?.students.size().toString(),
				'total_absent' : a.absentees.size().toString() ,
				'absentees':a.absentees



			]
		}

		JSON.createNamedConfig('absentees')
		{
			it.registerObjectMarshaller( Attendance ) { Attendance a ->
				return [


					"attendanceDoneFlag":"Y",
					'attendanceId' : a.attendanceId.toString(),
					'date' : a.date.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
					'grade' : a.grade?.name.toString() ,
					'section' : a.grade?.section,
					'total_students' : a.grade?.students.size().toString(),
					'total_absent' : a.absentees.size().toString() ,
					'absentees':a.absentees.collect() { Student s ->
						['stdentId' : s.studentId.toString() ,
							'studentName':s.studentName
						]},
					'students':a.grade?.students.collect() { Student s ->
						['stdentId' : s.studentId.toString() ,
							'studentName':s.studentName
						]

					}
				]

			}
		}

		JSON.createNamedConfig('notAttendance')
		{
			it.registerObjectMarshaller( Grade ) { Grade a ->
				return [


					"attendanceDoneFlag":"N",
					'attendanceId' : "",
					'date' : "",
					'grade' : a?.name.toString() ,
					'section' : a?.section,
					'total_students' : a?.students.size().toString(),
					'total_absent' :"" ,
					'absentees':"",
					'students':a?.students.collect() { Student s ->
						['stdentId' : s.studentId.toString() ,
							'studentName':s.studentName
						]

					}
				]

			}
		}


		JSON.createNamedConfig('absent')
		{
			it.registerObjectMarshaller(Student) { Student s ->

				return ['stdentId' : s.studentId.toString() ,
					'studentName':s.studentName
				]

			}
		}


		JSON.registerObjectMarshaller( TimeTable ) { TimeTable t ->
			return [



				'id' : t.id.toString() ,
				'gradeId' : t.grade.gradeId.toString(),
				'grade' : t.grade.name?.toString(),
				'section' : t.grade?.section ,
				'subject' : t.subject?.subjectName,

				'day' : t.day ,
				'startTime': t.startTime ,
				'endTime' : t.endTime


			]
		}



		JSON.createNamedConfig('teacherWeekTT')
		{
			it.registerObjectMarshaller(Teacher) { Teacher t ->
				return [
					'teacherId' : t.id.toString() ,
					'teacherName'  : t.teacherName ,
					'timetable' : t.timetables
				]

			}


		}



		JSON.createNamedConfig('TeachergetTimeTable') {
			it.registerObjectMarshaller( TimeTable ) { TimeTable t ->



				return  [
					grade: t.grade?.name.toString() ,
					section: t.grade?.section ,
					subjectName: t.subject?.subjectName.toString(),
					subjectId: t.subject?.subjectId.toString() ,
					startTime : t.startTime ,
					endTime : t.endTime
				]
			}
		}


		//NEW
		JSON.registerObjectMarshaller( Teacher )
		{ Teacher t ->
			return [
				teacherId: t.id.toString() ,
				teacherName: t.teacherName ,
				teacherEmailId: t.teacherEmailId ,
				phoneNo: t.phoneNo,
				school_id : t.school_id.toString(),
				teacherPhoto: t.teacherPhoto,
				empCode:t.empCode,
				doj:t.doj,
				DOB:t.DOB,
				quali:t.qualification,
				post:t.post,
				addr:t.tAddr



			]
		}


		JSON.registerObjectMarshaller( SchoolClass  )
		{ SchoolClass s ->
			return [
				school_class_id: s.classId.toString() ,
				className: s.className.toString(),
				class_tags: s.classTags ,
				school : s.school ,
				grades : s.grades.collect() { Grade g ->
					[gradeId: g.gradeId,
						name : g.name ,
						section: g.section
					]
				}


			]
		}









		JSON.registerObjectMarshaller( ExamResult  )
		{ ExamResult e ->
			return [
				resultId : e.resultId.toString() ,
				exam : [ examId : e.exam?.examId ,
					examName : e.exam?.examName ,
					examType : e.exam?.examType ,
					grade : e.exam?.grade.gradeId.toString()
				] ,

				subject : [ subjectId : e.subject?.subjectId.toString() ,
					subjectName: e.subject?.subjectName
				] ,
				student : [studentId : e.student?.studentId.toString() ,
					studentName: e.student?.studentName
				] ,
				maxMarks : e.maxMarks,
				marks : e.marks ,
				grade : e.grade
			]
		}

		JSON.registerObjectMarshaller( Exam )
		{ Exam e ->
			return[

				examId : e.examId.toString() ,
				examName : e.examName ,
				examType : e.examType ,
				grade : [gradeId : e.grade?.gradeId.toString() , gradeName: e.grade?.name , section : e.grade?.section] ,
				examSchedule : e.examSubjectSchedule.collect()    { ExamSchedule es ->
					[ subject:[ subjectId:  es.subject?.subjectId.toString() ,
							subjectName: es.subject?.subjectName ] ,
						syllabus : [ id:es.subjectSyllabus!=null?es.subjectSyllabus?.id.toString():'' , syllabus:es.subjectSyllabus!=null? es.subjectSyllabus?.syllabus:''] ,
						examDate : es.startTime.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") ,
						startTime : es.startTime.format("KK:mm a") ,
						endTime: es.endTime.format("KK:mm a")]
				} ,
				results : e.results.collect() {
					[ resultId : it.resultId.toString() , subjectId : it.subject?.subjectId.toString() , subjectName: it.subject?.subjectName , studentId: it.student?.studentId.toString() , studentName: it.student?.studentName , maxMarks: it.maxMarks.toString() , marks: it.marks.toString()]
				}


			]




		}


		JSON.registerObjectMarshaller( Department )
		{ Department d ->
			return [
				dept_id : d.dept_id.toString() ,
				dept_name : d.dept_name ,
				dept_tags : d.dept_tags ,
				school_id : d.school_id ,
				teachers:d.teachers.collect(){

					[ teacherId: it?.id ,
						teacherName: it?.teacherName
					]
				}

			]
		}



		JSON.registerObjectMarshaller( Subject )
		{ Subject s ->
			return [
				subjectId: s.subjectId.toString() ,
				subjectName: s.subjectName
			]
		}

		JSON.registerObjectMarshaller( GradeTeacherSubject )
		{ GradeTeacherSubject g ->
			return [
				grade : [gradeId: g.grade.gradeId.toString() , gradeName: g.grade.name.toString(), section: g.grade.section],
				subject :[subjectId: g.subject.subjectId.toString() ,
					subjectName: g.subject.subjectName ,
				],
				teacher: [teacherId: g.teacher.id.toString() ,
					teacherName: g.teacher.teacherName ,
					teacherEmailId: g.teacher?.teacherEmailId ,
					teacherPhoto: g.teacher?.teacherPhoto

				]



			]

		}



		JSON.registerObjectMarshaller( ClassFees )
		{ ClassFees cf ->
			return [
				id:cf.id.toString(),
				schoolClass: [classId:cf.schoolClass.classId.toString() , className: cf.schoolClass.className] ,
				type : [feesTypeId:cf.type.feesTypeId.toString() , feesType:cf.type.feesType],
				totalFee : cf.totalFee.toString(),
				feeSchedule : cf.feeSchedule



			]


		}




		JSON.createNamedConfig('conversationVer1')
		{


			it.registerObjectMarshaller( Conversation  )
			{   cnv ->



				return [
					'threadId': cnv.threadId.toString(),
					'numberOfMessages' : cnv.messages.size().toString(),
					//'fromDate': cnv.fromDate?.format('EEEE, dd MMMM yyyy, hh:mm:ss a'),
					'fromId': cnv.fromId ,
					'toId': cnv.toId,
					'fromName':cnv.fromName,
					'toName':cnv.toName,
					'inTrash': cnv.inTrash,
					'isRead': cnv.isRead,
					'title': cnv.title,
					'toDate': cnv.toDate?.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
					'messages' : cnv.messages.collect(){

						[
							'messageId' : it.messageId.toString(),
							'fromId' : it.fromId,
							'toId' : it.toId ,
							'messageText' : it.messageText,
							'messageTime' : it.messageTime?.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

						]
					}


				]

			}

			JSON.registerObjectMarshaller(ExamSchedule) { ExamSchedule exSchedule ->

				return  ['subjectName':  exSchedule.subject.subjectName,
					'subjectSyllabus':exSchedule.subjectSyllabus.syllabus,
					'teacherName':exSchedule.teacher.teacherName,
					'examStartTime':exSchedule.startTime? exSchedule.startTime.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") :'date not',

					'examEndTime':exSchedule.endTime? exSchedule.endTime.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") :'date not' ]



			}



			
			JSON.registerObjectMarshaller(Notice)
			{
				Notice n ->
					return [
							noticeId   : n.noticeId.toString(),
							topic      : n.topic,
							message    : n.message,
							senderName : n.senderName,
							designation: n.designation,
							salutation : n.salutation,
							createBy   : n.createBy,
							signoffWord: n.signoffWord,
							date       : n.date
					]
			}



			JSON.registerObjectMarshaller( FileManager  )
			{ FileManager e ->
				return [
					fileType:e.fileGroupType,
					fileName:e.fileGroupName,
					coverpicUrl: e.albumCoverUrl,
					filecount: e.fileCount,
					postedDate: e.postedDate,
					files: e.files.collect()    { MyChildFile es ->
						[file: [ fileName:  es.fileName,
								filePath: es.filePath,
								description: es.description
							] ] }
				]
			}
			JSON.registerObjectMarshaller( TimeTable ) { TimeTable t ->
				return [


					'id' : t.id.toString() ,
					'gradeId' : t.grade.gradeId.toString(),
					'grade' : t.grade.name?.toString(),
					'section' : t.grade?.section ,
					'subject' : t.subject?.subjectName,
					'teacher' : t.teacher?.teacherName ,

					'day' : t.day ,
					'startTime': t.startTime ,
					'endTime' : t.endTime


				]
			}
			JSON.registerObjectMarshaller( TimeTable ) { TimeTable t ->
				return [



						'id' : t.id.toString() ,
						'gradeId' : t.grade.gradeId.toString(),
						'grade' : t.grade.name?.toString(),
						'section' : t.grade?.section ,
						'subjectId':t.subject?.subjectId.toString() ,
						'subject' : t.subject?.subjectName,
						'teacherId':t.teacher?.id.toString() ,
						'teacher' : t.teacher?.teacherName ,
						'day' : t.day ,
					   'startTime': t.startTime ,
					   'endTime' : t.endTime


				]
			}

			JSON.createNamedConfig('busDetails')
			{
	
	
				it.registerObjectMarshaller( BusLocation  )
				{
		 t ->
				return [
			     "status":"success",
				 "message":"route present",
				 "location":[
				  "driverName":t.driverName,
				  "driverPhonenumber": t.driverPhonenumber,
				  "intialLatitude":t.intialLatitude!=null?t.intialLatitude:"",
				  "intialLongitute": t.intialLongitute!=null?t.intialLongitute:"",
				  "lastUpdated":t.lastUpdated,
				  "currentLatitude":t.latitude,
				  "currentLongitute": t.longitute
				  ]
				
			  ]
			}
			}
			def destroy = {
			}
		}
	}
}
