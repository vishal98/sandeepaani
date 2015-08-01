package ghumover2

import grails.plugin.aws.s3.S3FileUpload;
import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
import sun.misc.BASE64Decoder

class FileManagerWriteController {

	S3FileUpload st = new S3FileUpload()
	def fg = st.acl
	
	public def upload(){
	def s3file = new MyChildFile("testupload.txt").s3upload {
    path "Test1/"
}
	}
	
	def updateProfilePic(){
		

			
			//def params = JSON.parse(params.data)
			Long studentId = Long.parseLong(params.studentId)
			String encoded = params.studentPhoto
			
			println studentId
			
		//Long studentId = Long.parseLong(params.data)
			FileInputStream fis = null;
			try {
			
			String idl=params.studentId
		      
			
					//validate file or do something crazy hehehe
					String url="images/profile/"
					//now transfer file
					def webrootDir = servletContext.getRealPath("/")+url //app directory
		
					File file = new File(webrootDir)
					if(!file.exists()){
						file.mkdirs();
					}
					String filePath='/'+url+idl+".png"
					String path=webrootDir+idl+".png"
					encoded.replaceAll("\r", "")
					encoded.replaceAll("\n", "")
					byte[] decoded = encoded.decodeBase64()
					new File(path).withOutputStream {
					  it.write(decoded);
					}
					
	     
			
				
				Student.executeUpdate("update Student set  studentPhoto ='"+filePath+ "' where studentId ="+studentId)
			
					def result = [:]
			
			result['status'] = 'success'
			result['message']="pic saved successfully"
			result['imageUrl']=filePath
		    render result as JSON
				
			}
			catch(Exception e){
					println e.getMessage();
		
		
				}
		}
 
	def saveAlbumFiles(){
	
		def params = JSON.parse(params.data)
		String title = params.title
		
	
		FileInputStream fis = null;
		try {
			//store file the get data from that file
			def f = request.getFile('file')
			//validate file or do something crazy hehehe
			String albumTitle="test"
	
			//now transfer file
			String filepath="images/albums/"+title;
			def webrootDir = servletContext.getRealPath("/")+filepath //app directory
			String filename=f.getOriginalFilename()
			String url='/'+filepath+'/'+filename
			
			FileManager fileManager=FileManager.findByFileGroupName(title)
			File file = new File(webrootDir)
			if(!file.exists()){
				file.mkdirs();
			}
				if(fileManager==null){
					fileManager=new FileManager()
					fileManager.setFileGroupType("Albums")
					fileManager.setFileGroupName(title)
					fileManager.setAlbumCoverUrl(url)
					fileManager.setPostedDate(Calendar.getInstance().getTime())
					fileManager.setFileCount("0")
					fileManager.save(flush:true)
			
				
				
			}
		
			
			
			File fileDest = new File(webrootDir,f.getOriginalFilename())
			MyChildFile fil =new MyChildFile()
			
			fil.setFileName(filename)
			fil.setFilePath(url)
			fil.setDescription("profilepic")
			f.transferTo(fileDest)
			fil.fileManager=fileManager
			fil.save()
			fileManager.addToFiles(fil)
			fileManager.save()
			
			def result = [:]
			
			result['status'] = 'success'
			result['message']="pic saved successfully"
			render result as JSON
			
		
			
		}
		catch(Exception e){
				println e.getMessage();
	
	
			}
		
	
	}
}
