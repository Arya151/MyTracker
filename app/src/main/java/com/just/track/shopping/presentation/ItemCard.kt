package com.just.track.shopping.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.just.track.shopping.domain.model.ShoppingItems
import com.just.track.utils.presentation.ExpandableText

@Composable
fun ItemCard(
    data: ShoppingItems,
    onCheckBoxClicked: () -> Unit,
) {
    var isChecked by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onCheckBoxClicked.invoke()
            },
        shape = RoundedCornerShape(4.dp),
        tonalElevation = 4.dp,
        color = Color(data.cardColor)
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.name,
                    maxLines = 2,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = !isChecked
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor =  Color(0xFF1F800E),
                        uncheckedColor = Color.Black,
                        checkmarkColor = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = when (data.priority) {
                    0 -> "\uD83D\uDD35 Very Low"
                    1 -> "🟢 Low"
                    2 -> "🟡 Moderate"
                    3 -> "🟠 Important"
                    4 -> "🔴 High"
                    5 -> "🚨 Urgent"
                    else -> ""
                },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Light
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExpandableText(
                text = data.description,
                minimizedMaxLines = 3
            )
        }


    }


}

@PreviewLightDark
@Composable
private fun PreviewLightDark() {
    ItemCard(
        data = ShoppingItems(
            name = "Practice DSA",
            description = "Topic: graphs fdfjdfjdnf\njajfejfajfjdfjdhfkheiuhhfsjdfj jfhdhfjdhfjahejhjefjdbfdjbfajbfjadbflajkfefhauefeabfjfhjhahfuehfujdshfjdshfjhjakhkjfhkangfkjjbfjadbflajkfefhauefeabfjfhjhahfuehfujdshfjdshfjhjakhkjfhkangfkjjbfjadbflajkfefhauefeabfjfhjhahfuehfujdshfjdshfjhjakhkjfhkangfkjjbfjadbflajkfefhauefeabfjfhjhahfuehfujdshfjdshfjhjakhkjfhkangfkj",
            quantity = 0,
            priority = 4,
            cardColor = Color(0xFFB0BEC5).toArgb(),
            isShopped = false,
            createdAt = 1753625495
        ),
        onCheckBoxClicked = {}
    )
}