package pw.edu.pl.pap.ui.groupScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pw.edu.pl.pap.data.uiSetup.inputFields.InputFieldData
import pw.edu.pl.pap.data.uiSetup.inputFields.InvitationData
import pw.edu.pl.pap.ui.common.*
import pw.edu.pl.pap.util.constants.horizontalPadding
import pw.edu.pl.pap.util.constants.verticalPadding

@Composable
fun InvitationFields(
    invitationsData: List<InvitationData>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn {
            items(invitationsData) { data ->
                createField(data)
            }
        }
    }
}

@Composable
private fun createField(data: InvitationData) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .height(50.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (data) {
                is InvitationData.NewInvitationData ->  createNewInvitation(data)
                is InvitationData.ReceivedInvitationData -> createReceivedInvitation(data)
                is InvitationData.SentInvitationData -> createSentInvitation(data)
            }
        }
    }
}

@Composable
private fun createNewInvitation(data: InvitationData.NewInvitationData){
    //TODO
}

@Composable
private fun createReceivedInvitation(data: InvitationData.ReceivedInvitationData){

}

@Composable
private fun createSentInvitation(data: InvitationData.SentInvitationData){

}