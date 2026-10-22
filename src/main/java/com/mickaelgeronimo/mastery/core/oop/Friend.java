package com.mickaelgeronimo.mastery.core.oop;

import java.util.Objects;

/**
 * Entidade Amigo representativa do modificador {@code static} em Java (Capítulo 43).
 * Demonstra o uso de variáveis estáticas para recursos compartilhados (contagem global)
 * e métodos estáticos que pertencem à classe e não a um objeto específico.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class Friend {

    // Atributo de instância: cada amigo tem o seu próprio nome
    public String name;

    // Atributo estático: compartilhado por toda a classe Friend (uma única gaveta na memória)
    public static int numOfFriends;

    /**
     * Construtor que recebe o nome do amigo e incrementa automaticamente o contador estático.
     *
     * @param name nome do amigo
     */
    public Friend(String name) {
        this.name = Objects.requireNonNull(name, "Nome do amigo não pode ser nulo.");
        numOfFriends++;
    }

    /**
     * Retorna a mensagem formatada de contagem total de amigos.
     *
     * @return texto no formato "You have X total friends"
     */
    public static String getShowFriendsMessage() {
        return "You have " + numOfFriends + " total friends";
    }

    /**
     * Método estático que exibe no console a contagem total de amigos.
     * Pertence à classe Friend e pode ser chamado via {@code Friend.showFriends()}.
     */
    public static void showFriends() {
        System.out.println(getShowFriendsMessage());
    }

    /**
     * Reinicia o contador estático para zero.
     * Essencial para isolamento e repetibilidade de testes automatizados.
     */
    public static void resetFriendsCount() {
        numOfFriends = 0;
    }
}
