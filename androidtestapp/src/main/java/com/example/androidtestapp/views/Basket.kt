package com.example.androidtestapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidtestapp.services.MyBasketService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Basket: KoinComponent {

    private val basketService: MyBasketService by inject()

    @Composable
    fun Content() {
        val state = basketService.getBasketFlow().collectAsState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Panier du client", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Total Panier -> ${state.value.items.sumOf { it.price }} €")
            Text(text = "Nombre de recette -> ${state.value.recipeCount}")
            Divider()
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { basketService.pushProduct() }) {
                    Text(text = "Ajouter")
                }
                Button(onClick = { basketService.removeProduct() }) {
                    Text(text = "Retirer")
                }
                Button(onClick = { basketService.flushRecipes() }) {
                    Text(text = "flush recipes")
                }
            }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                state.value.items.forEach {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Box(
                            Modifier
                                .background(Color.Gray)
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                        ) {
                            Column {
                                Text(
                                    text = it.name,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = " X ${it.quantity} ", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }

}