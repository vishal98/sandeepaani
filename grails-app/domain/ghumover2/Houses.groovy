package ghumover2

import grails.converters.JSON

class Houses {
	static responseFormats = ['json', 'xml']

	int houseId
	String hCode
	String hName;
	
	static mapping = { id generator: 'increment',name: 'houseId'}
	
	static constraints = {
		houseId(nullable:false)
		hCode(nullable:false)	
		hName(nullable:false)
	}

	Houses(String code, String hnam){
		this.hCode= code;
		this.hName= hnam;
	}
}
	
	
/*
	def checkHouseCode(String h){
		String hC= h.charAt(0).toUpperCase()
		Houses shouse= Houses.findByHCode(hC).collect{["houseId":it.houseId, "hCode":it.hCode, "hName":it.hName]}
		if(shouse.gethCode()!=null){
			String sh=shouse.gethName()
			int siz=h.size()
			String ss= sh.charAt(0);
			println("this is siz "+siz)
			println("this is siz "+hC.equals(ss))
			println(hC);
			println(ss.toString())
			println("hi Compare"+siz);
			if(1==siz & hC.equals(ss)){		//('y','Y')
				return sh;
			}
			else{
				 return "n/a";
			}
		}
		else{
			return "n/a"
		}
	}

	
}

	/*	Houses(char code){
		Houses house=Houses.findByHCode(code).each {
			it.hName
		}
		if(Houses.findByHCode(code)!=null){
		this.hName= Houses."code";
		}
		else{
			this.hName=new Houses(){this.}
		}
	}

	def checkHouseCode(String h){
		String sh= h.charAt(0).toUpperCase()
		Houses shh=Houses.findByHCode(sh).collect{["houseId":it.houseId, "hCode":it.hCode, "hName":it.hName]}
		if(shh.getHouseId()!=null){
		String shhh=shh.gethName()
		Houses sHouse;
	
		if((h.size()==1 && h.equals(shhh.charAt(0).toLowerCase())) || (h.size()==1 && h.equals(shhh.charAt(0) )) || (h.equals(shhh.toLowerCase()))
			 || (h.equals(shhh))|| (h.charAt(0).equals(shhh.charAt(0)) && h.toUpperCase().equals(shhh))){
			sHouse = Houses.findByHCode(sh).collect{["houseId":it.houseId, "hCode":it.hCode, "hName":it.hName]}
			return sHouse;
		}
	 else{
			 def msg="Sorry given House not found";
		 	}
		}
		else{
			def msg="Sorry given House not found";  
		}
	}
	def checkHouseCode(String h){
		String sh= h.charAt(0).toUpperCase()
		Houses shh=Houses.findByHCode(sh).collect{["houseId":it.houseId, "hCode":it.hCode, "hName":it.hName]}
		Houses sHouse;
		
	
		if(h=="y"|| h=="Y"|| h=="yellow"|| h=="Yellow"||h=="YELLOW"){
			sHouse = Houses.findByHCode("Y").collect{["houseId":it.houseId, "hCode":it.hCode, "hName":it.hName]}
			return sHouse;
		}
		else if(h=="g"|| h=="G"|| h=="green"|| h=="Green"|| h=="GREEN"){
			sHouse = Houses.findAllByHCode("G").collect{["houseId":it.houseId, "hCode":it.hCode, "hName":it.hName]}
			return sHouse; 
		}
		else if(h=="b"|| h=="B"|| h=="blue"|| h=="Blue"|| h=="BLUE"){
			sHouse = Houses.findByHCode("B").collect{["houseId":it.houseId, "hCode":it.hCode, "hName":it.hName]}
			return sHouse;
		}
		else if(h=="r"|| h=="R"|| h=="red"|| h=="Red"|| h=="RED"){
			sHouse = Houses.findByHCode("R").collect{["houseId":it.houseId, "hCode":it.hCode, "hName":it.hName]}
			return sHouse;
		}
		else{
			def msg = "House not found for the given input "+h+". It should be (Y,G,B,R)";
			return msg;
		}  
	}*/

