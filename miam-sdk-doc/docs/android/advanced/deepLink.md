# Link


## DeepLink
Miam provide a bind function on catalog that show desired categories.

```kotlin
Catalog(this@MainActivity).apply { bind(MIAM_CATEGORY_ID) }.Content()
```

:::tip
You can find miam categories id on our back office    <a target="https://partners.miam.tech/" href='https://partners.miam.tech/'> **miam partner**</a> 
:::

## Direct Link

You can also use this feature directly in the application, for that you need 
to get current active catalog's categories that you can get by passing a callback :

```kotlin
PointOfSaleHandler.getCatalogCategories(::updateCategory)
```

An example of full implementation with compose :

```kotlin
 class LinkDropDownMenu(val navigateTo: (id: String) -> Unit) {

    private val categoriesState: MutableState<List<CatalogCategory>> =
        mutableStateOf(listOf())

    init {
        PointOfSaleHandler.getCatalogCategories(::fetchCategory)
    }

    private fun fetchCategory(categories: List<CatalogCategory>) {
        categoriesState.value = categories
    }

    @Composable
    fun Content() {

        val expanded = remember { mutableStateOf(false) }

        Box {
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = "More Menu"
                )
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {

                categoriesState.value.forEach {
                    DropdownMenuItem(onClick = {
                        navigateTo(it.id)
                        expanded.value = false
                    }) {
                        Text(it.title)
                    }
                }
            }
        }
    }
}
```

```kotlin
class MainActivity: ComponentActivity() {
    
    private val routes = listOf(
        Route("home", "home", Icons.Filled.Home),
        Route("Catalogue", "catalogCategories", Icons.Filled.Book),
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
             Scaffold(
                topBar = {
                    actions = { 
                        LinkDropDownMenu {
                            navController.navigate("catalog/$it") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                            }
                        }.Content()
                    }
                }
            )
            {
                NavHost( navController = navController, startDestination = "home") {
                    composable("home") {
                        Box {
                            Home()
                        }
                    }
                    composable("catalog/{Id}") {
                        Catalog(this@MainActivity).apply { bind(it.arguments?.getString("Id") ?: "", "") }.Content()
                    }
                }
            }
        }
    }
}
```


