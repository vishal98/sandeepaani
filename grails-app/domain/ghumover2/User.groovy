package ghumover2

import grails.rest.Resource

@Resource(formats=['json', 'xml'])
class User {

	transient springSecurityService
    String name
	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	String tags 
	boolean tagRegister=false
	boolean usersetupemialSent=false
	boolean istagupdate =true
	String deviceToken
	String platform
	long school_id
	String sesEmailResponse
	String sesEmailStatus
	
	static hasMany = [conversations : Conversation,fileManager:FileManager]
//	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		tags(nullable: true)
		deviceToken(nullable: true)
		platform(nullable: true)
		school_id(nullable: true)
		sesEmailResponse(nullable: true)
		sesEmailStatus(nullable: true)
		name(nullable:true)
	}
	


	static mapping = {
		password column: '`password`'
		cache:true
		username index:'t1'
		password index:'t1'
		
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
