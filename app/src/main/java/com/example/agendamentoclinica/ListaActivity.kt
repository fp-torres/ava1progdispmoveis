package com.example.agendamentoclinica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog // Importante para o alerta
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvListaVazia: TextView
    private lateinit var adapter: AgendamentoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        // Vincula os componentes da tela
        recyclerView = findViewById(R.id.recyclerView)
        tvListaVazia = findViewById(R.id.tvListaVazia)

        // Define como a lista deve se comportar (Vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        // Toda vez que a tela aparecer, atualiza os dados
        atualizarLista()
    }

    private fun atualizarLista() {
        // Busca os dados atualizados do Repositório
        val lista = AgendamentoRepository.listar()

        // Lógica para mostrar lista ou aviso de vazio
        if (lista.isEmpty()) {
            recyclerView.visibility = View.GONE
            tvListaVazia.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            tvListaVazia.visibility = View.GONE

            // Cria o adaptador passando as ações de clique
            adapter = AgendamentoAdapter(lista,
                onEditClick = { agendamento ->
                    // Ao clicar para EDITAR: Vai para o formulário
                    val intent = Intent(this, FormActivity::class.java)
                    intent.putExtra("id_agendamento", agendamento.id)
                    startActivity(intent)
                },
                onDeleteClick = { agendamento ->
                    // Ao clicar para DELETAR: Mostra a confirmação antes
                    mostrarConfirmacaoExclusao(agendamento)
                }
            )
            recyclerView.adapter = adapter
        }
    }

    // Função que cria o alerta "Tem certeza?"
    private fun mostrarConfirmacaoExclusao(agendamento: Agendamento) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Excluir Agendamento")
        builder.setMessage("Tem certeza que deseja remover o paciente ${agendamento.nomePaciente}?")

        // Botão Positivo (Sim)
        builder.setPositiveButton("Sim, excluir") { dialog, _ ->
            AgendamentoRepository.deletar(agendamento.id)
            atualizarLista() // Atualiza a tela imediatamente
            Toast.makeText(this, "Agendamento excluído!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        // Botão Negativo (Não)
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}