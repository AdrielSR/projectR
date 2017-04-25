package es.aromano.users.model;


public enum UserRoleType {
    ROLE_USER("ROL USER"),
    ROLE_ADMIN("ROL ADMIN");

    private String name;

    UserRoleType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
