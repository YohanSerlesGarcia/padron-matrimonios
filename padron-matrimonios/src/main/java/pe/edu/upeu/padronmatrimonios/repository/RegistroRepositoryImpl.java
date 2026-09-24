package pe.edu.upeu.padronmatrimonios.repository;
import pe.edu.upeu.padronmatrimonios.model.Registro;
import java.util.ArrayList;
import java.util.List;

public class RegistroRepositoryImpl implements RegistroRepository {
    private List<Registro> registros;

    public RegistroRepositoryImpl() {
        this.registros = new ArrayList<>();
    }

    @Override
    public void guardar(Registro registro) {
        registros.add(registro);
    }

    @Override
    public List<Registro> listarTodos() {
        return registros;
    }

    @Override
    public List<Registro> buscarPorTexto(String texto) {
        List<Registro> resultados = new ArrayList<>();
        for (Registro r : registros) {
            if (r.coincideCon(texto)) {
                resultados.add(r);
            }
        }
        return resultados;
    }

    @Override
    public void eliminarPorActa(String numeroActa) {
        registros.removeIf(r -> r.getNumeroActa().equals(numeroActa));
    }
}