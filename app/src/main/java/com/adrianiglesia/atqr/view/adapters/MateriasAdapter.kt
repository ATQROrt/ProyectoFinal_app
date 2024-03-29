package com.adrianiglesia.atqr.view.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.model.Course
import com.adrianiglesia.atqr.model.response.CourseResponse
import kotlinx.android.synthetic.main.materias_template.view.*

class MateriasAdapter(private val materias:List<CourseResponse>, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<MateriasAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.materias_template,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return materias.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(materias[position], itemClickListener)

    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bindItems(course: CourseResponse, itemClickListener: OnItemClickListener) {
            itemView.materia_name.text = course.course.asignature.name.toUpperCase()
            itemView.asist_percent.text= "${course.percentage}%"
            when {
                course.percentage >= 70 -> itemView.asist_percent.setTextColor(Color.parseColor("#168f06"))
                course.percentage in 50..69 -> itemView.asist_percent.setTextColor(Color.parseColor("#ff8f05"))
                else -> itemView.asist_percent.setTextColor(Color.parseColor("#cb0909"))
            }

            /*when(course.percentage){
                in 70..100 ->
                in 50..69 ->
                in 30..0 ->
            }*/
            itemView.setOnClickListener { itemClickListener.onItemClicked(course.course) }
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(course: Course)
    }
}