package com.example.androidtestapp.models


class MyProductsRepository {

    companion object {
        val productSample = listOf(
            MyProduct("12726", "Farine de blé T45 FRANCINE, 1k", 1, 0.88, "id_12726"),
            MyProduct("484202", "Lait UHT entier U, 6x1L", 1, 5.46, "id_484202"),
            MyProduct("809586", "Mascarpone GALBANI - 250g", 1, 2.14, "id_809586"),
            MyProduct("970417", "Beurre doux U, 125", 1, 2.12, "id_970417"),
            MyProduct(
                "1298293",
                "Sucre en morceaux prédécoupé n°4 DADDY, 1kg",
                1,
                1.35,
                "id_1298293"
            ),
            MyProduct(
                "1922350",
                "Oeufs Plein air ELEVEURS ENGAGES L'OEUF DE NOS VILLAGES - Boîte de 12",
                1,
                3.23,
                "id_1922350"
            ),
            MyProduct(
                "1941111",
                "Chocolat noir bio 75% Pérou ALTER ECO - Tablette 100g",
                1,
                1.95,
                "id_1941111"
            ),
            MyProduct(
                "2021117",
                "Mandarine Nadorcott à feuilles, calibre 3, catégorie 1, Espagne",
                1,
                1.79,
                "id_2021117"
            ),
            MyProduct(
                "2276426",
                "Sucre vanillé ALSA, 12 sachets, 90g",
                1,
                1.91,
                "id_2276426"
            ),
            MyProduct(
                "2540700",
                "Levure chimique AlSA, sachet de 8 soit 88g",
                1,
                0.62,
                "id_2540700"
            ),
            MyProduct("3895532", "Cassonade fine DADDY, 600g", 1, 1.95, "id_3895532"),
            MyProduct(
                "4671939",
                "Pain d'épices au miel BROSSARD, 350g",
                1,
                1.96,
                "id_4671939"
            ),
            MyProduct("5068663", "Huile de tournesol U, 3l", 1, 5.99, "id_5068663"),
            MyProduct(
                "5774130",
                "Cannelle moulue U, format petit, 17g",
                1,
                0.60,
                "id_5774130"
            ),
            MyProduct(
                "6134471",
                "Banane Cavendish BIO, calibre P14, catégorie 2, Republique Dominicaine, ruban 5 fruits",
                1,
                1.99,
                "id_6134471"
            ),
            MyProduct(
                "6182231",
                "Beurre de cacahuète creamy MENGUY'S 454g",
                1,
                3.99,
                "id_6182231"
            ),
            MyProduct(
                "352902000000909790",
                "Baguette Triskel",
                1,
                1.0,
                "id_352902000000909790"
            ),
            MyProduct("88455", "Emmental français rapé U, 29%mg, 100g", 1, 0.84, "id_88455"),
            MyProduct("2107653", "Lait de coco KARA, 200ml", 1, 0.94, "id_2107653"),
            MyProduct(
                "5319173",
                "Filet de blanc de poulet U, France, barquette",
                1,
                6.06,
                "id_5319173"
            ),
            MyProduct(
                "6511680",
                "Curry tradition en poudre DUCROS, 53g",
                1,
                3.40,
                "id_6511680"
            )
        )

        fun getRandomProduct(): MyProduct {
            return productSample.random()
        }
    }
}