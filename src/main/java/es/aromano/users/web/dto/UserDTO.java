package es.aromano.users.web.dto;

import es.aromano.users.domain.model.User;

public class UserDTO {

    private int id;
    private String username;
    private String email;

    public UserDTO() {
    }

    private UserDTO(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public static UserDTO from(User user){
        return new UserDTO(user.getId(),
                            user.getUsername(),
                            user.getEmail());
    }


}
