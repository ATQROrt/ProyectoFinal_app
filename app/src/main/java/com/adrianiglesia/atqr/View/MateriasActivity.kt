package com.adrianiglesia.atqr.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.adrianiglesia.atqr.Model.Materia

import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.View.Adapters.MateriasAdapter
import kotlinx.android.synthetic.main.activity_materias.*

class MateriasActivity : AppCompatActivity(), MateriasAdapter.OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        var mat1 = Materia("taller de programacion", "30")
        var mat2 = Materia("estudios judaicos", "50")
        var mat3 = Materia("calidad de software", "50")
        var mat4 = Materia("programacion 3", "70")
        var mat5 = Materia("proyecto final", "100")

        var materias = listOf(mat1,mat2,mat3,mat4,mat5)

        recycler_materias.layoutManager = LinearLayoutManager(this)
        recycler_materias.hasFixedSize()
        recycler_materias.adapter = MateriasAdapter(materias,this)


        btn_scan_qr.setOnClickListener {
            Toast.makeText(this,"  ESCANEAME ESTA PUTO DE MIERDA", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onItemClicked(materia: Materia) {
        Toast.makeText(this,materia.name, Toast.LENGTH_SHORT).show()
    }
}
