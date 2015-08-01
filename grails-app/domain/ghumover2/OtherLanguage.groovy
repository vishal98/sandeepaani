package ghumover2;

class OtherLanguage {
	int oLangId
	String oLangCode
	String oLangValue
	
	static mapping = { id generator: 'increment',name: 'oLangId'}
	
	static constraints = {
		oLangId(nullable:false)
		oLangCode(nullable:false)
		oLangValue(nullable:false)
	}
	OtherLanguage(String c, String v){
		this.oLangCode=c
		this.oLangValue=v
	}

   
}
