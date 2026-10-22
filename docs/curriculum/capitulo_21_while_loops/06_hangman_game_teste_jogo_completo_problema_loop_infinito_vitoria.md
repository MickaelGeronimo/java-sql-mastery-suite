# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 7: Jogando a Partida de Teste, a Palavra Completa e o Bug do Loop Infinito

---

## 🧭 Visão Geral Desta Etapa

Nesta sétima parte do **Capítulo 61**, o instrutor Bro Code executa o primeiro teste completo de jogabilidade do nosso protótipo de forca!

Aqui vemos momentos de puro aprendizado prático:
1. **O Jogo Funciona em Tempo Real (Imagens 1, 2 e 3)**: O jogador vai chutando letras e o tabuleiro vai se completando progressivamente:
   * Começa em: `_ _ _ _ _`
   * Digita `'z'`: vira `_ _ z z _` (duplo acerto!)
   * Digita `'x'`: erro! O tabuleiro se mantém `_ _ z z _`
   * Digita `'p'`: vira `p _ z z _`
   * Digita `'i'`: vira `p i z z _`
   * Digita `'a'`: vira `p i z z a` (palavra 100% adivinhada!)
2. **O Grande Problema Revelado ao Vivo (Imagem 3)**: Mesmo com a palavra `"pizza"` totalmente preenchida na tela, o computador continua perguntando teimosamente:  
   `Guess a letter: `!  
   Por que o jogo não diz *"YOU WIN!"*? Por que ele não para?
3. **Análise dos 2 Furos Lógicos que Precisam ser Corrigidos**:
   * **Furo 1 (Falta da Condição de Vitória)**: O `while(wrongGuesses < 6)` só sabe quando o jogador perde, mas não sabe quando o jogador ganha!
   * **Furo 2 (Falta de Punição no Erro)**: Ao chutar `'x'`, o jogo disse `"Wrong guess!"`, mas `wrongGuesses` não aumentou e nenhum boneco foi desenhado na forca!

---

## 📸 Reconstituição Visual Completa das 3 Imagens (Para o Google Docs)

```
====================================================================================================
IMAGEM 1: SEGUNDA RODADA — APÓS O ACERTO DUPLO DE 'z' (TIMESTAMP INICIAL)
====================================================================================================
Console "Run" (Terminal inferior):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 
  Guess a letter: z
  Correct guess!

  Word: _ _ z z _ 
  Guess a letter: █  <-- [Cursor aguardando o 2º palpite]

====================================================================================================
IMAGEM 2: TESTANDO PALPITE ERRADO 'x' NO MEIO DO JOGO (TIMESTAMP 23:33 DO VÍDEO)
====================================================================================================
Console "Run" (Terminal inferior):
  Word: _ _ _ _ _ 
  Guess a letter: z
  Correct guess!

  Word: _ _ z z _ 
  Guess a letter: x
  Wrong guess!

  Word: _ _ z z _ 
  Guess a letter: █  <-- [O tabuleiro não mudou e o jogo continua]

====================================================================================================
IMAGEM 3: VITÓRIA COMPLETA ("pizza") COM O JOGO PRESO NO LOOP (TIMESTAMP 23:33)
====================================================================================================
Console "Run" (O instrutor seleciona com o mouse as letras finais "z a"):
  Word: p _ z z _ 
  Guess a letter: i
  Correct guess!

  Word: p i z z _ 
  Guess a letter: a
  Correct guess!

  Word: p i z z a 
  Guess a letter: █  <-- [A PALAVRA ESTÁ COMPLETA, MAS O JOGO NÃO PARA!]
```

---

## 🧠 Explicação Didática Como se Fosse um Iniciante Amador em Java

### 1. A Evolução Mágica da Partida Passo a Passo
Observe como a combinação do laço `while` com o `wordState.set(i, guess)` criou uma sensação real de videogame:

1. **Rodada 1**: O console mostra 5 tracinhos: `_ _ _ _ _`. O jogador chuta `'z'`.
   * Acontece o acerto duplo nas posições 2 e 3.
   * Mensagem: `"Correct guess!\n"`.
