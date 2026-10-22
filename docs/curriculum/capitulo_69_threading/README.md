# Capítulo 69: Threads e Execução Paralela (threading)

Fundamentos de execução concorrente na JVM: Thread, Runnable, ciclo de vida da thread (start, run, sleep), paralelização de tarefas e a thread principal (main).

---

## 🧭 Visão Geral e Arquitetura Conceitual

Este capítulo aborda de ponta a ponta a teoria, sintaxe, boas práticas e armadilhas comuns encontradas na prática profissional ao lidar com este tópico em Java.

### 🎯 Objetivos de Aprendizagem
- Dominar a sintaxe fundamental e convenções da linguagem Java.
- Compreender a anatomia interna e comportamento na JVM.
- Reconhecer e contornar armadilhas e erros de compilação clássicos.
- Aplicar o conceito em arquiteturas limpas e desacopladas.

---

## 📚 Índice Modular dos Materiais de Estudo

Todos os documentos detalhados de estudo e transcrição didática deste capítulo estão catalogados abaixo:

| Parte | Arquivo | Tema Principal |
| :---: | :--- | :--- |
| **01** | [**01_threading_conceito_definicao_e_problema_timer.md**](file:///docs/curriculum/capitulo_69_threading/01_threading_conceito_definicao_e_problema_timer.md) | Curso de Java â€” Bro Code (12 Horas) |
| **02** | [**02_threading_tentativa_ingenuo_sleep_loop_e_erro_sequencial.md**](file:///docs/curriculum/capitulo_69_threading/02_threading_tentativa_ingenuo_sleep_loop_e_erro_sequencial.md) | Curso de Java â€” Bro Code (12 Horas) |
| **03** | [**03_threading_criacao_myrunnable_interface_e_metodo_run.md**](file:///docs/curriculum/capitulo_69_threading/03_threading_criacao_myrunnable_interface_e_metodo_run.md) | Curso de Java â€” Bro Code (12 Horas) |
| **04** | [**04_threading_instanciacao_thread_start_e_execucao_paralela.md**](file:///docs/curriculum/capitulo_69_threading/04_threading_instanciacao_thread_start_e_execucao_paralela.md) | Curso de Java â€” Bro Code (12 Horas) |
| **05** | [**05_threading_daemon_threads_setdaemon_e_10_segundos.md**](file:///docs/curriculum/capitulo_69_threading/05_threading_daemon_threads_setdaemon_e_10_segundos.md) | Java Full Course â€” Bro Code |
| **06** | [**06_threading_teste_timeout_meme_awkward_e_independencia.md**](file:///docs/curriculum/capitulo_69_threading/06_threading_teste_timeout_meme_awkward_e_independencia.md) | Java Full Course â€” Bro Code |
| **07** | [**07_threading_system_exit_encerramento_forcado_e_resumo_teorico.md**](file:///docs/curriculum/capitulo_69_threading/07_threading_system_exit_encerramento_forcado_e_resumo_teorico.md) | Java Full Course â€” Bro Code |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
