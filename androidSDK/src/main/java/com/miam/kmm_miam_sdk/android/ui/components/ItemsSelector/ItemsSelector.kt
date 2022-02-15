package com.miam.kmm_miam_sdk.android.ui.components.ItemsSelector

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHorizontalCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.Price
import com.miam.kmm_miam_sdk.android.ui.components.common.clickable
/**
 * TODO AddReal Item + controller
 */
@ExperimentalFoundationApi
@Composable
fun ItemsSelector() {
    Column( modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 32.dp),
    verticalArrangement =  Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            border = BorderStroke(1.dp, Color(0xff007E92)),
            shape = RoundedCornerShape(25),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)

        ) {
            Row( modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp ),
                 horizontalArrangement = Arrangement.SpaceEvenly,
                 verticalAlignment = Alignment.CenterVertically
                ) {
                Image(
                    painter = rememberImagePainter("https://www.presse-citron.net/app/uploads/2019/01/oeuf-instagram.jpg"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(72.dp)
                        .width(72.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "DUCROS Curry tradition en poudre DUCROS, 53g | 0.053 Kg",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xff4B555D))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Price(price=1.0, isTotalPrice = true).content()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        
        Text(text = "Remplacer cet article par : ",
             Modifier.fillMaxWidth() ,
             textAlign = TextAlign.Start,
             fontSize = 16.sp,
             color = Color(0xff4B555D),
             fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        LazyVerticalGrid (
             cells = GridCells.Adaptive(140.dp),
             contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 0.dp
            ),
            content = {

                val itemsList = (1..10).map { it.toString() }
                items(itemsList.size) {
                    index ->
                    clickable(
                        onClick = { /* TODO*/ },
                        children = {
                            Surface(
                                border = BorderStroke(1.dp, Color(0xffecf1f4)),
                                shape = RoundedCornerShape(15),
                                modifier= Modifier.padding(8.dp)
                            ) {
                                Column( verticalArrangement =  Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier =  Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        tint = Color(0xffE22019),
                                        imageVector = Icons.Default.SwapHorizontalCircle,
                                        contentDescription = " swap_horizontal_circle"
                                    )

                                    Image(
                                        painter = rememberImagePainter("https://www.presse-citron.net/app/uploads/2019/01/oeuf-instagram.jpg"),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .height(72.dp)
                                            .width(72.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                    )
                                    Text(text = "DUCROS Curry tradition en poudre DUCROS, 53g | 0.053 Kg",
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.Center,
                                        color = Color(0xff4B555D))
                                    Price(price = 4.0,isTotalPrice = true).content()
                                }
                            }
                        }
                    )
                }
            }
        )

    }
}
  

