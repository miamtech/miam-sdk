import com.example.kmm_miam_sdk.network.KtorClientFactory
import com.example.kmm_miam_sdk.network.service.RecipeServiceImpl

class NetworkModule {
    val recipeService = RecipeServiceImpl(
            httpClient=  KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL );
}