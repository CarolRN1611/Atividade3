import org.example.application.VooApplication;
import org.example.entities.Aviao;
import org.example.entities.Voo;
import org.example.repositories.VooRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class VooTest {
    private Voo vooValido;
    private Voo vooCapacidadeZero;
    private Voo vooCapacidadeNegativa;
    private VooRepository vooRepository;
    private VooApplication vooApplication;

    @BeforeEach
    public void setUp() {
        vooRepository = new VooRepository();
        vooApplication = new VooApplication(vooRepository);
        Aviao aviao = new Aviao(1,"EMB 190",2,"Embraer");
        LocalDateTime dataHora = LocalDateTime.of(2025, 5, 16, 12, 0);
        vooValido = new Voo(1,"Bahia","Rio de Janeiro",dataHora, aviao);
    }

    @Test
    public void cadastrarVooDadosValidos(){
        Assertions.assertTrue(vooApplication.salvar(vooValido));
    }

    @Test
    public void cadastrarVooSemAviaoAssociado(){
        LocalDateTime dataHora = LocalDateTime.of(2025, 5, 20, 16, 30);
        Aviao aviao = null;
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Voo vooSemAviao = new Voo(2,"São Paulo","Orlando",dataHora, aviao);;
        });
    }

    @Test
    public void listarVooApos1Cadastros(){
        vooApplication.salvar(vooValido);
        List<Voo> voos = vooApplication.buscarTodos();

        Assertions.assertEquals(1,voos.size());
        Voo voo = voos.get(0);

        Assertions.assertEquals(vooValido.getOrigem(),voo.getOrigem());
        Assertions.assertEquals(vooValido.getDestino(),voo.getDestino());
        Assertions.assertEquals(vooValido.getDataHora(),voo.getDataHora());
        Assertions.assertEquals(vooValido.getAviao().getModelo(),voo.getAviao().getModelo());
        Assertions.assertEquals(vooValido.getAviao().getCapacidade(),voo.getAviao().getCapacidade());
        Assertions.assertEquals(vooValido.getAviao().getFabricante(),voo.getAviao().getFabricante());
    }
}
