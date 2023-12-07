package com.example.finalapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.model.VerVotacion
import com.example.finalapp.model.Votacion
import com.example.finalapp.repository.Repository
import com.example.finalapp.utils.Global

class Details : AppCompatActivity() {
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val txtIdVotacion = findViewById<TextView>(R.id.txtIdVotacion)
        val idVotacion = intent.getIntExtra("idVotacion", -1)
        txtIdVotacion.text = "Id: $idVotacion"

        val list = findViewById<ListView>(R.id.listDetails)
        val repository = Repository()
        val viewModelFactory = DetailsViewModelFactory(repository)
        var votacion: VerVotacion
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        viewModel.getVotacion(idVotacion)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {
                votacion = response.body()!!.data;
                val candidatos = votacion.candidatos
                val datos = mutableListOf<String>();
                for(i in candidatos.indices){
                    datos.add("${candidatos[i].nombre}: ${candidatos[i].votos} votos")
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)
                list.adapter = adapter
            } else {
                Log.d("Response", response.errorBody().toString())
            }
        })

        val btnAtras = findViewById<Button>(R.id.btnAtras)
        btnAtras.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}