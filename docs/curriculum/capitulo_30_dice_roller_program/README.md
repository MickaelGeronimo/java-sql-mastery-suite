# 🎲 Capítulo 30: Dice Roller Program (Simulador de Lançamento de Dados com Arte ASCII)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 30)

---

## 🧭 Visão Geral

O **Dice Roller Program** consolida os pilares de lógica algorítmica e modularidade:
- Validação defensiva de entrada (`numOfDice > 0`)
- Sorteio pseudo-aleatório balanceado (`Random.nextInt(1, 7)`)
- Acumulação composta de totais (`total += roll`)
- Renderização visual em terminal via **ASCII Art** utilizando Text Blocks Java (`"""`)
- Decomposição em métodos auxiliares (`printDie(int roll)`)
- Estrutura de seleção rápida com Enhanced Switch (`case 1 -> ...`)

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_planejamento_e_validacao.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_30_dice_roller_program/01_planejamento_e_validacao.md) | Estratégia de planejamento com comentários e validação de aridade defensiva. |
| [02_laco_for_e_sorteio.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_30_dice_roller_program/02_laco_for_e_sorteio.md) | Iteração controlada por `for` e geração de intervalos `[1, 6]` com `random.nextInt(1, 7)`. |
| [03_acumulador_total_e_soma.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_30_dice_roller_program/03_acumulador_total_e_soma.md) | Variável acumuladora `total` e ciclo de vida de escopo local. |
| [04_metodo_printDie_ascii_art.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_30_dice_roller_program/04_metodo_printDie_ascii_art.md) | Decomposição do método `printDie` e representação de faces em Text Blocks. |
| [05_switch_e_execucao_final.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_30_dice_roller_program/05_switch_e_execucao_final.md) | O switch expression final, depuração de duplicate cases e homologação no console. |
| [apostila_completa_dice_roller_program_capitulo30.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_30_dice_roller_program/apostila_completa_dice_roller_program_capitulo30.md) | Apostila definitiva e unificada contendo a galeria ASCII completa e código compilável. |

---

## 🎲 Galeria Visual das Faces do Dado

```text
  Face 1         Face 2         Face 3         Face 4         Face 5         Face 6
 ---------      ---------      ---------      ---------      ---------      ---------
|         |    | ●       |    | ●       |    | ●     ● |    | ●     ● |    | ●     ● |
|    ●    |    |         |    |    ●    |    |         |    |    ●    |    | ●     ● |
|         |    |       ● |    |       ● |    | ●     ● |    | ●     ● |    | ●     ● |
 ---------      ---------      ---------      ---------      ---------      ---------
```

---

## 💻 Código-Fonte e Testes no Projeto
- Motor de Simulação e Renderização: [`DiceRollerEngine.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/games/DiceRollerEngine.java)
- CLI Interativa: [`DiceRollerCLI.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/games/DiceRollerCLI.java)
- Suíte de Testes Automatizados: [`DiceRollerTest.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/test/java/com/mickaelgeronimo/mastery/core/DiceRollerTest.java)
