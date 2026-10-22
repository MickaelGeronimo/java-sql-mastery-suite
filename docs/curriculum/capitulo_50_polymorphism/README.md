# Capítulo 50: Polimorfismo (polymorphism)

A essência de 'muitas formas' em POO: tratar subclasses distintas sob a mesma referência de superclasse genérica, iteração de arrays heterogêneos e invocação de métodos dinâmicos.

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
| :---: | :--- | :--- |\n| **01** | [**01_polimorfismo_definicao_etimologia_e_analogia_do_cachorro.md**](./01_polimorfismo_definicao_etimologia_e_analogia_do_cachorro.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **02** | [**02_polimorfismo_criacao_vehicle_e_car.md**](./02_polimorfismo_criacao_vehicle_e_car.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **03** | [**03_polimorfismo_criacao_bike_e_sobrescrita.md**](./03_polimorfismo_criacao_bike_e_sobrescrita.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **04** | [**04_polimorfismo_criacao_boat_e_o_trio_de_veiculos.md**](./04_polimorfismo_criacao_boat_e_o_trio_de_veiculos.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **05** | [**05_polimorfismo_instanciacao_tripla_e_chamadas_individuais.md**](./05_polimorfismo_instanciacao_tripla_e_chamadas_individuais.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **06** | [**06_polimorfismo_o_erro_didatico_do_array_de_carros.md**](./06_polimorfismo_o_erro_didatico_do_array_de_carros.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **07** | [**07_polimorfismo_a_trilogia_de_erros_especificos.md**](./07_polimorfismo_a_trilogia_de_erros_especificos.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **08** | [**08_polimorfismo_a_solucao_do_array_de_vehicles_e_o_inicio_do_loop.md**](./08_polimorfismo_a_solucao_do_array_de_vehicles_e_o_inicio_do_loop.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **09** | [**09_polimorfismo_o_loop_magico_e_a_transicao_para_interface.md**](./09_polimorfismo_o_loop_magico_e_a_transicao_para_interface.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **10** | [**10_polimorfismo_recriando_vehicle_como_interface_e_o_erro_de_visibilidade.md**](./10_polimorfismo_recriando_vehicle_como_interface_e_o_erro_de_visibilidade.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **11** | [**11_polimorfismo_com_interfaces_e_a_execucao_final.md**](./11_polimorfismo_com_interfaces_e_a_execucao_final.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |
| **12** | [**12_polimorfismo_a_revisao_final_e_o_resumo_definitivo.md**](./12_polimorfismo_a_revisao_final_e_o_resumo_definitivo.md) | Curso de Java â€” CapÃ­tulo 50: Polimorfismo (`polymorphism ðŸŽ­`) |\n\n---\n\n> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.\n
