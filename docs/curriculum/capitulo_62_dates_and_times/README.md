# Capítulo 62: Datas e Horas Modernas (dates & times)

A API java.time do Java 8+: LocalDate, LocalTime, LocalDateTime, Instant, formatação customizada com DateTimeFormatter e métodos comparadores (isBefore, isAfter, isEqual).

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
| :---: | :--- | :--- |\n| **01** | [**01_dates_and_times_localdatetime_comparacoes_isbefore_isafter_isequal.md**](./01_dates_and_times_localdatetime_comparacoes_isbefore_isafter_isequal.md) | Curso de Java Completo (Bro Code) — Capítulo 62: `dates & times ⌚` (Parte 1) |
| **02** | [**02_dates_and_times_introducao_localdate_now.md**](./02_dates_and_times_introducao_localdate_now.md) | Curso de Java Completo (Bro Code) — Capítulo 62: `dates & times 📆` (Parte 1) |
| **03** | [**03_dates_and_times_localtime_localdatetime_instant_utc.md**](./03_dates_and_times_localtime_localdatetime_instant_utc.md) | Curso de Java Completo (Bro Code) — Capítulo 62: `dates & times 📆` (Parte 2) |
| **04** | [**04_dates_and_times_datetimeformatter_ofpattern_custom_format.md**](./04_dates_and_times_datetimeformatter_ofpattern_custom_format.md) | Curso de Java Completo (Bro Code) — Capítulo 62: `dates & times 📆` (Parte 3) |
| **05** | [**05_dates_and_times_metodo_of_criando_datas_especificas.md**](./05_dates_and_times_metodo_of_criando_datas_especificas.md) | Curso de Java Completo (Bro Code) — Capítulo 62: `dates & times 📆` (Parte 4) |
| **06** | [**06_dates_and_times_criando_duas_datas_date1_date2_localdatetime_of.md**](./06_dates_and_times_criando_duas_datas_date1_date2_localdatetime_of.md) | Curso de Java Completo (Bro Code) — Capítulo 62: `dates & times 📆` (Parte 5) |
| **07** | [**07_dates_and_times_comparando_datas_isbefore_isafter.md**](./07_dates_and_times_comparando_datas_isbefore_isafter.md) | Curso de Java Completo (Bro Code) — Capítulo 62: `dates & times 📆` (Parte 6) |
| **08** | [**08_dates_and_times_comparacao_igualdade_isequal.md**](./08_dates_and_times_comparacao_igualdade_isequal.md) | Curso de Java Completo (Bro Code) — Capítulo 62: `dates & times 📆` (Parte 7) |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
