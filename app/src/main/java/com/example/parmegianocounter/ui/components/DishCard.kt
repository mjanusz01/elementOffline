package com.example.parmegianocounter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parmegianocounter.ui.theme.ParmegianoCounterTheme

@Composable
fun DishCard(
    name: String,
    additional: String,
    price: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp),
    ) {
        Column {
            Text(
                text = name,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
                text = additional,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(Modifier.weight(1F))
        Text(
            text = "$price zł",
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@Composable
fun DishCardPreview() {
    ParmegianoCounterTheme {
        Surface {
            Column {
                DishCard(
                    name = "Filet z kurczaka parmegiano",
                    additional = "Gnocchi, sos pomidorowy, mix sałat",
                    "29"
                )
            }
        }
    }
}

@Preview
@Composable
fun DishCardPreviewOpen() {
    ParmegianoCounterTheme {
        Surface {
            Column {
                DishCard(
                    name = "Filet z kurczaka parmegiano",
                    additional = "Gnocchi, sos pomidorowy, mix sałat",
                    "29",
                )
            }
        }
    }
}