package com.example.finalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.model.CrearVotacion
import com.example.finalapp.model.Votacion
import com.example.finalapp.repository.Repository
import com.example.finalapp.utils.Global

class Crear : AppCompatActivity() {
    private lateinit var viewModel: CrearViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear)

        val btnAddItem = findViewById<Button>(R.id.btnAddItem)
        val btnAddInvitado = findViewById<Button>(R.id.btnAddInvitado)
        val listItems = findViewById<ListView>(R.id.listItemsVotacion)
        val listInvitados = findViewById<ListView>(R.id.listInvitados)
        val txtNombre = findViewById<EditText>(R.id.edtNombre)
        val txtItem = findViewById<EditText>(R.id.edtNewItem)
        val txtCI = findViewById<EditText>(R.id.edtNewCI)

        var items: Array<String> = emptyArray()
        btnAddItem.setOnClickListener {
            val newItem = txtItem.text.toString()
            items += newItem
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
            listItems.adapter = adapter
            txtItem.setText("")
        }

        var invitados: Array<String> = emptyArray()
        var asignados: Array<Int> = emptyArray()
        btnAddInvitado.setOnClickListener {
            val newInvitado = txtCI.text.toString()
            invitados += newInvitado
            asignados += newInvitado.toInt()
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, invitados)
            listInvitados.adapter = adapter
            txtCI.setText("")
        }

        val btnCrear = findViewById<Button>(R.id.btnCrearVotacion)
        btnCrear.setOnClickListener {
            val repository = Repository()
            val viewModelFactory = CrearViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(CrearViewModel::class.java)
            val ci = Global.ci.toInt()
            val nombre = txtNombre.text.toString()
            val datos = CrearVotacion(ci, nombre, items, asignados)
            viewModel.postVotacion(datos)
            viewModel.myResponse.observe(this, Observer { response ->
                if(response.isSuccessful) {
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                } else {
                    Log.d("Response", response.errorBody().toString())
                }
            })
        }

        val btnAtras = findViewById<Button>(R.id.btnAtras)
        btnAtras.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}