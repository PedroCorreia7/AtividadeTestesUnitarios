package com.example.biblioteca;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class LivroTest {
    @Test
    @DisplayName("Deve criar livro disponível com título válido")
    void criarLivroValido() {
        // Arrange
        Livro l = new Livro("Clean Code");

        // Act/Assert
        assertAll(
                () -> assertEquals("Clean Code", l.getTitulo()),
                () -> assertTrue(l.isDisponivel())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @DisplayName("Deve rejeitar títulos vazios ou em branco")
    void tituloInvalido(String titulo) {
        assertThrows(IllegalArgumentException.class,
                () -> new Livro(titulo));
    }


    @Test
    @DisplayName("Não deve permitir marcar indisponível duas vezes")
    void marcarIndisponivelDuasVezes() {

        // Arrange
        Livro l = new Livro("Refactoring");

        // Act
        l.marcarIndisponivel();

        // Assert
        assertThrows(IllegalStateException.class,
                () -> l.marcarIndisponivel());
    }
}
