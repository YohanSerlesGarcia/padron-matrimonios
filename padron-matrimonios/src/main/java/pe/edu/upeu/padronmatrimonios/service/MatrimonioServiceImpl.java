package pe.edu.upeu.padronmatrimonios.service;
import pe.edu.upeu.padronmatrimonios.model.Matrimonio;
import pe.edu.upeu.padronmatrimonios.model.Registro;
import pe.edu.upeu.padronmatrimonios.repository.RegistroRepository;
import java.time.LocalDate;
import java.util.List;

public class MatrimonioServiceImpl implements MatrimonioService {
    private RegistroRepository repository;

    public MatrimonioServiceImpl(RegistroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrarMatrimonio(String numeroActa, String c1, String c2, LocalDate fecha, String lugar) {
        Matrimonio matrimonio = new Matrimonio(numeroActa, c1, c2, fecha, lugar);
        repository.guardar(matrimonio);
    }

    @Override
    public List<Registro> listarMatrimonios() {
        return repository.listarTodos();
    }

    @Override
    public List<Registro> buscarMatrimonios(String texto) {
        return repository.buscarPorTexto(texto);
    }

    @Override
    public void eliminarMatrimonio(String numeroActa) {
        repository.eliminarPorActa(numeroActa);
    }
}