package es.aromano.edificios.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class EdificioDTO {

    private String nombre;
    private String direccion;
    private MultipartFile file;

    public EdificioDTO() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
