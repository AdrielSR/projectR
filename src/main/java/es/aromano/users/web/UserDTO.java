package es.aromano.users.web;

import es.aromano.users.domain.model.User;

public class UserDTO {

    private int id;
    private String tag;

    public UserDTO(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public static UserDTO from(User user){
        return new UserDTO(user.getId(),
                            user.getUsername());
    }


}
