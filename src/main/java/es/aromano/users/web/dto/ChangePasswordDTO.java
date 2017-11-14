package es.aromano.users.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordDTO {

    @NotEmpty
    private String currentPassword;

    @NotEmpty
    private String newPassword;

    @NotEmpty
    private String repeatedNewPassword;

    public ChangePasswordDTO() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatedNewPassword() {
        return repeatedNewPassword;
    }

    public void setRepeatedNewPassword(String repeatedNewPassword) {
        this.repeatedNewPassword = repeatedNewPassword;
    }
}
