package com.miam.kmm_miam_sdk.miam_core.model

data class CatalogFilterOptions(
     val name: String,
     val uiLabel: String,
     var isSelected: Boolean= false
)

class CatalogFilter {

    var difficulty : List<CatalogFilterOptions> = listOf(
        CatalogFilterOptions("1","Chef débutant"),
        CatalogFilterOptions("2","Chef intermédiaire"),
        CatalogFilterOptions("3","Top chef"),
    )
    var cost :  List<CatalogFilterOptions> = listOf(
        CatalogFilterOptions("0-5","Moins de 5€"),
        CatalogFilterOptions("5-10","Entre 5€ et 10€"),
        CatalogFilterOptions("10-1000","Plus de 10€"),
    )
    var time :  List<CatalogFilterOptions> = listOf(
        CatalogFilterOptions("15","Moins de 15 min"),
        CatalogFilterOptions("30","Moins de 30 min"),
        CatalogFilterOptions("60","Moins de 1 h"),
        CatalogFilterOptions("120","Moins de 2 h"),
    )

    fun clearFilter(){
        difficulty.forEach { option -> option.isSelected = false }
        cost.forEach { option -> option.isSelected = false }
        time.forEach { option -> option.isSelected = false }
    }

    fun getSelectedFilterAsQueryString() : String {
        var filter = ""
        val difficultyOptions = difficulty.filter { option -> option.isSelected  }
        val costOption = difficulty.find { option -> option.isSelected  }
        val timeOption = difficulty.find { option -> option.isSelected  }

        if(difficultyOptions.isNotEmpty()){
            filter+="filter[difficulty]="
            filter+= difficultyOptions.joinToString("-") { it.name }
            filter+= ",eq&"
        }
        if(costOption != null){
            filter+="filter[computed_cost]="
            val border = costOption.name.split('-')
            filter+= "${border[0]},gt,${border[1]},lt&"
        }
        if(timeOption != null){
            filter+="filter[total-time]="
            filter+= (timeOption.name +"&")
        }
        return filter
    }
}
