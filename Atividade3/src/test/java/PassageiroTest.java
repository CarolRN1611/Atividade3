import org.example.App;
import org.example.Passageiro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PassageiroTest {
    private Passageiro passageiroValido;
    private Passageiro passageiroInvalidoCPF;
    private Passageiro passageiroInvalidoEmail;

    @BeforeEach
    public void setUp() {
        passageiroValido = new Passageiro(1, "João Silva", "123.456.789-09", "joao@email.com");
        passageiroInvalidoCPF = new Passageiro(2, "Maria Souza", "123.456.789", "maria@email.com");
        passageiroInvalidoEmail = new Passageiro(3, "Carlos Oliveira", "987.654.321-00", "carlos-email");
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
    public void cadastrarPassageirosList() {
        Passageiro passageiroValido2 = new Passageiro(2, "Carlos Oliveira", "987.654.321-00", "carlos@gmail.com");

        App.salvar(passageiroValido);
        App.salvar(passageiroValido2);

        List<Passageiro> listaPassageiros = App.listarTodosPassageiros();

        Assertions.assertEquals(2, listaPassageiros.size());

        Passageiro primeiro = listaPassageiros.get(0);
        Passageiro segundo = listaPassageiros.get(1);

        Assertions.assertEquals(1, primeiro.getId());
        Assertions.assertEquals("João Silva", primeiro.getNome());
        Assertions.assertEquals("123.456.789-09", primeiro.getCpf());
        Assertions.assertEquals("joao@email.com", primeiro.getEmail());

        Assertions.assertEquals(2, segundo.getId());
        Assertions.assertEquals("Carlos Oliveira", segundo.getNome());
        Assertions.assertEquals("987.654.321-00", segundo.getCpf());
        Assertions.assertEquals("carlos@gmail.com", segundo.getEmail());
    }

    @Test
    public void testarTodosOsSetters() {
        Passageiro passageiro = new Passageiro(4, "João Silva", "123.456.789-09", "joao@email.com");

        passageiro.setId(4);
        passageiro.setNome("Manuela Ferreira");
        passageiro.setCpf("987.654.784-98");
        passageiro.setEmail("manuela@email.com");


        Assertions.assertEquals(4, passageiro.getId());
        Assertions.assertEquals("Manuela Ferreira", passageiro.getNome());
        Assertions.assertEquals("987.654.784-98", passageiro.getCpf());
        Assertions.assertEquals("manuela@email.com", passageiro.getEmail());
    }
}
