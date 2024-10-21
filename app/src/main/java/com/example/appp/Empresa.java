package com.example.appp;

public class Empresa {

    private String id;               // Identificador del prestador
    private String nombre;               // Nombre de la empresa
    private String ubicacion;            // Ubicación de la empresa
    private String telefono;             // Número de teléfono
    private String horarioApertura;      // Horario de apertura
    private String horarioCierre;        // Horario de cierre
    private String servicios;            // Servicios disponibles
    private String categoria;            // Categoría de la empresa
    private String uidPrestador;
    // Constructor vacío necesario para Firebase
    public Empresa() {
    }

    // Constructor con parámetros
    public Empresa(String nombre, String ubicacion, String telefono, String horarioApertura, String horarioCierre, String servicios, String categoria, String uidPrestador) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.servicios = servicios;
        this.categoria = categoria;
        this.uidPrestador = uidPrestador; // Inicializar el UID del prestador
    }

    // Getters y Setters
    public String getUidPrestador() {
        return uidPrestador;
    }

    public void setUidPrestador(String uidPrestador) {
        this.uidPrestador = uidPrestador;
    }


    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public String getHorarioCierre() {
        return horarioCierre;
    }

    public String getServicios() {
        return servicios;
    }

    public String getCategoria() {
        return categoria;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setHorarioApertura(String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public void setHorarioCierre(String horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
