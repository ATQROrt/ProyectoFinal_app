package com.adrianiglesia.atqr.view.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.model.Materia
import com.adrianiglesia.atqr.model.response.MateriaResponse
import kotlinx.android.synthetic.main.materias_template.view.*

class MateriasAdapter(private val materias:List<MateriaResponse>, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<MateriasAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.materias_template,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return materias.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(materias[position].asignature, itemClickListener)

    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bindItems(materia: Materia, itemClickListener: OnItemClickListener) {
            itemView.materia_name.text = materia.name.toUpperCase()
            //itemView.asist_percent.text= "${materia.percent}%"

            itemView.setOnClickListener { itemClickListener.onItemClicked(materia) }
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(materia: Materia)
    }
}