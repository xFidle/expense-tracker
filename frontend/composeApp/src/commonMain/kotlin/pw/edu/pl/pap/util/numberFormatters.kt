package pw.edu.pl.pap.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun formatForDisplay(number: Float?): String {

    if (number == null) {
        return "null"
    }

    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
        decimalSeparator = ','   // Use ',' for decimal separator
        groupingSeparator = ' ' // Use space for grouping separator
    }

    val pattern = "#,###.##"
    val decimalFormat = DecimalFormat(pattern, symbols)

    return decimalFormat.format(number)
}

fun formatForTextField(number: Float?): String {
    if (number == null) {
        return ""
    }
    val decimalFormat = DecimalFormat("0.##")

    return decimalFormat.format(number)
}