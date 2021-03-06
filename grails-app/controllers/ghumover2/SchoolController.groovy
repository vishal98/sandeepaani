package ghumover2

import grails.converters.JSON

class SchoolController {
	
	def addSchool() {
		Map ob = new HashMap();
		try{
			
			def school = new School()
			school.schoole_name = params.schoole_name;
			school.address = params.address
			school.landmark = params.landmark
			school.landline = params.landline
			
			 school.save(flush:true);
				
					ob.put("status", true)
					ob.put("message", "School created success fully having id "+school.school_id)
		}catch(Exception e){
		ob.put("status", false)
		ob.put("message", "failed due to "+e.getMessage())
		}
		
	
		
	 render ob as JSON
	}
	
	def addDepartMent() {
		
		Map ob = new HashMap();
		try{
			
			   Long school_id = Long.parseLong(params.school_id)
		
		def dept = new Department()
		dept.dept_name = params.dept_name;
		
		dept.school_id = school_id
		 dept.save(flush:true);
				
					ob.put("status", true)
					ob.put("message", "department created  having id  "+dept.dept_id)
		}catch(Exception e){
		ob.put("status", false)
		ob.put("message", "failed due to "+e.getMessage())
		}
	
		render ob as JSON
	}

    def index() { }
}
