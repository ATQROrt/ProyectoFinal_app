package com.adrianiglesia.atqr.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.adrianiglesia.atqr.model.Materia

import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.response.MateriaResponse
import com.adrianiglesia.atqr.view.adapters.MateriasAdapter
import com.adrianiglesia.atqr.viewmodel.MateriasViewModel
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_materias.*

class MateriasActivity : AppCompatActivity(), MateriasAdapter.OnItemClickListener {

    private lateinit var materiasViewModel: MateriasViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        materiasViewModel = ViewModelProviders.of(this).get(MateriasViewModel::class.java)

        val user = intent.getParcelableExtra<User>("USER")
        tv_welcome_user.text = "Bienvenido ${user.firstName}"


        materiasViewModel.getAssignatures(user).observe(this, Observer<List<MateriaResponse>>{
            setRecyclerView(it!!)
        })

        materiasViewModel.isLoading.observe(this,Observer<Boolean>{
            setVisiblities(it!!)
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
                Toast.makeText(this,"Cancelaste el scanneo",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,result.contents.toString(),Toast.LENGTH_SHORT).show()
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

    private fun setRecyclerView(materias:List<MateriaResponse>){
        recycler_materias.layoutManager = LinearLayoutManager(this)
        recycler_materias.hasFixedSize()
        recycler_materias.adapter = MateriasAdapter(materias,this)
    }

    override fun onItemClicked(materia: Materia) {
        val intent = Intent(this, AsistenciaActivity::class.java)
        startActivity(intent)
    }
}
