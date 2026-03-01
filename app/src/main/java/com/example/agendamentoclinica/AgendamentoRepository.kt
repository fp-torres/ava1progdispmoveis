package com.example.agendamentoclinica

// 1. CLASSE DE DADOS (MODELO)
data class Agendamento(
    var id: Long = 0,
    var nomePaciente: String,
    var dataConsulta: String
)

// 2. REPOSITÓRIO (ONDE SALVA NA MEMÓRIA)
object AgendamentoRepository {

    // AGORA A LISTA COMEÇA VAZIA NOVAMENTE
    private val agendamentos = mutableListOf<Agendamento>()

    private var proximoId: Long = 1

    fun salvar(agendamento: Agendamento) {
        if (agendamento.id == 0L) {
            // É um NOVO agendamento (Create)
            agendamento.id = proximoId++
            agendamentos.add(agendamento)
        } else {
            // É uma EDIÇÃO de um existente (Update)
            val index = agendamentos.indexOfFirst { it.id == agendamento.id }
            if (index != -1) {
                agendamentos[index] = agendamento
            }
        }
    }

    fun listar(): List<Agendamento> {
        return agendamentos
    }

    fun deletar(id: Long) {
        agendamentos.removeAll { it.id == id }
    }

    fun buscarPorId(id: Long): Agendamento? {
        return agendamentos.find { it.id == id }
    }
}