package com.adrianiglesia.atqr.view

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.R.drawable.ic_arrow_back
import com.adrianiglesia.atqr.model.Assistance
import com.adrianiglesia.atqr.view.adapters.AsistenciaAdapter
import kotlinx.android.synthetic.main.activity_asistencia.*

class AsistenciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)
        setToolbar()

        var asist1:Assistance = Assistance("02/09","PRESENTE")
        var asist2:Assistance = Assistance("09/09","PRESENTE")
        var asist3:Assistance = Assistance("16/09","AUSENTE")
        var asist4:Assistance = Assistance("23/09","PRESENTE")
        var asist5:Assistance = Assistance("30/09","AUSENTE")
        var asist6:Assistance = Assistance("6/10","PUTITO")

        var asistnecias = listOf(asist1,asist2,asist3,asist4,asist5,asist6)


        setRecyclerView(asistnecias)

    }


    private fun setToolbar(){
        toolbar_asistencias.title = "Materia"
        toolbar_asistencias.setTitleTextColor(Color.WHITE)
        toolbar_asistencias.navigationIcon = resources.getDrawable(ic_arrow_back)
        toolbar_asistencias.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setRecyclerView(assistances:List<Assistance>){
        if(!assistances.isNullOrEmpty()){
            reycler_asistencias.layoutManager = LinearLayoutManager(this)
            reycler_asistencias.hasFixedSize()
            reycler_asistencias.adapter = AsistenciaAdapter(assistances)
        }else{
            Toast.makeText(this,"No Hay assistances para esta materia, te quedaste re libre capo",Toast.LENGTH_SHORT).show()
        }

    }

}
