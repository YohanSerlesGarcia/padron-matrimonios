package pe.edu.upeu.padronmatrimonios.model;
import java.time.LocalDate;

public class Matrimonio extends Registro {
    private String contrayente1;
    private String contrayente2;

    public Matrimonio(String numeroActa, String contrayente1, String contrayente2,
                      LocalDate fecha, String lugar) {
        super(numeroActa, fecha, lugar);
        this.contrayente1 = contrayente1;
        this.contrayente2 = contrayente2;
    }

    public String getContrayente1() { return contrayente1; }
    public void setContrayente1(String c) { this.contrayente1 = c; }
    public String getContrayente2() { return contrayente2; }
    public void setContrayente2(String c) { this.contrayente2 = c; }

    @Override
    public String descripcion() { return contrayente1 + " & " + contrayente2; }

    @Override
    public boolean coincideCon(String texto) {
        String busqueda = texto.trim().toLowerCase();
        return contrayente1.toLowerCase().contains(busqueda) ||
                contrayente2.toLowerCase().contains(busqueda);
    }
}