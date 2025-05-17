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

    //Cadastrar avião com modelo e capacidade válidos → Deve ser salvo corretamente
    @Test
    public void cadastrarAviãoModeloCapacidadeValidos(){
        Assertions.assertTrue(aviaoApplication.salvar(aviaoValido));
    }

    //Tentar cadastrar avião com capacidade zero → Deve lançar exceção ou falhar
    @Test
    public void cadastrarAviãoCapacidadeZero(){
        Assertions.assertFalse(aviaoApplication.salvar(aviaoCapacidadeZero));
    }

    //Tentar cadastrar avião com capacidade negativa → Deve lançar exceção ou falhar
    @Test
    public void cadastrarAviãoCapacidadeNegativa(){
        Assertions.assertFalse(aviaoApplication.salvar(aviaoCapacidadeNegativa));
    }

    //Listar aviões após 2 cadastros → Deve retornar 2 registros
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

    @Test
    public void buscarAviaoPorId(){
        aviaoApplication.salvar(aviaoValido);
        Aviao aviao = aviaoApplication.buscarPorId(1);

        Assertions.assertEquals(1,aviao.getId());
        Assertions.assertEquals("EMB 190",aviao.getModelo());
        Assertions.assertEquals(2,aviao.getCapacidade());
        Assertions.assertEquals("Embraer",aviao.getFabricante());
    }

    @Test
    public void testToStringDeAviao() {
        Aviao aviao = new Aviao(1, "Boeing 737", 200, "Boeing");

        String esperado = "Aviao{id=1, modelo='Boeing 737', capacidade=200, fabricante='Boeing'}";
        String resultado = aviao.toString();

        Assertions.assertEquals(esperado, resultado);
    }
}
