package com.example.biblioteca;
// Arquivo: ValidadorDeSenha.java
public class ValidadorDeSenha {

    /**
     * Valida uma senha com base nas seguintes regras:
     * 1. Deve ter no mínimo 8 caracteres.
     * 2. Deve conter pelo menos uma letra maiúscula.
     * 3. Deve conter pelo menos um número.
     *
     * @param senha A senha a ser validada.
     * @return true se a senha for válida.
     * @throws IllegalArgumentException se a senha for nula ou não atender a qualquer um dos critérios.
     */
    public boolean validar(String senha) {
        if (senha == null) {
            throw new IllegalArgumentException("Senha não pode ser nula");
        }

        if (senha.length() < 8) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 8 caracteres");
        }

        if (!senha.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra maiúscula");
        }

        if (!senha.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um número");
        }

        return true;
    }
}
