package com.miam.kmm_miam_sdk.android.ui.components

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.R
import com.miam.kmm_miam_sdk.component.bottomSheet.BottomSheetViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Ingredient
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

@coil.annotation.ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class RecipeDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MiamMasterView(context, attrs, defStyleAttr) {

    private var vmRecipe : RecipeViewModel = RecipeViewModel()
    private val idRecipeState: MutableState<Int?> = mutableStateOf(null)

    var idRecipe: Int
        get() = idRecipeState.value ?: 0
        set(value) {
            idRecipeState.value = value
            if (value != null ) {
                vmRecipe.setEvent(
                    RecipeContract.Event.OnGetRecipe(
                        value
                    )
                )
            }
        }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {

        val vmBottomSheet: BottomSheetViewModel =
            KoinJavaComponent.get(BottomSheetViewModel::class.java)
        val state by vmRecipe.uiState.collectAsState()

        Box() {
            ManagementResourceState(
                resourceState = state.recipeState,
                successView = { recipe ->
                    requireNotNull(recipe)
                    recipeDetailCard(recipe, vmRecipe, vmBottomSheet)
                },
                onTryAgain = { vmRecipe.setEvent(RecipeContract.Event.Retry) },
                onCheckAgain = { vmRecipe.setEvent(RecipeContract.Event.Retry) },
            )
            //BottomSheet(bottomSheetState)

            /* BackHandler(enabled = bottomSheetState.isVisible) {
                 scope.launch {  bottomSheetState.hide() }
             }*/
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
private fun recipeDetailCard(
    recipe: Recipe,
    vmRecipeCard: RecipeViewModel,
    toggleBottomSheet: BottomSheetViewModel
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(recipe.attributes.mediaUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )

                FloatingActionButton(modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(24.dp)
                    .align(alignment = TopEnd),
                    backgroundColor = Color.Gray,
                    onClick = { })
                {
                    Text(text = "x", color = Color.White)
                }
            }

            // Temps de préparation
            Row(
                Modifier
                    .padding(end = 16.dp)
                    .align(CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_clock),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MiamMasterView.greenColor),
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = stringResource(id = R.string.miam_prep_time) + recipe.totalTime,
                    color = MiamMasterView.greenColor,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .align(CenterVertically)
                )
            }

            // Titre
            Row() {
                Text(
                    text = recipe.attributes.title,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.h5.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(horizontal = 30.dp)
                )
            }
            // Difficulte
            Row(
                Modifier
                    .padding(top = 4.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_diflow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(horizontal = 4.dp)

                )
                Text(
                    text = recipe.difficultyLabel,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Description
            Row() {
                Text(
                    text = recipe.attributes.description!!,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Switcher ingredients preparation

            // TODO: utiliser une enum dans le theme avec un state
            var isIngredientChecked by remember { mutableStateOf(true) }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomActionButton(
                    icon = R.drawable.ic_ingredient,
                    text = "Ingredients",
                    action = { isIngredientChecked = true }
                )
                CustomActionButton(
                    icon = R.drawable.ic_preparation,
                    text = "Préparation",
                    action = { isIngredientChecked = false }
                )
            }

            Row() {
                RecipeContent(recipe = recipe, isIngredientChecked = isIngredientChecked)
            }


            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(32.dp)
            ) {

                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 8.dp,
                                topStart = 8.dp,
                                topEnd = 8.dp,
                                bottomEnd = 8.dp
                            )
                        )
                        .background(MiamMasterView.greenColor)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ingredients", color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(32.dp)
                        .width(1.dp)
                )
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 8.dp,
                                topStart = 8.dp,
                                topEnd = 8.dp,
                                bottomEnd = 8.dp
                            )
                        )
                        .background(MiamMasterView.greenColor)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Recette", color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
            }

            Row(
                Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 32.dp,
                        bottom = 16.dp
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(32.dp)
                        .width(1.dp)
                )

            }
            Row() {
                Column() {
                    Row() {
                        Text("4,", color = Color(0xff037E92), fontSize = 24.sp)
                        Text("99€", color = Color(0xff037E92), fontSize = 16.sp)
                    }
                    Text("par pers.", color = Color.Gray, fontSize = 16.sp)
                }
            }

        }
//            Box(modifier = Modifier
//                .absoluteOffset(x= 0.dp, y = 178.dp)){
//
//                Box(modifier = Modifier
//                    .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
//                    .background(MiamMasterView.greenColor)){
//                    Row(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center){
//                        Image(
//                            painter = painterResource(R.drawable.ic_cookhat),
//                            contentDescription = null,
//                            modifier = Modifier.size(20.dp)
//                        )
//                        Text(text = "Recette", color = Color.White,
//                            modifier = Modifier.padding(horizontal = 5.dp))
//                    }
//                }
//            }

//        Box(
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(end = 8.dp, bottom = 8.dp)
//        ) {
//            FloatingActionButton(modifier = Modifier.size(36.dp),
//                backgroundColor = Color(0xff037E92),
//                onClick = { /*TODO*/ }) {
//                Image(
//                    painter = painterResource(R.drawable.ic_cart),
//                    contentDescription = null,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//        }


    }

}

@Composable
fun RecipeContent(recipe: Recipe, isIngredientChecked: Boolean) {
    if (isIngredientChecked) {
        IngredientsList(recipe.attributes.ingredients!!.ingredients)
    } else
        RecipeSteps()
}

@Composable
fun IngredientsList(ingredients: List<Ingredient>) {
    Column() {
        Row(
            Modifier.padding(
                horizontal = 8.dp,
                vertical = 8.dp
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
           NumberOfEaterSelector()
            Text(
                text = "Quantité",
                color = MiamMasterView.grayColor
            )
        }
        ingredients.forEach {
            IngredientsLine(it.attributes.name!!.capitalize(), it.attributes.quantity!! + " " + it.attributes.unit!!)
        }
    }
}

@Composable
fun IngredientsLine(ingredient: String, quantity: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp)
    ) {
        Text(
            text = ingredient,
            fontWeight = FontWeight.Bold
        )
        // TODO: Faire en sorte que ce soit ingredient qui retourne la concatenation avec gestion des float qty
        Text(
            text = quantity,
            color = MiamMasterView.grayColor
        )
    }
}

@Composable
fun NumberOfEaterSelector() {
    Row(
        Modifier.padding(
            horizontal = 8.dp,
            vertical = 8.dp
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_peoples),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(R.drawable.ic_less),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
            Row(
                modifier = Modifier
                    .height(32.dp)
                    .width(48.dp)
                    .border(
                        border = BorderStroke(width = 1.dp, color = Color.Gray),
                        shape = RoundedCornerShape(4.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = "4",
                    onValueChange = {/*TODO*/ },
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun RecipeSteps() {
    Text(text = "Liste des steps")
}

// TODO: Ajouter la notion de selected (changer le background color et la couleur de la police)
@Composable
fun CustomActionButton(action: () -> Unit, icon: Int, text: String) {
    ExtendedFloatingActionButton(
        text = {
            Row() {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)

                )
                Text(
                    text = text, color = Color.White,
                )
            }
        },
        backgroundColor = MiamMasterView.greenColor,
        onClick = action
    )
}
