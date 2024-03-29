package com.adrianiglesia.atqr.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.R.drawable.ic_arrow_back
import com.adrianiglesia.atqr.model.Assistance
import com.adrianiglesia.atqr.model.Course
import com.adrianiglesia.atqr.model.response.QrBody
import com.adrianiglesia.atqr.utils.PieChartCalculator
import com.adrianiglesia.atqr.view.adapters.AsistenciaAdapter
import com.adrianiglesia.atqr.viewmodel.AsistenciaViewModel
import kotlinx.android.synthetic.main.activity_asistencia.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue

class AsistenciaActivity : AppCompatActivity() {

    private lateinit var materiasViewModel: AsistenciaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)
        setToolbar()

        val studentId = intent.getLongExtra("STUDENT_ID", 0)
        val course = intent.getParcelableExtra<Course>("COURSE_ID")

        tv_materia_name.text = course.asignature.name

        materiasViewModel = ViewModelProviders.of(this).get(AsistenciaViewModel::class.java)

        val body = QrBody(studentId,course.id.toLong())
        materiasViewModel.getMyAsistance(body).observe(this, Observer<List<Assistance>> {
            setRecyclerView(it!!)
            setPieChart(it)

        })

        materiasViewModel.getMessage().observe(this, Observer<String>{
            showMessage(it!!)
        })

        materiasViewModel.isLoading.observe(this, Observer<Boolean>{
            setVisiblities(it!!)
        })


    }

    private fun setToolbar(){
        toolbar_asistencias.title = "Asistencias"
        toolbar_asistencias.setTitleTextColor(Color.WHITE)
        @Suppress("DEPRECATION")
        toolbar_asistencias.navigationIcon = resources.getDrawable(ic_arrow_back)
        toolbar_asistencias.setNavigationOnClickListener { onBackPressed() }

    }

    private fun setRecyclerView(assistances:List<Assistance>){
        if(!assistances.isNullOrEmpty()){
            reycler_asistencias.layoutManager = LinearLayoutManager(this)
            reycler_asistencias.hasFixedSize()
            reycler_asistencias.adapter = AsistenciaAdapter(assistances)
        }else{
            showMessage("No hay asistencias para esta materia")
        }
    }

    private fun setPieChart(assistances: List<Assistance>){
        val chartList:List<SliceValue> = PieChartCalculator.getPercentForAssistances(assistances)
        val pieChart = PieChartData(chartList)
        pieChart.setHasLabels(true)
        pieChart.setHasLabelsOnlyForSelected(true)
        pieChart.setHasCenterCircle(true)
        pieChart.centerText1 = "Asistencia"
        pieChart.centerText1FontSize = 16
        chart.pieChartData = pieChart

    }


    private fun setVisiblities(it:Boolean){
        if(it){
            loading_assistance.visibility = View.VISIBLE
            reycler_asistencias.visibility = View.GONE
        }else{
            loading_assistance.visibility = View.GONE
            reycler_asistencias.visibility = View.VISIBLE
        }
    }


    private fun showMessage(message:String){
       Snackbar.make(asistencias_layout, message, Snackbar.LENGTH_SHORT).show()
    }

}
