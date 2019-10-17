package com.adrianiglesia.atqr.view

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.R.drawable.ic_arrow_back
import com.adrianiglesia.atqr.model.Asistencia
import com.adrianiglesia.atqr.view.adapters.AsistenciaAdapter
import com.adrianiglesia.atqr.view.adapters.MateriasAdapter
import kotlinx.android.synthetic.main.activity_asistencia.*
import kotlinx.android.synthetic.main.activity_materias.*
import java.util.*

class AsistenciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)
        setToolbar()

        var asist1:Asistencia = Asistencia("02/09","PRESENTE")
        var asist2:Asistencia = Asistencia("09/09","PRESENTE")
        var asist3:Asistencia = Asistencia("16/09","AUSENTE")
        var asist4:Asistencia = Asistencia("23/09","PRESENTE")
        var asist5:Asistencia = Asistencia("30/09","AUSENTE")
        var asist6:Asistencia = Asistencia("6/10","PUTITO")

        var asistnecias = listOf(asist1,asist2,asist3,asist4,asist5,asist6)

        setToolbar()
        setRecyclerView(asistnecias)

    }


    private fun setToolbar(){
        toolbar_asistencias.title = "Asistencia"
        toolbar_asistencias.setTitleTextColor(Color.WHITE)
        toolbar_asistencias.navigationIcon = resources.getDrawable(ic_arrow_back)
        toolbar_asistencias.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setRecyclerView(asistencias:List<Asistencia>){
        if(!asistencias.isNullOrEmpty()){
            reycler_asistencias.layoutManager = LinearLayoutManager(this)
            reycler_asistencias.hasFixedSize()
            reycler_asistencias.adapter = AsistenciaAdapter(asistencias)
        }else{
            Toast.makeText(this,"No Hay asistencias para esta materia, te quedaste re libre capo",Toast.LENGTH_SHORT).show()
        }

    }

}
