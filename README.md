# 🏥 Agendamento Clínica

![Kotlin](https://img.shields.io/badge/Kotlin-0095D4?style=for-the-badge&logo=kotlin&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge)

Um aplicativo Android nativo desenvolvido para simular um sistema de gerenciamento de consultas médicas. O projeto foca na implementação de um **CRUD completo** (Create, Read, Update, Delete) utilizando componentes modernos da interface Android e boas práticas de programação.

## 📋 Sobre o Projeto

O **Agendamento Clínica** permite que o usuário marque consultas, visualize uma lista de pacientes agendados, edite informações incorretas e exclua agendamentos com segurança. O objetivo principal foi consolidar conhecimentos sobre `RecyclerView`, `Intents`, `Dialogs` e manipulação de dados em memória.

## 🚀 Funcionalidades Principais

* **Agendamento Rápido:** Formulário intuitivo para cadastro de nome do paciente e data da consulta.
* **Listagem Dinâmica:** Visualização de todos os agendamentos em uma lista de rolagem vertical (RecyclerView).
* **Edição de Dados:** Botão dedicado **"EDITAR"** (Azul) que carrega os dados atuais para alteração.
* **Exclusão Segura:** Botão **"EXCLUIR"** (Vermelho) que aciona um **Alerta de Confirmação** ("Tem certeza?") para evitar remoções acidentais.
* **Validação e Máscara:**
    * Formatação automática de data no padrão brasileiro (DD/MM/AAAA) enquanto o usuário digita.
    * Bloqueio de cadastro com campos vazios.
* **Feedback ao Usuário:** Mensagens flutuantes (Toasts) confirmando ações de sucesso e tratamento visual para quando a lista está vazia.

## 🛠 Tecnologias e Arquitetura

O projeto foi construído utilizando **Kotlin** e segue uma arquitetura organizada:

* **View Layer (XML & Activities):**
    * `ConstraintLayout`: Para layouts responsivos e organizados.
    * `CardView`: Para estilização dos itens da lista.
    * `AlertDialog`: Para janelas de confirmação.
* **Logic Layer (Kotlin):**
    * **RecyclerView & Adapter:** Uso de um adaptador customizado (`AgendamentoAdapter`) para renderizar a lista e gerenciar os eventos de clique nos botões de Editar e Excluir.
    * **Repository Pattern:** Implementação de um objeto Singleton (`AgendamentoRepository`) que atua como um "banco de dados em memória", centralizando as operações de Salvar, Listar, Buscar por ID e Deletar.
    * **TextWatcher:** Utilizado para aplicar a lógica de máscara no campo de data em tempo real.

## 📂 Estrutura de Arquivos

* `MainActivity.kt`: Tela inicial com navegação para cadastro ou listagem.
* `FormActivity.kt`: Tela responsável por Criar (Create) e Editar (Update) os agendamentos.
* `ListaActivity.kt`: Tela que exibe os dados (Read) e gerencia a remoção (Delete).
* `AgendamentoAdapter.kt`: Controla a exibição de cada item individual na lista.
* `AgendamentoRepository.kt`: Lógica de armazenamento dos dados.

## 📦 Como Rodar

1.  Clone este repositório.
2.  Abra o projeto no **Android Studio**.
3.  Aguarde o Gradle sincronizar as dependências.
4.  Execute o app em um Emulador ou Dispositivo Físico.

---
Desenvolvido por **Felipe Torres** 💻
