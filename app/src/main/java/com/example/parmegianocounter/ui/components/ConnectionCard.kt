package com.example.parmegianocounter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parmegianocounter.R
import com.example.parmegianocounter.ui.theme.ParmegianoCounterTheme
import com.example.parmegianocounter.ui.vm.ConnectionState

@Composable
fun ConnectionCard(
    connectionState: ConnectionState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.onPrimaryContainer)
            .padding(10.dp)

    ) {
        Column(
            Modifier
                .weight(0.7F)
                .padding(start = 10.dp)) {
            Spacer(Modifier.weight(1F))
            Text(
                text = "You are currently",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Text(
                text = connectionState.toString(),
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer,
                fontWeight = FontWeight.W700
            )
            Spacer(Modifier.weight(1F))
        }

        Image(
            painter = painterResource(
                when (connectionState) {
                    ConnectionState.ONLINE -> R.drawable.ok_svgrepo_com
                    ConnectionState.OFFLINE -> R.drawable.error_close_svgrepo_com
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .weight(0.3F).padding(10.dp)
        )
    }
}

@Composable
@Preview
fun ConnectionCardPreview() {
    ParmegianoCounterTheme {
        Surface {
            Column {
                ConnectionCard(connectionState = ConnectionState.ONLINE, modifier = Modifier.weight(0.2F))
            }
        }
    }
}