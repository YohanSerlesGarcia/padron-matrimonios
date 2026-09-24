package pe.edu.upeu.padronmatrimonios.model;
import java.time.LocalDate;

public abstract class Registro implements buscable {
    private String numeroActa;
    private LocalDate fecha;
    private String lugar;

    protected Registro(String numeroActa, LocalDate fecha, String lugar){
        this.numeroActa = numeroActa;
        this.fecha = fecha;
        this.lugar = lugar;
    }
    public abstract  String descripcion();
    public String getNumeroActa(){return  numeroActa; }
    public LocalDate getFecha() {return  fecha;}
    public void  setFecha(LocalDate fecha){this.fecha = fecha;}
    public String getLugar() {return lugar; }
    public void setLugar(String lugar){this.lugar = lugar;}
    }