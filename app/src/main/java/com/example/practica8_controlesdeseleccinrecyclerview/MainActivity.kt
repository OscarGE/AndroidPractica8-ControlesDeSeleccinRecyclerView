package com.example.practica8_controlesdeseleccinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recView: RecyclerView

    private lateinit var btnInsertar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnMover: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInsertar = findViewById(R.id.btnInsertar)
        btnEliminar = findViewById(R.id.btnEliminar)
        btnMover = findViewById(R.id.btnMover)



        recView = findViewById(R.id.recView)
        val datos=
            MutableList(50) {i->Titular("Titulo $i", "Subtítulo Item $i")}
        //val adaptador = AdaptadorTitulares(datos)
        val adaptador = AdaptadorTitulares(datos){
            Toast.makeText(this,"Pulsado el elemento: ${it.titulo}",
            Toast.LENGTH_SHORT).show()
        }

        btnInsertar.setOnClickListener{
            datos.add(1, Titular("Nuevo Titular", "Subtitulo Nuevo Titular"))
            adaptador.notifyItemInserted(1)
        }
        btnEliminar.setOnClickListener{
            datos.removeAt(1)
            adaptador.notifyItemRemoved(1)
        }
        btnMover.setOnClickListener{
            val titularAux = datos[1]
            datos[1] = datos[2].also { datos[2] = datos[1] }
            adaptador.notifyItemMoved(1,2)
        }

        recView.setHasFixedSize(true)
//        recView.layoutManager =
//            LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        recView.layoutManager = GridLayoutManager(this, 3)

        recView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL))
        recView.adapter = adaptador
    }
}