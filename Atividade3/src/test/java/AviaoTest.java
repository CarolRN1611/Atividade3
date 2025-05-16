import org.example.application.AviaoApplication;
import org.example.entities.Aviao;
import org.example.repositories.AviaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AviaoTest {
    private Aviao aviaoValido;
    private Aviao aviaoCapacidadeZero;
    private Aviao aviaoCapacidadeNegativa;
    private AviaoRepository aviaoRepository;
    private AviaoApplication aviaoApplication;

    @BeforeEach
    public void setUp() {
        aviaoRepository = new AviaoRepository();
        aviaoApplication = new AviaoApplication(aviaoRepository);
        aviaoValido = new Aviao(1,"EMB 190",2,"Embraer");
        aviaoCapacidadeZero = new Aviao(2,"Boeing 737",0,"Boeing");
        aviaoCapacidadeNegativa = new Aviao(3,"Airbus A320",-2,"Airbus");
    }

    @Test
    public void cadastrarAviãoModeloCapacidadeValidos(){
        Assertions.assertTrue(aviaoApplication.salvar(aviaoValido));
    }

    @Test
    public void cadastrarAviãoCapacidadeZero(){
        Assertions.assertFalse(aviaoApplication.salvar(aviaoCapacidadeZero));
    }

    @Test
    public void cadastrarAviãoCapacidadeNegativa(){
        Assertions.assertFalse(aviaoApplication.salvar(aviaoCapacidadeNegativa));
    }

    @Test
    public void listarAviaoApos2Cadastros(){
        Aviao aviao1 = new Aviao(1, "EMB 190", 2, "Embraer");
        Aviao aviao2 = new Aviao(2, "Boeing 737", 150, "Boeing");


        aviaoApplication.salvar(aviao1);
        aviaoApplication.salvar(aviao2);

        List<Aviao> avioes = aviaoApplication.buscarTodos();
        Assertions.assertEquals(2,avioes.size());

        Assertions.assertEquals(aviao1.getModelo(),avioes.get(0).getModelo());
        Assertions.assertEquals(aviao2.getModelo(),avioes.get(1).getModelo());
        Assertions.assertEquals(aviao1.getCapacidade(),avioes.get(0).getCapacidade());
        Assertions.assertEquals(aviao2.getCapacidade(),avioes.get(1).getCapacidade());
        Assertions.assertEquals(aviao1.getFabricante(),avioes.get(0).getFabricante());
        Assertions.assertEquals(aviao2.getFabricante(),avioes.get(1).getFabricante());
        Assertions.assertEquals(aviao1.getId(),avioes.get(0).getId());
        Assertions.assertEquals(aviao2.getId(),avioes.get(1).getId());
    }
}
