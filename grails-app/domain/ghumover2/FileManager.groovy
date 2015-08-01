package ghumover2

import ghumover2.MyChildFile;
import ghumover2.User;
import java.sql.Timestamp

class FileManager {

	int fileGroupId
	String fileGroupType//school album,personal files
	String fileGroupName
	String albumCoverUrl
	String fileCount
	Date postedDate
	User user
	static hasMany = [files:MyChildFile]
	
	static mapping ={
		id generator: 'increment',name: 'fileGroupId'
		
	
		}
	
    static constraints = {
		user(nullable:true)
    }
}
