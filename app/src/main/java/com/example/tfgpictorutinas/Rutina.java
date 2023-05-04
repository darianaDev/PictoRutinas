package com.example.tfgpictorutinas;

public class Rutina {
    long idRutina;
    String nombre;
    String foto;

<<<<<<< Updated upstream
    public Rutina(long idRutina, String nombre, String foto){
        this.idRutina=idRutina;
        this.nombre=nombre;
        this.foto=foto;
=======
    String repeticiones;
    String hora_ini;

    public Rutina(long idRutina, String nombre, String foto, String repeticiones, String hora_ini){
        this.idRutina=idRutina;
        this.nombre=nombre;
        this.foto=foto;
        this.hora_ini= hora_ini;
        this.repeticiones=repeticiones;
>>>>>>> Stashed changes
    }

    public String getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(String hora_ini) {
        this.hora_ini = hora_ini;
    }

    public long getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(long idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
