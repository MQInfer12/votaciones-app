package com.example.finalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.model.Candidato
import com.example.finalapp.model.Votacion
import com.example.finalapp.model.Votar
import com.example.finalapp.repository.Repository
import com.example.finalapp.utils.Global

class Votar : AppCompatActivity() {
    private lateinit var viewModel: VotarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_votar)
        val idAsignacion = intent.getIntExtra("idAsignacion", -1)
        val idVotacion = intent.getIntExtra("idVotacion", -1)

        val list = findViewById<ListView>(R.id.listOpciones)
        val repository = Repository()
        val viewModelFactory = VotarViewModelFactory(repository)
        var candidatos: List<Candidato> = mutableListOf()
        viewModel = ViewModelProvider(this, viewModelFactory).get(VotarViewModel::class.java)
        viewModel.getAsignaciones(idVotacion)
        viewModel.getResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {
                candidatos = response.body()!!.data;
                val datos = mutableListOf<String>();
                for(i in candidatos.indices){
                    datos.add(candidatos[i].nombre)
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)
                list.adapter = adapter
            } else {
                Log.d("Response", response.errorBody().toString())
            }
        })

        list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val idCandidato = candidatos[position].id
            val data = Votar(idCandidato)
            viewModel.putVotar(idAsignacion, data)
            viewModel.putResponse.observe(this, Observer { response ->
                if(response.isSuccessful) {
                    val intent = Intent(this, Thanks::class.java)
                    startActivity(intent)
                } else {
                    Log.d("Response", response.errorBody().toString())
                }
            })
        }

        val btnAtras = findViewById<Button>(R.id.btnAtras)
        btnAtras.setOnClickListener {
            val intent = Intent(this, MisVotaciones::class.java)
            startActivity(intent)
        }
    }
}