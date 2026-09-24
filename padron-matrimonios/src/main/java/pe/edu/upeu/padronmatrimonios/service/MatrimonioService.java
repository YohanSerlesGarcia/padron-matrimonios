package pe.edu.upeu.padronmatrimonios.service;
import pe.edu.upeu.padronmatrimonios.model.Matrimonio;
import pe.edu.upeu.padronmatrimonios.model.Registro;
import java.time.LocalDate;
import java.util.List;

public interface MatrimonioService {
    void registrarMatrimonio(String numeroActa, String c1, String c2, LocalDate fecha, String lugar);
    List<Registro> listarMatrimonios();
    List<Registro> buscarMatrimonios(String texto);
    void eliminarMatrimonio(String numeroActa);
}