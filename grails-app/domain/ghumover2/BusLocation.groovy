package ghumover2

import java.sql.Timestamp;

import grails.rest.Resource

/**
 * Created by sarath on 4/8/15.
 */
@Resource(formats=['json', 'xml'])
class BusLocation {
	Long busLocationId
   //String locationData
   String longitute
   String latitude
   String intialLongitute
   String intialLatitude
   String driverName
   String driverPhonenumber
   Date lastUpdated
   Date creationDate
   Route route
   
   static belongsTo = [route:Route]
   static mapping = { id generator: 'increment',name: 'busLocationId'
   }


        static constraints = {
     //  locationData(nullable: true)
    //   longitute(nullable: true)
      // latitude(nullable: true)
       driverName(nullable: true)
       driverPhonenumber(nullable: true)
	   lastUpdated(nullable: true)
	   creationDate(nullable: true)
	   //route(nullable:true)
     intialLongitute(nullable:true)
	    intialLatitude(nullable:true)
	 
   }



}
