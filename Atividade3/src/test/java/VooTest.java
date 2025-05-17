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
    private LocalDateTime dataHora;
    private Aviao aviao;

    @BeforeEach
    public void setUp() {
        vooRepository = new VooRepository();
        vooApplication = new VooApplication(vooRepository);
        aviao = new Aviao(1,"EMB 190",1,"Embraer");
        dataHora = LocalDateTime.of(2025, 5, 16, 12, 0);
        vooValido = new Voo(1,"Bahia","Rio de Janeiro",dataHora, aviao);
    }

    //Cadastrar voo com origem, destino, data e avião válido → Deve ser salvo corretamente
    @Test
    public void cadastrarVooDadosValidos(){
        Assertions.assertTrue(vooApplication.salvar(vooValido));
    }

    //Tentar cadastrar voo sem avião associado → Deve lançar exceção ou falhar
    @Test
    public void cadastrarVooSemAviaoAssociado(){
        LocalDateTime dataHora = LocalDateTime.of(2025, 5, 20, 16, 30);
        Aviao aviao = null;
        Voo vooSemAviao = new Voo(2,"São Paulo","Orlando",dataHora, aviao);
        Assertions.assertFalse(vooApplication.salvar(vooSemAviao));
    }

    //Listar voos após 1 cadastro → Deve retornar 1 registro com dados do avião
    @Test
    public void listarVooApos1Cadastros(){
        vooApplication.salvar(vooValido);
        List<Voo> voos = vooApplication.buscarTodos();

        Assertions.assertEquals(1,voos.size());
        Voo voo = vooApplication.buscarPorId(1);

        Assertions.assertEquals(vooValido.getOrigem(),voo.getOrigem());
        Assertions.assertEquals(vooValido.getDestino(),voo.getDestino());
        Assertions.assertEquals(vooValido.getDataHora(),voo.getDataHora());
        Assertions.assertEquals(vooValido.getAviao().getModelo(),voo.getAviao().getModelo());
        Assertions.assertEquals(vooValido.getAviao().getCapacidade(),voo.getAviao().getCapacidade());
        Assertions.assertEquals(vooValido.getAviao().getFabricante(),voo.getAviao().getFabricante());
    }

    @Test
    public void cadastrarVooSemVagasDisponiveis(){
        Aviao aviao2 = new Aviao(2,"EMB 190",0,"Embraer");
        LocalDateTime dataHora = LocalDateTime.of(2025, 5, 20, 16, 30);
        Voo vooSemAviao = new Voo(2,"São Paulo","Orlando",dataHora, aviao2);
        Assertions.assertFalse(vooApplication.salvar(vooSemAviao));
    }

    @Test
    public void testToStringDeVoo() {
        Aviao aviao = new Aviao(1, "EMB 190", 100, "Embraer");
        LocalDateTime dataHora = LocalDateTime.of(2025, 5, 16, 12, 0);
        Voo voo = new Voo(1, "Bahia", "Rio de Janeiro", dataHora, aviao);

        String resultado = voo.toString();

        Assertions.assertTrue(resultado.contains("id=1"));
        Assertions.assertTrue(resultado.contains("origem='Bahia'"));
        Assertions.assertTrue(resultado.contains("destino='Rio de Janeiro'"));
        Assertions.assertTrue(resultado.contains("dataHora=2025-05-16T12:00"));
        Assertions.assertTrue(resultado.contains("aviao=Aviao{id=1, modelo='EMB 190', capacidade=100, fabricante='Embraer'}"));
    }

}
