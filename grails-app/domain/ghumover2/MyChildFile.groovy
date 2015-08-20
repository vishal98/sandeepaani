package ghumover2


import java.sql.Timestamp;

class MyChildFile {

	Long fileId
	String fileName
	String filePath
	String fileType
	String description
	Timestamp accessStart
	Timestamp archiveDatetime
	Timestamp accessEnd
	String statusCode
	User createdByUserId
	String isActive
	Timestamp creationdate
	Timestamp lastModifiedDatetime
	String lastModifiedBy
	
	static mapping ={
		id generator: 'increment',name: 'fileId'
		
	
		}
	
	static hasMany = [tags:Tag]
	static belongsTo = [fileManager:FileManager]
	
    static constraints = {
		accessStart(nullable: true)
		fileType(nullable: true)
	 description(nullable: true)
	accessStart(nullable: true)
		archiveDatetime(nullable: true)
		 accessEnd(nullable: true)
		statusCode(nullable: true)
		 createdByUserId(nullable: true)
		 isActive(nullable: true)
		 creationdate(nullable: true)
		 lastModifiedDatetime(nullable: true)
		 lastModifiedBy(nullable: true)
		}
	
}
