package org.example.entities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassageiroTest {
    private Passageiro passageiroValido;
    private Passageiro passageiroInvalidoCPF;
    private Passageiro passageiroInvalidoEmail;

    @BeforeEach
    public void setUp() {
        passageiroValido = new Passageiro(1, "João Silva", "52998224725", "joao@email.com");
        passageiroInvalidoCPF = new Passageiro(2, "Maria Souza", "1234567890", "maria@email.com");
        passageiroInvalidoEmail = new Passageiro(3, "Carlos Oliveira", "987.654.321-00", "carlos-email");
    }

    //Testar CPF válido (exemplo: "52998224725") → Deve retornar true
    @Test
    public void validarCPFValido() {
        Assertions.assertTrue(passageiroValido.validarCPF(passageiroValido.getCpf()));
    }

    //Testar CPF inválido (exemplo: "12345678900") → Deve retornar false
    @Test
    public void validarCPFInvalido() {
        Assertions.assertFalse(passageiroInvalidoCPF.validarCPF(passageiroInvalidoCPF.getCpf()));
    }

    //Testar e-mail válido (exemplo: "ana.souza@email.com") → Deve retornar true
    @Test
    public void validarEmailValido() {
        Assertions.assertTrue(passageiroValido.validarEmail(passageiroValido.getEmail()));
    }

    //Testar e-mail inválido (exemplo: "ana.souza@com") → Deve retornar false
    @Test
    public void validarEmailInvalido() {
        Assertions.assertFalse(passageiroInvalidoEmail.validarEmail(passageiroInvalidoEmail.getEmail()));
    }

    //Testar retorno do toString
    @Test
    public void testToStringDePassageiro() {
        Passageiro passageiro = new Passageiro(1, "Ana", "52998224725", "ana@email.com");
        String esperado = "Passageiro{id=1, nome='Ana', cpf='52998224725', email='ana@email.com'}";
        Assertions.assertEquals(esperado, passageiro.toString(), "O método toString não gerou o resultado esperado.");
    }
}
