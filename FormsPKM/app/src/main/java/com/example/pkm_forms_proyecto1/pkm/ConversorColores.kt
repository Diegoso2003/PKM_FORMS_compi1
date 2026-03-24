package com.example.pkm_forms_proyecto1.pkm

import androidx.compose.ui.graphics.Color
import com.example.pkm_forms_proyecto1.enums.TColor
import com.example.pkm_forms_proyecto1.excepciones.PkmException

class ConversorColores {

    fun tipoColor(tColor: TColor): ClaseColor{
        return ClaseColor(tColor.color)
    }
    fun hexColor(hex: String): ClaseColor{
        return ClaseColor(Color(("FF${hex}").toLong(16)))
    }

    fun rgbColor(r: Double, g: Double, b: Double): ClaseColor{
        val rInt = r.toInt()
        val gInt = g.toInt()
        val bInt = b.toInt()
        if(rInt !in 0..255 || gInt !in 0..255 || bInt !in 0..255){
            throw PkmException()
        }
        return ClaseColor(Color(rInt, gInt, bInt))
    }

    fun hslColor(h: Double, s:Double, l:Double): ClaseColor{
        val hInt = h.toInt()
        val sInt = s.toInt()
        val lInt = l.toInt()
        if(sInt !in 0..100 || lInt !in 0..100){
            throw PkmException()
        }
        val hNorm = hInt / 360f
        val sNorm = sInt / 100f
        val lNorm = lInt / 100f

        val c = (1 - kotlin.math.abs(2 * lNorm - 1)) * sNorm
        val x = c * (1 - kotlin.math.abs((hNorm * 6) % 2 - 1))
        val m = lNorm - c / 2

        val (r, g, b) = when {
            hNorm < 1/6f -> Triple(c, x, 0f)
            hNorm < 2/6f -> Triple(x, c, 0f)
            hNorm < 3/6f -> Triple(0f, c, x)
            hNorm < 4/6f -> Triple(0f, x, c)
            hNorm < 5/6f -> Triple(x, 0f, c)
            else -> Triple(c, 0f, x)
        }

        return ClaseColor(Color(
            red = r + m,
            green = g + m,
            blue = b + m
        ))
    }
}