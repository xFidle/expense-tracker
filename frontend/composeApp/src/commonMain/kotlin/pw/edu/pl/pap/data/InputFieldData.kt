package pw.edu.pl.pap.data

import kotlinx.serialization.Serializable

@Serializable
data class InputFieldData(
    val title: String,
    var parameter: String,
    val onChange: (String) -> Unit,
)