package org.gspi.protrack.common.utils

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