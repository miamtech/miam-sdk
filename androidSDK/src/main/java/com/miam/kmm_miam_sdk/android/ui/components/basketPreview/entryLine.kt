package com.miam.kmm_miam_sdk.android.ui.components.basketPreview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.common.*
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry

@Composable
fun entriesLine(entry: BasketEntry) {
    val price = Price(price = 14.00, isTotalPrice = true)

    Spacer(modifier = Modifier.padding(vertical = 4.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            modifier = Modifier
                .size(30.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(0xffD9D9D9)
                    ),
                    shape = RoundedCornerShape(100.dp)
                ),
            onClick = {
                //TODO
            }) {
            Icon(
                tint = Color.Gray,
                imageVector = Icons.Default.Delete,
                contentDescription = "Drop-Down Arrow"
            )
        }
        Image(
            painter = rememberImagePainter("https://www.presse-citron.net/app/uploads/2019/01/oeuf-instagram.jpg"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(72.dp)
                .width(72.dp)
                .clip(RoundedCornerShape(8.dp)),
        )

        Column() {
            Text(
                text = "Oeuf",
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                text = entry.id.toString(),
                color= MiamMasterView.Grey02,
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 14.sp,
                ),
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            clickable(
                onClick = { /*TODO*/ },
                children = {
                    Text(
                        text ="Changer d'article",
                        color = Color(0xff037E92),
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h5.copy(
                            fontSize = 14.sp,
                            textDecoration = TextDecoration.Underline
                        ),

                        )
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Surface(
                border = BorderStroke(1.dp, Color(0xff676767)),
                contentColor = Color(0xff676767),
                shape = CircleShape
            ) {
                Text(modifier = Modifier.padding(8.dp),text = "pour 14 recettes")
            }

            Row (verticalAlignment = Alignment.CenterVertically) {
                price.content()
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Counter(
                    count = 1,
                    increase = { /*TODO*/ },
                    decrease = { /*TODO*/ },
                    counterModifier = CounterModifier(
                        iconModifier = Modifier.width(0.dp))
                )
            }
        }
    }
}