package com.just.track.utils.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(
    text: String,
    minimizedMaxLines: Int = 3
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isTextOverflowing by remember { mutableStateOf(false) }
    var readyToDraw by remember { mutableStateOf(false) }
   //var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    SideEffect {
        Log.d("Composable - ","isTextOverflowing - $isTextOverflowing")
    }
    Column {
        Text(
            text = text,
            maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Light
            ),
            color = Color.Black,
            onTextLayout = { layoutResult ->
               // textLayoutResult = layoutResult
                Log.d("Composable - ","line count - ${layoutResult.lineCount}")

                isTextOverflowing = layoutResult.lineCount >= minimizedMaxLines

                if (!readyToDraw) {
                    readyToDraw = true // only set once to avoid flickering
                }
            }
        )

        if (isTextOverflowing) {
            Text(
                text = if (isExpanded) "Show less" else "Read more",
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { isExpanded = !isExpanded },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}