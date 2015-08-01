package ghumover2

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
import sun.misc.BASE64Decoder

class FileManagerReadController {

   
	
	def testSMS() {
		def rest = new RestBuilder()
		def resp = rest.post("http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=vishal.sujanian@gmail.com:247619&senderID=TEST SMS&receipientno=9886326669&dcs=0&msgtxt=This is Test message for vishal&state=4")

			
		
		
		System.out.print("resp val : "+resp)
		render resp as JSON
	
	
		
		//render (view:'/')
	}
	
		def updateProfile(){
	
		println  "here"
		println params.data
		
		def params = JSON.parse(params.data)
		Long studentId = Long.parseLong(params.studentId)
		
		println studentId
		
	//Long studentId = Long.parseLong(params.data)
		FileInputStream fis = null;
		try {
			//store file the get data from that file
			def f = request.getFile('file')
			//validate file or do something crazy hehehe
			String albumTitle="test"
		//	String albumTitle = params.albumTitle
			//now transfer file
			def webrootDir = servletContext.getRealPath("/")+"images/profile" //app directory

			File file = new File(webrootDir)
			if(!file.exists()){
				file.mkdirs();
			}
			println servletContext.getContextPath()
			
			File fileDest = new File(webrootDir,f.getOriginalFilename())
			MyChildFile fil =new MyChildFile()
			//file.setFileId(file.name)
			String filename=f.getOriginalFilename()
			fil.setFileName(filename)
			fil.setFilePath(fileDest.path)
			fil.setDescription("profilepic")
			f.transferTo(fileDest)
			
			Student.executeUpdate("update Student set  studentPhoto = 'images/profile/"+filename +"' where studentId ="+studentId)
		
		
			fil.save()
			def result = [:]
			
			result['status'] = 'success'
			result['message']="pic saved successfully"
			render result as JSON
			
		}
		catch(Exception e){
				println e.getMessage();
	
	
			}
	}
	
	
		def getAlbums(){	
			def output=[:]
			
			def album=FileManager.getAll()
			
			if(album==null ||album.size==0){
				FileManager [] fileManagerList=new FileManager()
				FileManager fileManager=new FileManager()
				fileManager.setFileGroupType("test")
				fileManager.setFileGroupName("test")
				fileManager.setAlbumCoverUrl("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/011.JPG")
				fileManager.setPostedDate(Calendar.getInstance().getTime())
				fileManager.setFileCount("1")
				fileManagerList[0]=fileManager
				render fileManagerList as JSON
				
			}
			render album as JSON
			//JSON.use('getFiles'){render album as JSON}
			//JSON.use('getFiles'){ render album as JSON}
		}
		
		

		def readImage1(){
			
				FileManager [] fileManagerList=new FileManager()
				FileManager fileManager=new FileManager()
				fileManager.setFileGroupType("Album")
				fileManager.setFileGroupName("Children Day- Euro School")
				fileManager.setAlbumCoverUrl("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/011.JPG")
				fileManager.setPostedDate(Calendar.getInstance().getTime())
				fileManager.setFileCount("2")
				
				
			
				MyChildFile file =new MyChildFile()
				file.setFileId("123")
				file.setFileName("Seeya Dancing")
				file.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/013.JPG")
				file.setDescription("schoolImage")
				
				
				
				MyChildFile file2 =new MyChildFile()
				file2.setFileId("1243")
				file2.setFileName("Seeya profilec ")
				file2.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/005.JPG")
				file2.setDescription("schoolImage2")
				
				MyChildFile file3 =new MyChildFile()
				file3.setFileId("1243")
				file3.setFileName("Sidhu")
				file3.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/006.JPG")
				file3.setDescription("schoolImage2")
				
				MyChildFile file4 =new MyChildFile()
				file4.setFileId("1243")
				file4.setFileName("Seeya Similing")
				file4.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/007.JPG")
				file4.setDescription("schoolImage2")
				
				MyChildFile file5 =new MyChildFile()
				file5.setFileId("1243")
				file5.setFileName("Seeya")
				file5.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/008.JPG")
				file5.setDescription("schoolImage2")
				
				MyChildFile file6 =new MyChildFile()
				file6.setFileId("1243")
				file6.setFileName("Indepedence Day")
				file6.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/009.JPG")
				file6.setDescription("schoolImage2")
				
				
				MyChildFile file7 =new MyChildFile()
				file7.setFileId("1243")
				file7.setFileName("Dance Program")
				file7.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/010.JPG")
				file7.setDescription("schoolImage2")
				
				MyChildFile file8 =new MyChildFile()
				file8.setFileId("1243")
				file8.setFileName("Annual Day")
				file8.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/011.JPG")
				file8.setDescription("schoolImage2")
				
				MyChildFile file9 =new MyChildFile()
				file9.setFileId("1243")
				file9.setFileName("student")
				file9.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/012.JPG")
				file9.setDescription("schoolImage2")
				
				MyChildFile file10 =new MyChildFile()
				file10.setFileId("1243")
				file10.setFileName("Seeya Dancing")
				file10.setFilePath("https://s3-ap-southeast-1.amazonaws.com/gotomychild/app/js/images/005.JPG")
				file10.setDescription("schoolImage2")
				
				fileManager.addToFiles(file)
				fileManager.addToFiles(file2)
				fileManager.addToFiles(file3)
				fileManager.addToFiles(file4)
				fileManager.addToFiles(file5)
				fileManager.addToFiles(file6)
				fileManager.addToFiles(file7)
				fileManager.addToFiles(file8)
				fileManager.addToFiles(file9)
				fileManager.addToFiles(file10)
				
				fileManagerList[0]=fileManager
				
				render  fileManagerList as JSON
			}
		
		
		
	
}