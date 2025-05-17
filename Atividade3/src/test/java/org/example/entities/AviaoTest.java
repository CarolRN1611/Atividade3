package org.example.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AviaoTest{
    private Aviao aviaoValido;

    @BeforeEach
    public void setUp() {
        aviaoValido = new Aviao(1,"EMB 190",2,"Embraer");
    }

    //Cadastrar avião com modelo e capacidade válidos → Deve ser salvo corretamente
    @Test
    public void criarAviaoValido(){
        Assertions.assertEquals(1, aviaoValido.getId());
        Assertions.assertEquals("EMB 190", aviaoValido.getModelo());
        Assertions.assertEquals(2, aviaoValido.getCapacidade());
        Assertions.assertEquals("Embraer", aviaoValido.getFabricante());

    }


    @Test
    public void testarToStringDeAviao() {
        Aviao aviao = new Aviao(1, "Boeing 737", 200, "Boeing");
        String esperado = "Aviao{id=1, modelo='Boeing 737', capacidade=200, fabricante='Boeing'}";
        String obtido = aviao.toString();
        Assertions.assertEquals(esperado, obtido);
    }


}
