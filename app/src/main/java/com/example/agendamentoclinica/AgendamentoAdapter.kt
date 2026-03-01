package com.example.agendamentoclinica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AgendamentoAdapter(
    private val lista: List<Agendamento>,
    private val onEditClick: (Agendamento) -> Unit,
    private val onDeleteClick: (Agendamento) -> Unit
) : RecyclerView.Adapter<AgendamentoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNome: TextView = view.findViewById(R.id.tvNomeItem)
        val tvData: TextView = view.findViewById(R.id.tvDataItem)

        // Botões
        val btnDeletar: Button = view.findViewById(R.id.btnDeletar)
        val btnEditar: Button = view.findViewById(R.id.btnEditar) // Novo botão
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_agendamento, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        holder.tvNome.text = item.nomePaciente
        holder.tvData.text = item.dataConsulta

        // Clique no Botão EXCLUIR
        holder.btnDeletar.setOnClickListener {
            onDeleteClick(item)
        }

        // Clique no Botão EDITAR
        holder.btnEditar.setOnClickListener {
            onEditClick(item)
        }

        // Mantive o clique no cartão inteiro como backup (opcional)
        holder.itemView.setOnClickListener {
            onEditClick(item)
        }
    }

    override fun getItemCount() = lista.size
}