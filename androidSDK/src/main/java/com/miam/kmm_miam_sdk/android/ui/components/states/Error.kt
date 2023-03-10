package com.miam.kmm_miam_sdk.android.ui.components.states

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Template

@Composable
fun Error(
    msg: String = "An error has ocurred",
    onTryAgain: () -> Unit = {}
) {
    Template.errorTemplate?.let {
        it()
    } ?: run {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = msg,
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton( onClick = onTryAgain) {
                    Text(
                        text = "Try Again",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}