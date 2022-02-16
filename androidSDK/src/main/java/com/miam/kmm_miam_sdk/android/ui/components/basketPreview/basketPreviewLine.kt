package com.miam.kmm_miam_sdk.android.ui.components.basketPreview


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.common.*
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun basketPreviewLine(line: BasketPreviewLine) {

    val price = Price(price = line.price.toDouble(), isTotalPrice = true)

    Column(

    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            clickable(
                onClick = { /*TODO*/ },
                children = {  Image(
                    painter = rememberImagePainter(line.picture),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(0.dp, 20.dp, 0.dp, 0.dp)),
            )
                }
                    )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                clickable(

                    onClick = { /*TODO*/ },
                children = { Text(
                    text = line.title,
                    maxLines = 2,
                    color = MiamMasterView.Primary,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    ),
                )}
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(
                    text = "${line.description?.get(0) ?: ' '}",
                    color= MiamMasterView.Grey02,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = "${line.price}€ /personne",
                    color= MiamMasterView.Grey02,
                    style = MaterialTheme.typography.h5.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Counter(
                    count = line.count,
                    increase = { /*TODO*/ },
                    decrease = { /*TODO*/ },
                    counterModifier = CounterModifier(
                        iconModifier = Modifier.size(30.dp),
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    price.content()
                }
            }

        }
    }

}