# 💰 Capítulo 29: Banking Program (Simulador de Caixa Eletrônico / ATM)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 29)

---

## 🧭 Visão Geral

O **Banking Program** é o primeiro projeto prático unificado do curso. Ele integra em uma aplicação interativa e funcional os principais conceitos dos capítulos anteriores:
- Laço de repetição de longa duração (`while (isRunning)`)
- Controle de fluxo com Enhanced Switch (`switch (choice) ->`)
- Decomposição modular em métodos desacoplados (`showBalance`, `deposit`, `withdraw`)
- Escopo de classe (`static Scanner scanner`)
- Validações de domínio financeiro (depósitos estritamente positivos, saques sem saldo a descoberto)
- Formatação monetária com `printf` e especificadores `%s` e `%.2f`

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_planejamento_e_menu.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/01_planejamento_e_menu.md) | Arquitetura inicial do sistema ATM e layout visual do menu. |
| [02_entrada_usuario_e_switch.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/02_entrada_usuario_e_switch.md) | Captura de comando numérico (1 a 4) e estrutura de decisão switch. |
| [03_loop_infinito_com_while.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/03_loop_infinito_com_while.md) | Implementação do *Event Loop* bancário com a flag `isRunning`. |
| [04_exit_scanner_close_e_showbalance.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/04_exit_scanner_close_e_showbalance.md) | Tratamento do encerramento com liberação de recursos (`scanner.close()`). |
| [05_saldo_e_conectando_showbalance.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/05_saldo_e_conectando_showbalance.md) | Inicialização da variável de estado `balance = 0.0`. |
| [06_showbalance_e_quebra_de_linha.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/06_showbalance_e_quebra_de_linha.md) | Ajustes visuais de separação de linhas e legibilidade do terminal. |
| [07_formatacao_moeda_duas_casas.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/07_formatacao_moeda_duas_casas.md) | Formatação monetária com `printf("$%.2f")`. |
| [08_teste_saldo_real_com_centavos.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/08_teste_saldo_real_com_centavos.md) | Testes com valores flutuantes fracionários e arredondamento financeiro. |
| [09_moldura_de_asteriscos_no_showbalance.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/09_moldura_de_asteriscos_no_showbalance.md) | Estilização gráfica de cabeçalhos e caixas de diálogo no terminal. |
| [10_finalizacao_showbalance_e_code_folding.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/10_finalizacao_showbalance_e_code_folding.md) | Fechamento do método de exibição e organização na IDE. |
| [11_criando_metodo_deposit_e_escopo_scanner.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/11_criando_metodo_deposit_e_escopo_scanner.md) | Elevação do `Scanner` para escopo de classe (`static`) para evitar conflitos de I/O. |
| [12_validacao_if_else_deposit_e_conexao_main.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/12_validacao_if_else_deposit_e_conexao_main.md) | Regra de negócio: Rejeição de depósitos negativos ou nulos. |
| [13_operador_mais_igual_e_testes_deposito.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/13_operador_mais_igual_e_testes_deposito.md) | Atualização de saldo com o operador de atribuição cumulativa `balance += amount`. |
| [14_metodo_withdraw_e_operador_menos_igual.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/14_metodo_withdraw_e_operador_menos_igual.md) | Regra de negócio do saque: Validação dupla (valor positivo E saldo suficiente). |
| [15_testes_saque_e_mensagem_final.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/15_testes_saque_e_mensagem_final.md) | Casos de teste de fundos insuficientes (*Insufficient Funds*). |
| [16_demonstracao_completa_e_teste_centavos.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/16_demonstracao_completa_e_teste_centavos.md) | Roteiro de homologação com fluxo ponta a ponta (depósito, saldo, saque e centavos). |
| [17_execucao_ao_vivo_opcao_exit.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/17_execucao_ao_vivo_opcao_exit.md) | Teste de saída limpa e fechamento de conexões. |
| [apostila_completa_banking_program_capitulo29.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_29_banking_program/apostila_completa_banking_program_capitulo29.md) | Apostila unificada contendo todos os 17 passos, diagramas de fluxo e código completo. |

---

## 🏛️ Arquitetura Funcional do Sistema ATM

```text
                         ┌─────────────────────────────┐
                         │      MÉTODO PRINCIPAL       │
                         │         main(...)           │
                         └──────────────┬──────────────┘
                                        │
         ┌──────────────────────────────┼──────────────────────────────┐
         ▼                              ▼                              ▼
┌──────────────────┐           ┌──────────────────┐           ┌──────────────────┐
│   showBalance()  │           │    deposit()     │           │    withdraw()    │
│  (Apenas Exibe)  │           │ (Retorna double) │           │ (Retorna double) │
└──────────────────┘           └──────────────────┘           └──────────────────┘
```

---

## 💻 Código-Fonte e Testes no Projeto
- Classe Didática & Motor Bancário: [`BankingAccount.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/banking/BankingAccount.java)
- CLI Interativa do Caixa Eletrônico: [`BankingProgramCLI.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/banking/BankingProgramCLI.java)
- Suíte de Testes Automatizados: [`BankingProgramTest.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/test/java/com/mickaelgeronimo/mastery/core/BankingProgramTest.java)
