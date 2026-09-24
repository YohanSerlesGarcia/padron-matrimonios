package pe.edu.upeu.padronmatrimonios.repository;
import pe.edu.upeu.padronmatrimonios.model.Registro;
import java.util.List;

public interface RegistroRepository {
    void guardar(Registro registro);
    List<Registro> listarTodos();
    List<Registro> buscarPorTexto(String texto);
    void eliminarPorActa(String numeroActa);
}