2. **Rodada 2**: O laço `while` volta ao topo e redesenha o tabuleiro:
   `Word: _ _ z z _`
   * O jogador chuta `'x'`.
   * Mensagem: `"Wrong guess!\n"`.
3. **Rodada 3**: O tabuleiro é desenhado novamente. Como `'x'` não existia, continuou:
   `Word: _ _ z z _`
   * O jogador chuta `'p'`.
   * A posição 0 é atualizada: `Word: p _ z z _`.
4. **Rodada 4**: O jogador chuta `'i'`.
   * A posição 1 é atualizada: `Word: p i z z _`.
5. **Rodada 5**: O jogador chuta `'a'`.
   * A posição 4 é atualizada: `Word: p i z z a`!

---

### 2. Por que o jogo não parou quando formou `"pizza"` (Imagem 3)?
Veja o código da nossa condição de repetição na Linha 23:
```java
while(wrongGuesses < 6){
    // ...
}
```
Pense como o processador do computador (que é extremamente obediente e literal):
* O processador olha para a variável `wrongGuesses`. Quanto ela vale? Vale `0` (ou `1`).
* O processador faz a pergunta: *"O número de erros é menor do que 6?"*
* `0 < 6`? **SIM (true)!**
* Como a resposta é verdadeira, ele **continua rodando o loop indefinidamente**!
* O computador não tem intuição humana. Ele **não sabe** que o jogo é de forca e **não sabe** que `"pizza"` é a palavra final, a não ser que nós, programadores, digamos explicitamente:  
  *"Ei Java, se não houver mais nenhum tracinho `'_'` na lista, pare o jogo porque o jogador venceu!"*

---

### 3. Os Dois Grandes Furos do Código Atual

> [!WARNING]
> **Furo nº 1: O Jogador é Imortal!**  
> Na Imagem 2, quando chutamos `'x'`, o código executou:
> ```java
> else{
>     System.out.println("Wrong guess!\n");
> }
> ```
> Nós esquecemos de colocar `wrongGuesses++`! Como a variável `wrongGuesses` nunca aumenta, o jogador pode errar 500 vezes que nunca vai morrer e o boneco da forca nunca vai aparecer!

> [!IMPORTANT]
> **Furo nº 2: O Jogador Nunca Vence!**  
> Quando todas as letras são descobertas (`p i z z a`), a lista `wordState` não contém mais nenhum sublinhado `'_'`. Precisamos de uma checagem com `!wordState.contains('_')` para dar a vitória e sair do laço!

---

## 🔬 Análise da Execução no Console em Cada Rodada

| Rodada | Entrada do Usuário | Condição Testada | Tabuleiro Resultante | Estado do Jogo |
| :---: | :---: | :---: | :---: | :--- |
| **Início** | — | — | `_ _ _ _ _` | Jogo iniciado |
| **1ª** | `'z'` | `'z'` existe em 2 e 3 | `_ _ z z _` | Acerto duplo |
| **2ª** | `'x'` | `'x'` não existe | `_ _ z z _` | Erro (sem penalidade ainda) |
| **3ª** | `'p'` | `'p'` existe em 0 | `p _ z z _` | Acerto |
| **4ª** | `'i'` | `'i'` existe em 1 | `p i z z _` | Acerto |
| **5ª** | `'a'` | `'a'` existe em 4 | `p i z z a` | **Vitória lógica (aguardando código)** |

---

## 🖥️ Saída Completa e Exata do Terminal na Demonstração

```text
************************
Welcome to Java Hangman!
************************
Word: _ _ _ _ _ 
Guess a letter: z
Correct guess!

Word: _ _ z z _ 
Guess a letter: x
Wrong guess!

Word: _ _ z z _ 
Guess a letter: p
Correct guess!

Word: p _ z z _ 
Guess a letter: i
Correct guess!

Word: p i z z _ 
Guess a letter: a
Correct guess!

Word: p i z z a 
Guess a letter: █
```

*(O instrutor pausou a gravação exatamente aqui para mostrar para a turma: "Vejam só, o jogo está quase perfeito, mas agora precisamos implementar a lógica de vitória quando acabarem os tracinhos e desenhar a forca com `wrongGuesses++` quando o usuário errar!" Isso é o que faremos no próximo passo!)*
