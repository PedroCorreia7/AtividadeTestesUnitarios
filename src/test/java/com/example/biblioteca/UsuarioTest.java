package com.example.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setup() {
        usuario = new Usuario("João");
    }

    @Test
    @DisplayName("Usuário começa sem empréstimos ativos")
    void semEmprestimosAtivosInicialmente() {
        assertTrue(usuario.getEmprestimosAtivos().isEmpty());
    }

    @Test
    @DisplayName("Valida se o nome do usuário")
    void oNomeDoUsuarioEstaCorreto() {
        String nome = "Fabricio";
        Usuario user = new Usuario(nome);
        assertEquals(nome, user.getNome());
    }

    @Nested
    class LimiteEmprestimos {

        @Test
        @DisplayName("Pode emprestar até o limite")
        void podeEmprestarAteOLimite() {

            // Act
            usuario.adicionarEmprestimo(new Livro("L1"));
            usuario.adicionarEmprestimo(new Livro("L2"));
            usuario.adicionarEmprestimo(new Livro("L3"));

            // Assert
            assertEquals(Usuario.LIMITE_EMPRESTIMOS,
                    usuario.getEmprestimosAtivos().size());
        }

        @Test
        @DisplayName("Ultrapassar limite deve lançar exceção")
        void ultrapassarLimiteLancaExcecao() {
            // Act
            usuario.adicionarEmprestimo(new Livro("L1"));
            usuario.adicionarEmprestimo(new Livro("L2"));
            usuario.adicionarEmprestimo(new Livro("L3"));

            // Assert
            assertThrows(IllegalStateException.class,
                    () -> usuario.adicionarEmprestimo(new Livro("L4")));
        }
    }

    @Nested
    class Devolucao {

        @Test
        @DisplayName("Devolver remove empréstimo da lista ativa")
        void devolverRemoveDaListaAtiva() {
            // Arrange
            Livro l = new Livro("Clean Architecture");

            // Act
            usuario.adicionarEmprestimo(l);
            usuario.devolver(l);

            // Assert
            assertTrue(usuario.getEmprestimosAtivos().isEmpty());
            assertTrue(l.isDisponivel());
        }

        @Test
        @DisplayName("Devolver livro não emprestado deve falhar")
        void devolverLivroNaoEmprestadoFalha() {

            // Arrange
            Livro l = new Livro("Effective Java");

            // Act / Assert
            assertThrows(IllegalStateException.class, () -> usuario.devolver(l));
        }
    }
}