package com.mickaelgeronimo.mastery.core.oop;

import java.util.Objects;

/**
 * Entidade Usuário representativa dos fundamentos de Construtores Sobrecarregados (Capítulo 41).
 * Demonstra a técnica de múltiplos construtores com diferentes listas de parâmetros
 * e encadeamento inteligente (constructor chaining) via {@code this(...)}.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class User {

    public String username;
    public String email;
    public int age;

    /**
     * Construtor 4: Modo Convidado / Anônimo (sem argumentos).
     * Aplica valores padrão universais.
     */
    public User() {
        this("Guest", "Not provided", 0);
    }

    /**
     * Construtor 1: Cadastro Rápido (apenas nome de usuário).
     *
     * @param username apelido do usuário
     */
    public User(String username) {
        this(username, "Not provided", 0);
    }

    /**
     * Construtor 2: Cadastro Web Intermediário (nome e e-mail).
     *
     * @param username apelido do usuário
     * @param email    endereço de correio eletrônico
     */
    public User(String username, String email) {
        this(username, email, 0);
    }

    /**
     * Construtor 3: Cadastro Completo (nome, e-mail e idade).
     * Construtor mestre que recebe todos os dados e inicializa os campos.
     *
     * @param username apelido do usuário
     * @param email    endereço de correio eletrônico
     * @param age      idade do usuário em anos
     */
    public User(String username, String email, int age) {
        this.username = Objects.requireNonNull(username, "Nome de usuário não pode ser nulo.");
        this.email = email != null ? email : "Not provided";
        this.age = Math.max(age, 0);
    }

    /**
     * Retorna a descrição sumária do perfil cadastrado.
     *
     * @return representação textual do perfil
     */
    public String getProfile() {
        return "Username: " + username + " | Email: " + email + " | Age: " + age;
    }
}
