import org.example.App;
import org.example.application.PassageiroApplication;
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
        passageiroValido = new Passageiro(1, "João Silva", "123.456.789-09", "joao@email.com");
        passageiroInvalidoCPF = new Passageiro(2, "Maria Souza", "123.456.789", "maria@email.com");
        passageiroInvalidoEmail = new Passageiro(3, "Carlos Oliveira", "987.654.321-00", "carlos-email");
        passageiroRepository = new PassageiroRepository();
        passageiroApplication = new PassageiroApplication(passageiroRepository);
    }
    
    @Test
    public void cpfValido(){
        Assertions.assertTrue(passageiroValido.validarCPF(passageiroValido.getCpf()));
    }

    @Test
    public void cpfInValido(){
        Assertions.assertFalse(passageiroInvalidoCPF.validarCPF(passageiroInvalidoCPF.getCpf()));
    }

    @Test
    public void emailValido(){
        Assertions.assertTrue(passageiroValido.validarEmail(passageiroValido.getEmail()));
    }
    @Test
    public void emailInValido(){
        Assertions.assertFalse(passageiroInvalidoEmail.validarEmail(passageiroInvalidoEmail.getEmail()));
    }

    @Test
    public void cadastrarPassageiroDadosValidos() {
        Assertions.assertTrue(passageiroApplication.salvar(passageiroValido));
    }

    @Test
    public void cadastrarPassageiroCPFInválido() {
        Assertions.assertFalse(passageiroApplication.salvar(passageiroInvalidoCPF));
    }

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

        Assertions.assertEquals(passageiro1.getNome(),passageiros.get(0).getNome());
        Assertions.assertEquals(passageiro2.getNome(),passageiros.get(1).getNome());
        Assertions.assertEquals(passageiro3.getNome(),passageiros.get(2).getNome());
        Assertions.assertEquals(passageiro1.getEmail(),passageiros.get(0).getEmail());
        Assertions.assertEquals(passageiro2.getEmail(),passageiros.get(1).getEmail());
        Assertions.assertEquals(passageiro3.getEmail(),passageiros.get(2).getEmail());
        Assertions.assertEquals(passageiro1.getCpf(),passageiros.get(0).getCpf());
        Assertions.assertEquals(passageiro2.getCpf(),passageiros.get(1).getCpf());
        Assertions.assertEquals(passageiro3.getCpf(),passageiros.get(2).getCpf());
        Assertions.assertEquals(passageiro1.getId(),passageiros.get(0).getId());
        Assertions.assertEquals(passageiro2.getId(),passageiros.get(1).getId());
        Assertions.assertEquals(passageiro3.getId(),passageiros.get(2).getId());
    }
}
