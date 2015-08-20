package ghumover2

import java.util.concurrent.TimeUnit;

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder

class LocationDetailsController {

    def index() { }
	def 	getlocationDetails(){
		
		  def studentId=Long.parseLong(params.studentId)
		
		
		
		def msg=[]
		def output = [:]
		List<Route> routes=Route.executeQuery("from Route as r where r.routeId=(select routeId from Student s where s.studentId=?)",studentId)
		if(routes!=null&&routes.size()>0){
		def 	route=routes.get(0)
		
		def loc=route.locationDetails
		if(loc!=null)
		{
			 
		//	def currentDate =  new Date()
			
		//	def difference = currentDate - loc.lastUpdated
//			if (difference < TimeUnit.MINUTES.toMillis(5)) {
				//loc=callBusApi(route.deviceNo,loc)
				
				JSON.use('busDetails'){
				
				render loc as JSON
	//			}
			}
			
		}}
		
		output['status'] = 'error'
		output['message'] = 'student transport details not found'
		msg<<output
		render  msg as JSON

		
}
	
	def callBusApi(def deviceName,BusLocation loc){
		def rest = new RestBuilder()
		def geoDetails=[]
	
		def resp = rest.get("http://media.zeboba.com:8080/stream?devicename="+deviceName)
		
		if(resp!=null&& resp.json!=null){
		
	 geoDetails=resp.json.geo_details
	  
        if(geoDetails!=null&& geoDetails.length()>0){
			loc.latitude=geoDetails[0].latitude
			loc.latitude=geoDetails[0].longitude
			loc.lastUpdated=new Date("2015-06-25 11:45:56")
			loc.save()
			
			
        }
			return loc
		}	
	
	}
}
