package com.adrianiglesia.atqr.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE


import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.Course
import com.adrianiglesia.atqr.model.response.CourseResponse
import com.adrianiglesia.atqr.model.response.QrBody
import com.adrianiglesia.atqr.view.adapters.MateriasAdapter
import com.adrianiglesia.atqr.viewmodel.MateriasViewModel
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_materias.*

class MateriasActivity : AppCompatActivity(), MateriasAdapter.OnItemClickListener {

    companion object{

        const val LOGOUT:Int = 9
        const val FINISH:Int = 0
    }

    private lateinit var materiasViewModel: MateriasViewModel
    private lateinit var user:User

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)
        setToolbar()

        user = intent.getParcelableExtra("USER")
        tv_welcome_user.text = "Bienvenido ${user.firstName}"

        materiasViewModel = ViewModelProviders.of(this).get(MateriasViewModel::class.java)


        materiasViewModel.getAssignatures(user).observe(this, Observer<List<CourseResponse>>{
            setRecyclerView(it!!)
        })

        materiasViewModel.isLoading.observe(this,Observer<Boolean>{
            setVisiblities(it!!)
        })

        materiasViewModel.getMessage().observe(this, Observer<String>{
            if(it != null) showMessage(it)
        })

        btn_scan_qr.setOnClickListener {
            Log.d("SCAN", "Escaneo QR activado")
            startScanQr()
        }

    }


    private fun startScanQr() {
        val scanner = IntentIntegrator(this)
        scanner.setOrientationLocked(true)
        scanner.setPrompt("Escanea el QR que habilite el profesor")
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
        scanner.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents == null){
                showMessage("Cancelaste el scanneo")
            }else{
                if(result.contents.contains("-")){
                    val split = result.contents.split("-")
                    val courseId:Long = split[0].toLong()
                    val qrBody = QrBody(user.id.toLong(), courseId)
                    materiasViewModel.sendQr(qrBody)
                }else{
                    showMessage("Datos incorrectos")
                }
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data)
        }
    }

    private fun setVisiblities(it:Boolean){
        if(it){
            loading_materias.visibility = VISIBLE
            recycler_materias.visibility = GONE
        }else{
            loading_materias.visibility = GONE
            recycler_materias.visibility = VISIBLE
        }
    }


    private fun showMessage(message:String){
        Snackbar.make(materias_layout, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setRecyclerView(materias:List<CourseResponse>){
        recycler_materias.layoutManager = LinearLayoutManager(this)
        recycler_materias.hasFixedSize()
        recycler_materias.adapter = MateriasAdapter(materias,this)
    }

    private fun setToolbar(){
        toolbar_materia.title = "Materias"
        toolbar_materia.setTitleTextColor(Color.WHITE)
        toolbar_materia.inflateMenu(R.menu.logout)
        toolbar_materia.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener {
            return@OnMenuItemClickListener when (it.itemId){
                R.id.menu_logout -> {
                    showFinishDialog("Desea cerrar su sesion?","ATENCION", "Cerrar sesion", LOGOUT)
                    true
                }

                else -> false
            }
        })
    }


    override fun onItemClicked(course: Course) {
        val intent = Intent(this, AsistenciaActivity::class.java)
        intent.putExtra("STUDENT_ID", user.id.toLong())
        intent.putExtra("COURSE_ID", course)
        startActivity(intent)
    }


    override fun onBackPressed() {
        showFinishDialog("Estas saliendo de ATQR!","Salir", "Salir", FINISH)
    }

    private fun showFinishDialog(message:String, title:String, possitiveBt:String, result:Int) {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(possitiveBt) { dialog, which ->
                    setResult(result)
                    finish()
                }
                .setNegativeButton("Cancelar"){dialog,which ->
                    dialog.dismiss()
                }.show()
    }
}
