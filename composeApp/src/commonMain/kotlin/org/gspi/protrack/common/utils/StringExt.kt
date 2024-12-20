package org.gspi.protrack.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime

fun String.toIndonesianDateFormat(): String {
    // Ensure the input string matches the expected format
    if (this.length != 10 || this[4] != '-' || this[7] != '-') {
        return this // Return the original string if format is incorrect
    }

    return try {
        // Extract year, month, and day as integers
        val year = this.substring(0, 4).toInt()
        val month = this.substring(5, 7).toInt()
        val day = this.substring(8, 10).toInt()

        // Array of month names in Indonesian
        val months = arrayOf(
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        )

        // Format the date in Indonesian style
        "$day ${months[month - 1]} $year"
    } catch (e: Exception) {
        this // Return the original string if parsing fails
    }
}
fun calculateTimeLeft(endDate: String): String {
    // Parse the input strings to LocalDate
    val start = LocalDate.parse(getCurrentDate())
    val end = LocalDate.parse(endDate)

    // Calculate the difference in days
    val daysBetween = start.daysUntil(end)

    // Determine the output message
    return when {
        daysBetween > 0 -> "$daysBetween days left"
        daysBetween < 0 -> "${-daysBetween} days overdue"
        else -> "Due today"
    }
}

fun isOverdue(endDate: String): Boolean {
    // Parse the input strings to LocalDate
    val start = LocalDate.parse(getCurrentDate())
    val end = LocalDate.parse(endDate)

    // Calculate the difference in days
    val daysBetween = start.daysUntil(end)

    // Determine if the end date is in the past
    return daysBetween < 0
}

fun getCurrentDate(): String {
    // Get the current moment in UTC
    val currentMoment: Instant = Clock.System.now()
    // Convert the current moment to a LocalDateTime in your time zone
    val currentDateTime: LocalDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
    // Extract the date part
    val currentDate: LocalDate = currentDateTime.date
    // Format the date as yyyy-MM-dd
    return "${currentDate.year}-${currentDate.monthNumber.toString().padStart(2, '0')}-${currentDate.dayOfMonth.toString().padStart(2, '0')}"
}
