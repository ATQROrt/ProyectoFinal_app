package com.adrianiglesia.atqr.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.R.drawable.ic_arrow_back
import com.adrianiglesia.atqr.model.Assistance
import com.adrianiglesia.atqr.model.response.QrBody
import com.adrianiglesia.atqr.view.adapters.AsistenciaAdapter
import com.adrianiglesia.atqr.viewmodel.AsistenciaViewModel
import kotlinx.android.synthetic.main.activity_asistencia.*

class AsistenciaActivity : AppCompatActivity() {

    private lateinit var materiasViewModel: AsistenciaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)
        setToolbar()

        val studentId = intent.getLongExtra("STUDENT_ID", 0)
        val courseId = intent.getLongExtra("COURSE_ID", 0)

        materiasViewModel = ViewModelProviders.of(this).get(AsistenciaViewModel::class.java)

        val body = QrBody(studentId,courseId)
        materiasViewModel.getMyAsistance(body).observe(this, Observer<List<Assistance>> {
            setRecyclerView(it!!)
        })

        materiasViewModel.showMessage().observe(this, Observer<String>{
            showMessage(it!!)
        })

    }


    private fun setToolbar(){
        toolbar_asistencias.title = "Asignature"
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
            Toast.makeText(this,"No Hay asistencias para esta materia",Toast.LENGTH_SHORT).show()
        }

    }


    private fun showMessage(message:String){
       Snackbar.make(asistencias_layout, message, Snackbar.LENGTH_SHORT).show()
    }

}
