package com.example.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {

    private Usuario usuario;
    private Livro livro;

    @BeforeEach
    void setup() {
        usuario = new Usuario("Rafaela");
        livro = new Livro("Domain-Driven Design");
    }

    @Test
    @DisplayName("Ao criar empréstimo, livro fica indisponível")
    void emprestimoMarcaLivroIndisponivel() {

        // Arrange
        Emprestimo e = new Emprestimo(usuario, livro);

        // Act / Assert
        assertAll(
                () -> assertSame(usuario, e.getUsuario()),
                () -> assertSame(livro, e.getLivro()),
                () -> assertFalse(livro.isDisponivel()),
                () -> assertTrue(e.isAtivo())
        );
    }

    @Test
    @DisplayName("Devolver empréstimo torna livro disponível")
    void devolverEmprestimo() {

        // Arrange
        Emprestimo emprestimo = new Emprestimo(usuario, livro);

        // Act
        emprestimo.devolver();

        // Assert
        assertAll(
                () -> assertFalse(emprestimo.isAtivo()),
                () -> assertNotNull(emprestimo.getDataDevolucao()),
                () -> assertTrue(livro.isDisponivel())
        );
    }

    @Test
    @DisplayName("Não deve emprestar livro já indisponível")
    void naoEmprestaLivroIndisponivel() {
        // Arrange
        new Emprestimo(usuario, livro);

        // Act / Assert
        assertThrows(IllegalStateException.class,
                () -> new Emprestimo(new Usuario("Maria"),
                        livro));
    }
}