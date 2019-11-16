package com.adrianiglesia.atqr.view.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.model.Assistance
import kotlinx.android.synthetic.main.template_asistecia.view.*


class AsistenciaAdapter(private val assistances:List<Assistance>): RecyclerView.Adapter<AsistenciaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.template_asistecia,parent,false)
        return ViewHolder(v)    }

    override fun getItemCount(): Int {
       return assistances.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(assistances[position])
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale", "SetTextI18n", "SimpleDateFormat")
        fun bindItems(assistance: Assistance) {
            val date = java.text.SimpleDateFormat("dd-MM-yyyy").format(assistance.date)

            itemView.tv_asistencia_date.text = date
            if(assistance.status.name == "PRESENT"){
                itemView.tv_asistencia_status.text = "PRESENTE"
            }else{
                itemView.tv_asistencia_status.text = "AUSENTE"
            }

        }
    }
}