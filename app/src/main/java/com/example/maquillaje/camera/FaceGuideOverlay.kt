package com.example.maquillaje.camera

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun FaceGuideOverlay(
    color: Color = Color.White.copy(alpha = 0.7f),
    strokeWidth: Float = 3f
) {
    val configuration = LocalConfiguration.current
    
    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        
        // Calculamos el tamaño del óvalo
        val ovalWidth = minOf(size.width, size.height) * 0.45f  // Ancho del óvalo
        val ovalHeight = ovalWidth * 1.5f  // Alto del óvalo (1.5 veces el ancho para forma de rostro)
        
        // Dibujamos el óvalo
        drawOval(
            color = color,
            topLeft = Offset(
                x = centerX - ovalWidth / 2,
                y = centerY - ovalHeight / 2
            ),
            size = Size(ovalWidth, ovalHeight),
            style = Stroke(width = strokeWidth)
        )
        
        // Marcas de referencia
        val markLength = ovalWidth * 0.1f
        
        // Marcas verticales (superior e inferior)
        drawLine(
            color = color,
            start = Offset(centerX, centerY - ovalHeight / 2),
            end = Offset(centerX, centerY - ovalHeight / 2 + markLength),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = color,
            start = Offset(centerX, centerY + ovalHeight / 2),
            end = Offset(centerX, centerY + ovalHeight / 2 - markLength),
            strokeWidth = strokeWidth
        )
        
        // Marcas horizontales (izquierda y derecha)
        drawLine(
            color = color,
            start = Offset(centerX - ovalWidth / 2, centerY),
            end = Offset(centerX - ovalWidth / 2 + markLength, centerY),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = color,
            start = Offset(centerX + ovalWidth / 2, centerY),
            end = Offset(centerX + ovalWidth / 2 - markLength, centerY),
            strokeWidth = strokeWidth
        )
        
        // Opcional: Línea central vertical para ayudar con la simetría
        drawLine(
            color = color.copy(alpha = 0.3f),  // Más transparente que el óvalo principal
            start = Offset(centerX, centerY - ovalHeight / 2 + markLength),
            end = Offset(centerX, centerY + ovalHeight / 2 - markLength),
            strokeWidth = strokeWidth / 2
        )
    }
} 