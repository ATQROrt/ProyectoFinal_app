package com.adrianiglesia.atqr.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.adrianiglesia.atqr.model.Materia

import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.view.adapters.MateriasAdapter
import com.google.zxing.integration.android.IntentIntegrator
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
            Log.d("SCAN", "Escaneo QR activado")
            startScanQr()
        }

    }

    private fun startScanQr() {
        var scanner = IntentIntegrator(this)
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

    override fun onItemClicked(materia: Materia) {
        var intent = Intent(this, AsistenciaActivity::class.java)
        startActivity(intent)
    }
}
