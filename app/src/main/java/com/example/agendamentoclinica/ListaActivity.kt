package com.example.agendamentoclinica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvListaVazia: TextView
    private lateinit var adapter: AgendamentoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        // CONFIGURAÇÃO DA BARRA E SETA DE VOLTAR
        val toolbar = findViewById<Toolbar>(R.id.toolbarLista)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerView)
        tvListaVazia = findViewById(R.id.tvListaVazia)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onResume() {
        super.onResume()
        atualizarLista()
    }

    private fun atualizarLista() {
        val lista = AgendamentoRepository.listar()

        if (lista.isEmpty()) {
            recyclerView.visibility = View.GONE
            tvListaVazia.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            tvListaVazia.visibility = View.GONE

            adapter = AgendamentoAdapter(lista,
                onEditClick = { agendamento ->
                    val intent = Intent(this, FormActivity::class.java)
                    intent.putExtra("id_agendamento", agendamento.id)
                    startActivity(intent)
                },
                onDeleteClick = { agendamento ->
                    mostrarConfirmacaoExclusao(agendamento)
                }
            )
            recyclerView.adapter = adapter
        }
    }

    private fun mostrarConfirmacaoExclusao(agendamento: Agendamento) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Excluir Agendamento")
        builder.setMessage("Tem certeza que deseja remover o paciente ${agendamento.nomePaciente}?")

        builder.setPositiveButton("Sim, excluir") { dialog, _ ->
            AgendamentoRepository.deletar(agendamento.id)
            atualizarLista()
            Toast.makeText(this, "Agendamento excluído!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}