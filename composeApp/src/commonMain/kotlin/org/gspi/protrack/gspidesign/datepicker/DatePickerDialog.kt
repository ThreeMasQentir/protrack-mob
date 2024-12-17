package org.gspi.protrack.gspidesign.datepicker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

object DatePickerDialog {
    private val _isVisible = mutableStateOf(false)
    val isVisible: State<Boolean> get() = _isVisible

    private var onDateSelected: ((String) -> Unit)? = null

    private var year = 2024
    private var monthIndex = 9 // Index for October (0-based)
    private var day = 20

    private val monthNames = listOf(
        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    )

    fun show(onDateSelected: (String) -> Unit) {
        this.onDateSelected = onDateSelected
        _isVisible.value = true
    }

    private fun hide() {
        _isVisible.value = false
    }

    @Composable
    fun Content() {
        if (isVisible.value) {
            Dialog(onDismissRequest = { hide() }) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth() // Full width
                        .padding(horizontal = 16.dp), // 16dp horizontal margin
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Pilih Tanggal", style = MaterialTheme.typography.headlineSmall)

                        // Row for Day, Month, and Year pickers
                        Row(horizontalArrangement = Arrangement.Center) {
                            // Day Picker
                            NumberPicker(
                                range = 1..31,
                                value = day,
                                onValueChange = { day = it },
                                modifier = Modifier.weight(1f)
                            )

                            // Month Picker
                            MonthPicker(
                                monthNames = monthNames,
                                currentIndex = monthIndex,
                                onValueChange = { monthIndex = it },
                                modifier = Modifier.weight(1f)
                            )

                            // Year Picker
                            NumberPicker(
                                range = 2000..2050,
                                value = year,
                                onValueChange = { year = it },
                                modifier = Modifier.weight(1f)
                            )
                        }

                        // Action Buttons
                        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                            TextButton(onClick = { hide() }) {
                                Text("Cancel")
                            }
                            TextButton(onClick = {
                                val selectedDate = "$day ${monthNames[monthIndex]} $year"
                                onDateSelected?.invoke(selectedDate)
                                hide()
                            }) {
                                Text("OK")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NumberPicker(
    range: IntRange,
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentValue by remember { mutableStateOf(value) }
    Column(
        modifier = modifier,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        IconButton(onClick = { if (currentValue < range.last) onValueChange(++currentValue) }) {
            Text("▲")
        }
        Text(currentValue.toString(), style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = { if (currentValue > range.first) onValueChange(--currentValue) }) {
            Text("▼")
        }
    }
}

@Composable
fun MonthPicker(
    modifier: Modifier = Modifier,
    monthNames: List<String>,
    currentIndex: Int,
    onValueChange: (Int) -> Unit
) {
    var currentMonthIndex by remember { mutableStateOf(currentIndex) }

    Column(
        modifier = modifier,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        IconButton(onClick = {
            if (currentMonthIndex < monthNames.lastIndex) {
                currentMonthIndex++
                onValueChange(currentMonthIndex)
            }
        }) {
            Text("▲")
        }
        Text(monthNames[currentMonthIndex], style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = {
            if (currentMonthIndex > 0) {
                currentMonthIndex--
                onValueChange(currentMonthIndex)
            }
        }) {
            Text("▼")
        }
    }
}