package es.aromano.users.model;


public enum UserRoleType {
    ROLE_USER("ROL USER"),
    ROLE_ADMIN("ROL ADMIN");

    private String name;

    private UserRoleType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static UserRoleType from(String role){
    	switch(role){
	    	case "ROLE_ADMIN": 
	    		return ROLE_ADMIN;
	    	case "ROLE_USER": 
	    		return ROLE_USER;
	    	default: 
	    		return ROLE_USER;
    	}
    }
}
