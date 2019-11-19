package com.adrianiglesia.atqr.utils

import android.graphics.Color
import com.adrianiglesia.atqr.model.Assistance
import lecho.lib.hellocharts.model.SliceValue

class PieChartCalculator {

    companion object{
        fun getPercentForAssistances(assistances:List<Assistance>):List<SliceValue>{

            val totAssis:Int = assistances.size
            var countPresent = 0F
            var countAbsent = 0F

            for(assist in assistances){
                if(assist.status.name == "PRESENT"){
                    countPresent++
                }else{
                    countAbsent++
                }
            }

            val presentPercent:Float = (countPresent/totAssis)*100
            val absentPercent:Float = (countAbsent/totAssis)*100

            return listOf(SliceValue(presentPercent,Color.GREEN).setLabel("Presente $presentPercent%"),
                          SliceValue(absentPercent,Color.RED).setLabel("Ausente $absentPercent%"))
        }
    }
}