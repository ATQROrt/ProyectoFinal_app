package com.adrianiglesia.atqr.view.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.model.Asistencia
import kotlinx.android.synthetic.main.template_asistecia.view.*


class AsistenciaAdapter(private val asistencias:List<Asistencia>): RecyclerView.Adapter<AsistenciaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.template_asistecia,parent,false)
        return ViewHolder(v)    }

    override fun getItemCount(): Int {
       return asistencias.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(asistencias[position])
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bindItems(asistencia: Asistencia) {
            itemView.tv_asistencia_date.text = asistencia.fecha.toString()
            itemView.tv_asistencia_status.text = asistencia.status
        }
    }
}