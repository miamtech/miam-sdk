package com.miam.kmm_miam_sdk.android.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
import org.koin.java.KoinJavaComponent

@coil.annotation.ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class RecipeDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MiamMasterView(context, attrs, defStyleAttr) {

    private var vmRecipe: RecipeViewModel = RecipeViewModel()
    private val idRecipeState: MutableState<Int?> = mutableStateOf(null)

    init {
        vmRecipe.setEvent(
            RecipeContract.Event.OnGetRecipe(
                idRecipe = 1
            )
        )
    }

    var idRecipe: Int
        get() = idRecipeState.value ?: 0
        set(value) {
            idRecipeState.value = value
            if (value != null) {
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
            var isIngredientChecked by remember { mutableStateOf(MiamMasterView.MiamDisplayMode.INGREDIENT_MODE) }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomActionButton(
                    icon = R.drawable.ic_ingredient,
                    text = "Ingredients",
                    action = {
                        isIngredientChecked = MiamMasterView.MiamDisplayMode.INGREDIENT_MODE
                    },
                    isActive = MiamMasterView.MiamDisplayMode.INGREDIENT_MODE == isIngredientChecked
                )
                CustomActionButton(
                    icon = R.drawable.ic_preparation,
                    text = "Préparation",
                    action = { isIngredientChecked = MiamMasterView.MiamDisplayMode.STEPS_MODE },
                    isActive = MiamMasterView.MiamDisplayMode.STEPS_MODE == isIngredientChecked
                )
            }

            Row() {
                RecipeContent(recipe = recipe, isIngredientChecked = isIngredientChecked, vmRecipeCard)
            }


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Price(recipeId = recipe.id).content()
                CustomActionButton(
                    action = { /*TODO*/ },
                    icon = R.drawable.ic_cart,
                    text = "Sélectionner ce repas",
                    isActive = true
                )
            }

        }
    }
}

@Composable
fun RecipeContent(recipe: Recipe, displayMode: MiamMasterView.MiamDisplayMode) {
    when (displayMode) {
        MiamMasterView.MiamDisplayMode.INGREDIENT_MODE -> IngredientsList(recipe.attributes.ingredients!!.ingredients)
        MiamMasterView.MiamDisplayMode.STEPS_MODE -> RecipeSteps(recipe.attributes.steps!!.steps,vmRecipe)
    }
}

@Composable
fun IngredientsList(ingredients: List<Ingredient>) {
    Column() {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Counter(count = 4, increase = {}, decrease = {}, counterModifier = CounterModifier())
            Text(
                text = "Quantité",
                color = MiamMasterView.darkGray
            )
        }
        ingredients.forEach {
            IngredientsLine(
                it.attributes.name!!.capitalize(),
                it.attributes.quantity!! + " " + it.attributes.unit!!
            )
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
fun CustomActionButton(action: () -> Unit, icon: Int, text: String, isActive: Boolean) {
    var colorFont = MiamMasterView.greenColor
    var backgroundColor = Color.White

    if (isActive) {
        colorFont = Color.White
        backgroundColor = MiamMasterView.greenColor
    }
    ExtendedFloatingActionButton(
        text = {
            Row() {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(colorFont),
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)

                )
                Text(
                    text = text, color = colorFont
                )
            }
        },
        backgroundColor = backgroundColor,
        onClick = action
    )
}
