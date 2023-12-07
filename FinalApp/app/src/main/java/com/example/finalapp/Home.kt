package com.example.finalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.model.Votacion
import com.example.finalapp.repository.Repository
import com.example.finalapp.utils.Global

class Home : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val txtCi = findViewById<TextView>(R.id.txtMiCI)
        txtCi.text = "Mi CI: ${Global.ci}"

        val btnCrear = findViewById<Button>(R.id.btnCrear)
        btnCrear.setOnClickListener {
            val intent = Intent(this, Crear::class.java)
            startActivity(intent)
        }

        val btnVotar = findViewById<Button>(R.id.btnVotar)
        btnVotar.setOnClickListener {
            val intent = Intent(this, MisVotaciones::class.java)
            startActivity(intent)
        }

        val btnAtras = findViewById<Button>(R.id.btnAtras)
        btnAtras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val listVotaciones = findViewById<ListView>(R.id.listVotaciones)
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        var votaciones: List<Votacion> = mutableListOf()
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.getVotaciones(Global.ci)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {
                votaciones = response.body()!!.data;
                val datos = mutableListOf<String>();
                for(i in votaciones.indices){
                    datos.add(votaciones[i].nombre)
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)
                listVotaciones.adapter = adapter
            } else {
                Log.d("Response", response.errorBody().toString())
            }
        })

        listVotaciones.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val idVotacion = votaciones[position].id
            val intent = Intent(this, Details::class.java)
            intent.putExtra("idVotacion", idVotacion)
            startActivity(intent)
        }
    }
}