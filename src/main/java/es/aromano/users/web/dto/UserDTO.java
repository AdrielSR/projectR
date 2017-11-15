package es.aromano.users.web.dto;

import es.aromano.users.domain.model.User;

public class UserDTO {

    private int id;
    private String username;
    private String email;
    private String enlaceImagen;
    private int idAvatar;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String email, String enlaceImagen) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enlaceImagen = enlaceImagen;
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

    public String getEnlaceImagen() {
        return enlaceImagen;
    }

    public int getIdAvatar() {
        return idAvatar;
    }

    public static UserDTO from(User user){
        return new UserDTO(user.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getAvatar().getEnlace());
    }


}
