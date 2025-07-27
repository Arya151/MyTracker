package com.just.track.dialog.addItem.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemDialog(
    viewModel: AddItemDialogViewModel = viewModel(),
    onDismiss: () -> Boolean,
    onDone: () -> Boolean
) {
    val state = viewModel.state.collectAsState().value

    var expanded by remember { mutableStateOf(false) }
    val quantityOption = (0..10).toList()
    var selectedOption by remember { mutableIntStateOf(quantityOption[0]) }
    val cardColorOption = listOf(
        Color(0xFFB0BEC5), // Light Blue Gray
        Color(0xFFCE93D8), // Muted Purple
        Color(0xFFFFCC80), // Soft Orange
        Color(0xFFA5D6A7), // Pale Green
        Color(0xFF90CAF9)  // Light Blue
    )
    var selectedCardColor by remember { mutableStateOf(cardColorOption[0]) }

    Surface(
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Add Item",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "Name",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Light
                )
            )
            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                value = state.name,
                onValueChange = {
                    if (it.length <= 50) {
                        viewModel.onEvent(AddItemUIContract.Event.onNameChanged(it))
                    }
                },
                isError = state.isNameError,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                supportingText = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "${state.name.length}/50",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "Description",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Light
                )
            )
            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                value = state.description,
                onValueChange = {
                    if (it.length <= 250) {
                        viewModel.onEvent(AddItemUIContract.Event.onDescriptionChanged(it))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5,
                supportingText = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "${state.description.length}/250",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {

                Column {

                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Quantity",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Light
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    OutlinedTextField(
                        value = selectedOption.toString(),
                        onValueChange = {
                        },
                        readOnly = true,
                        trailingIcon = { TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                }

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    quantityOption.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item.toString()) },
                            onClick = {
                                selectedOption = item
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "Priority",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Light
                    )
                )

                Slider(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    value = state.priority.toFloat(),
                    onValueChange = {
                        viewModel.onEvent(AddItemUIContract.Event.onPriorityChanged(it.toInt()))
                    },
                    valueRange = 0f..5f,
                    steps = 4,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTickColor = MaterialTheme.colorScheme.outline
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 12.sp,
                    text = when (state.priority) {
                        0 -> "Very Low 🔵"
                        1 -> "Low 🟢"
                        2 -> "Moderate 🟡"
                        3 -> "Important 🟠"
                        4 -> "High 🔴"
                        5 -> "Urgent 🚨"
                        else -> ""
                    },
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Light
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "Card Color",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Light
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                cardColorOption.forEach { color ->
                    val isSelected = color == selectedCardColor
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 28.dp else 24.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = if (isSelected) 3.dp else 0.dp,
                                color = if (isSelected) Color(0xFFA7CBE8) else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                viewModel.onEvent(
                                    AddItemUIContract.Event.onCardColorChanged(
                                        color.toArgb()
                                    )
                                )
                                selectedCardColor = color
                            }
                    )

                }
            }

            Spacer(modifier = Modifier.height(62.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp,end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {

                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {
                        viewModel.onEvent(AddItemUIContract.Event.onDismissButtonClicked)
                        onDismiss.invoke()
                    }
                )

                Spacer(modifier = Modifier.width(24.dp))

                Text(
                    text = "OK",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        if(state.name.length in 1..25){
                            viewModel.onEvent(AddItemUIContract.Event.onDoneButtonClicked)
                            onDone.invoke()
                        }else{
                            viewModel.onEvent(AddItemUIContract.Event.onNameError(true))
                        }
                    }
                )
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun AddItemDialogPreview() {
    MaterialTheme {
        AddItemDialog(
            onDismiss = { false },
            onDone = {
                false
            }
        )
    }
}
