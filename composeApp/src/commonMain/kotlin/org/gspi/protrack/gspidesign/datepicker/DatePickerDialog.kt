package org.gspi.protrack.gspidesign.datepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

object DatePickerDialog {
    private val _isVisible = mutableStateOf(false)
    val isVisible: State<Boolean> get() = _isVisible

    private var onDateSelected: ((String) -> Unit)? = null
    private var selectedDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

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

    private fun formatDate(date: LocalDate): String {
        val day = date.dayOfMonth
        val month = monthNames[date.monthNumber - 1] // Month is 1-based
        val year = date.year
        return "$day $month $year"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content() {
        if (isVisible.value) {
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = selectedDate.atStartOfDayIn(TimeZone.currentSystemDefault()).toEpochMilliseconds()
            )
            val primaryColor = Color(0xFFFFC130)
            val customDatePickerColors = DatePickerDefaults.colors(
                containerColor = Color.White,
                selectedDayContainerColor = primaryColor, // Change purple to this
                selectedDayContentColor = Color.White, // Text color inside selected day
                todayDateBorderColor = primaryColor, // Border for today's date
                selectedYearContainerColor = primaryColor, // Year highlight
                dayInSelectionRangeContainerColor = primaryColor.copy(alpha = 0.3f), // Range highlight
                dayInSelectionRangeContentColor = primaryColor
            )
            DatePickerDialog(
                colors = customDatePickerColors,
                onDismissRequest = { hide() },
                confirmButton = {
                    TextButton(onClick = {
                        val selectedMillis = datePickerState.selectedDateMillis
                        if (selectedMillis != null) {
                            val selectedLocalDate = Instant.fromEpochMilliseconds(selectedMillis)
                                .toLocalDateTime(TimeZone.currentSystemDefault()).date
                            val formattedDate = formatDate(selectedLocalDate)
                            onDateSelected?.invoke(formattedDate)
                        }
                        hide()
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { hide() }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}