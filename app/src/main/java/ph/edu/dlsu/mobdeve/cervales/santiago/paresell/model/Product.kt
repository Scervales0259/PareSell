package ph.edu.dlsu.mobdeve.cervales.santiago.paresell.model

class Product(
        val id: String,
        val name: String,
        val price: Double,
        val description: String,
        val imageUrl: Int,
        val category: String,
        val user: User,
        val trend: String,
)