import org.example.App;
import org.example.application.PassageiroApplication;
import org.example.entities.Aviao;
import org.example.entities.Passageiro;
import org.example.repositories.PassageiroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PassageiroTest {
    private Passageiro passageiroValido;
    private Passageiro passageiroInvalidoCPF;
    private Passageiro passageiroInvalidoEmail;
    private PassageiroRepository passageiroRepository;
    private PassageiroApplication passageiroApplication;

    @BeforeEach
    public void setUp() {
        passageiroValido = new Passageiro(1, "João Silva", "52998224725", "joao@email.com");
        passageiroInvalidoCPF = new Passageiro(2, "Maria Souza", "1234567890", "maria@email.com");
        passageiroInvalidoEmail = new Passageiro(3, "Carlos Oliveira", "987.654.321-00", "carlos-email");
        passageiroRepository = new PassageiroRepository();
        passageiroApplication = new PassageiroApplication(passageiroRepository);
    }

    //Testar CPF válido (exemplo: "52998224725") → Deve retornar true
    @Test
    public void cpfValido(){
        Assertions.assertTrue(passageiroValido.validarCPF(passageiroValido.getCpf()));
    }

    //Testar CPF inválido (exemplo: "12345678900") → Deve retornar false
    @Test
    public void cpfInValido(){
        Assertions.assertFalse(passageiroInvalidoCPF.validarCPF(passageiroInvalidoCPF.getCpf()));
        passageiroInvalidoCPF.setCpf("1234567890a");
        Assertions.assertFalse(passageiroInvalidoCPF.validarCPF(passageiroInvalidoCPF.getCpf()));
    }

    //Testar e-mail válido (exemplo: "ana.souza@email.com") → Deve retornar true
    @Test
    public void emailValido(){
        Assertions.assertTrue(passageiroValido.validarEmail(passageiroValido.getEmail()));
    }

    //Testar e-mail inválido (exemplo: "ana.souza@com") → Deve retornar false
    @Test
    public void emailInValido(){
        Assertions.assertFalse(passageiroInvalidoEmail.validarEmail(passageiroInvalidoEmail.getEmail()));
    }

    //Cadastrar passageiro com dados válidos → Deve adicionar à lista
    @Test
    public void cadastrarPassageiroDadosValidos() {
        Assertions.assertTrue(passageiroApplication.salvar(passageiroValido));
    }

    //Tentar cadastrar passageiro com CPF inválido → Deve falhar ou lançar exceção
    @Test
    public void cadastrarPassageiroCPFInválido() {
        Assertions.assertFalse(passageiroApplication.salvar(passageiroInvalidoCPF));
    }

    @Test
    public void cadastrarPassageiroEmailInválido() {
        Assertions.assertFalse(passageiroApplication.salvar(passageiroInvalidoEmail));
    }

    //Listar passageiros após 3 cadastros → Deve retornar 3 registros
    @Test
    public void listarPassageirosApos3Cadastros(){
        Passageiro passageiro1 = new Passageiro(1, "João Silva", "137.817.040-76", "joao@email.com");
        Passageiro passageiro2 = new Passageiro(2, "Maria Oliveira", "548.839.360-90", "maria.oliveira@gmail.com");
        Passageiro passageiro3 = new Passageiro(3, "Carlos Santos", "398.200.930-82", "carlos.santos@gmail.com");

        passageiroApplication.salvar(passageiro1);
        passageiroApplication.salvar(passageiro2);
        passageiroApplication.salvar(passageiro3);

        List<Passageiro> passageiros = passageiroApplication.buscarTodos();

        Assertions.assertEquals(3,passageiros.size());

        Passageiro p1 = passageiroApplication.buscarPorId(1);
        Passageiro p2 = passageiroApplication.buscarPorId(2);
        Passageiro p3 = passageiroApplication.buscarPorId(3);

        Assertions.assertEquals(passageiro1.getNome(),p1.getNome());
        Assertions.assertEquals(passageiro2.getNome(),p2.getNome());
        Assertions.assertEquals(passageiro3.getNome(),p3.getNome());
        Assertions.assertEquals(passageiro1.getEmail(),p1.getEmail());
        Assertions.assertEquals(passageiro2.getEmail(),p2.getEmail());
        Assertions.assertEquals(passageiro3.getEmail(),p3.getEmail());
        Assertions.assertEquals(passageiro1.getCpf(),p1.getCpf());
        Assertions.assertEquals(passageiro2.getCpf(),p2.getCpf());
        Assertions.assertEquals(passageiro3.getCpf(),p3.getCpf());
        Assertions.assertEquals(passageiro1.getId(),p1.getId());
        Assertions.assertEquals(passageiro2.getId(),p2.getId());
        Assertions.assertEquals(passageiro3.getId(),p3.getId());
    }

    @Test
    public void testToStringDePassageiro() {
        Passageiro passageiro = new Passageiro(1, "João Silva", "137.817.040-76", "joao@email.com");

        String esperado = "Passageiro{id=1, nome='João Silva', cpf='137.817.040-76', email='joao@email.com'}";
        String resultado = passageiro.toString();

        Assertions.assertEquals(esperado, resultado);
    }

}
