package com.example.tfgpictorutinas;

public class Tarea {
    long idTarea;
    String nombreTarea;
    String fotoTarea;
<<<<<<< Updated upstream

    public Tarea(long idTarea, String nombreTarea, String fotoTarea) {
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.fotoTarea = fotoTarea;
=======
    Long duracion;
    long rutina_id;

    Tarea() {
    }
    public Tarea(long idTarea, String nombreTarea, String fotoTarea,Long duracion, long rutina_id) {
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.fotoTarea = fotoTarea;
        this.duracion = duracion;
        this.rutina_id = rutina_id;
>>>>>>> Stashed changes
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getFotoTarea() {
        return fotoTarea;
    }

    public void setFotoTarea(String fotoTarea) {
        this.fotoTarea = fotoTarea;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public long getRutina_id() {
        return rutina_id;
    }

    public void setRutina_id(long rutina_id) {
        this.rutina_id = rutina_id;
    }
}
