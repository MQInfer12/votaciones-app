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
import com.example.finalapp.model.Asignacion
import com.example.finalapp.model.Votacion
import com.example.finalapp.repository.Repository
import com.example.finalapp.utils.Global

class MisVotaciones : AppCompatActivity() {
    private lateinit var viewModel: MisVotacionesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_votaciones)

        val list = findViewById<ListView>(R.id.listMisVotaciones)
        val repository = Repository()
        val viewModelFactory = MisVotacionesViewModelFactory(repository)
        var asignaciones: List<Asignacion> = mutableListOf()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MisVotacionesViewModel::class.java)
        viewModel.getAsignaciones(Global.ci)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {
                asignaciones = response.body()!!.data;
                val datos = mutableListOf<String>();
                for(i in asignaciones.indices){
                    datos.add(asignaciones[i].Votacion.nombre)
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)
                list.adapter = adapter
            } else {
                Log.d("Response", response.errorBody().toString())
            }
        })

        list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val idAsignacion = asignaciones[position].id
            val idVotacion = asignaciones[position].idVotacion
            val intent = Intent(this, Votar::class.java)
            intent.putExtra("idAsignacion", idAsignacion)
            intent.putExtra("idVotacion", idVotacion)
            startActivity(intent)
        }

        val btnAtras = findViewById<Button>(R.id.btnAtras)
        btnAtras.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}