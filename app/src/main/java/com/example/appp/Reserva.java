package com.example.appp;

public class Reserva {
    private String id;
    private String nombreEmpresa;
    private String ubicacion;
    private String horario;
    private String nombreContacto;
    private String correoContacto;
    private String telefonoContacto;

    // Constructor
    public Reserva(String id, String nombreEmpresa, String ubicacion, String horario, String nombreContacto, String correoContacto, String telefonoContacto) {
        this.id = id; // Guardar el ID
        this.nombreEmpresa = nombreEmpresa;
        this.ubicacion = ubicacion;
        this.horario = horario;
        this.nombreContacto = nombreContacto;
        this.correoContacto = correoContacto;
        this.telefonoContacto = telefonoContacto;
    }

    // Métodos getters
    public String getId() {
        return id; // Método para obtener el ID
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String fecha) {
        this.ubicacion = fecha;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public void setId(String idReserva) {
        this.id = idReserva;
    }
}
