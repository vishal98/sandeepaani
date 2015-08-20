package ghumover2


import grails.rest.Resource
@Resource(formats=['json', 'xml'])
class Route {
	Long   routeId
	String  deviceNo
	String routeName
	String schoolId
	
	static hasMany = [locationDetails:BusLocation]
	static mapping = { id generator: 'increment',name: 'routeId'
		
		
	}
    static constraints = {

	//	busNo(nullable:true)
		routeName(nullable:true)
		deviceNo(nullable:true)
	
    }
}
