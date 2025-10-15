package  com.example.biblioteca;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorDeSenhaTest {

    private final ValidadorDeSenha validador = new ValidadorDeSenha();

    @Test
    void deveRetornarTrueParaSenhaValida() {
        assertTrue(validador.validar("Senha123"));
    }

    @Test
    void deveLancarExcecaoParaSenhaNula() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            validador.validar(null);
        });
        assertEquals("Senha não pode ser nula", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoParaSenhaCurta() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            validador.validar("Sen1");
        });
        assertEquals("A sua senha deve ter no mínimo 8 caracteres", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoParaSenhaSemLetraMaiuscula() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            validador.validar("senha123");
        });
        assertEquals("Senha deve conter pelo menos uma letra maiúscula", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoParaSenhaSemNumero() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            validador.validar("SenhaSemNumero");
        });
        assertEquals("Senha deve conter pelo menos um número", excecao.getMessage());
    }

    public ValidadorDeSenha getValidador() {
        return validador;
    }
}
