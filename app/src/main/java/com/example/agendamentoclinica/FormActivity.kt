package com.example.agendamentoclinica

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormActivity : AppCompatActivity() {

    private var agendamentoId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etData = findViewById<EditText>(R.id.etData)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)

        // Adiciona máscara de data
        addMascaraData(etData)

        // Verifica se é edição
        agendamentoId = intent.getLongExtra("id_agendamento", 0L)

        if (agendamentoId != 0L) {
            val agendamentoExistente = AgendamentoRepository.buscarPorId(agendamentoId)
            if (agendamentoExistente != null) {
                etNome.setText(agendamentoExistente.nomePaciente)
                etData.setText(agendamentoExistente.dataConsulta)
                btnConfirmar.text = "ATUALIZAR"
            }
        }

        btnConfirmar.setOnClickListener {
            val nome = etNome.text.toString()
            val data = etData.text.toString()

            if (nome.isNotEmpty() && data.length == 10) {
                val agendamento = Agendamento(
                    id = agendamentoId,
                    nomePaciente = nome,
                    dataConsulta = data
                )
                AgendamentoRepository.salvar(agendamento)

                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Preencha tudo corretamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addMascaraData(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            var isUpdating = false
            var old = ""

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = s.toString().replace("\\D".toRegex(), "")
                var mascara = ""

                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                var i = 0
                val mask = "##/##/####"
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }

                isUpdating = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
    }
}