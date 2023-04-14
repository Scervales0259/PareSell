package ph.edu.dlsu.mobdeve.cervales.santiago.paresell

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.databinding.ActivitySellerBinding
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.model.Product
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.model.User

class SellerActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySellerBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView : RecyclerView
    private var productList : MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if(currentUser != null){
            loadData()
            recyclerView = binding.listedProductsRv
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = ItemAdapter(productList)

            val bundle = intent.extras

            binding.icon.setImageResource(bundle!!.getInt("icon", R.drawable.icon))
            binding.accountName.text = bundle!!.getString("sellerName", "Shams Cervales")
            binding.accountLocation.text = bundle!!.getString("sellerLoc", "Pasay City")


            binding.homeContainer.setOnClickListener {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            binding.favsContainer.setOnClickListener {
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
                finish()
            }

            binding.uploadContainer.setOnClickListener {
                val intent = Intent(this, UploadActivity::class.java)
                startActivity(intent)
                finish()
            }

            binding.accountContainer.setOnClickListener {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun loadData(){
        val user1 = User("1", "John", "Doe", "johndoe", "password1", "johndoe@example.com", "1234567890", "123 Main St", "New York", R.drawable.icon)
        val user2 = User("2", "Jane", "Doe", "janedoe", "password2", "janedoe@example.com", "0987654321", "456 Broadway", "Los Angeles", R.drawable.icon)
        val user3 = User("3", "Bob", "Smith", "bobsmith", "password3", "bobsmith@example.com", "5555555555", "789 Elm St", "Chicago", R.drawable.icon)
        val user4 = User("4", "Alice", "Johnson", "alicejohnson", "password4", "alicejohnson@example.com", "1112223333", "321 Oak St", "Houston", R.drawable.icon)
        val user5 = User("5", "Sam", "Garcia", "samgarcia", "password5", "samgarcia@example.com", "4445556666", "654 Pine St", "San Francisco", R.drawable.icon)
        val user6 = User("6", "Sara", "Lee", "saralee", "password6", "saralee@example.com", "7778889999", "987 Cedar St", "Boston", R.drawable.icon)
        val user7 = User("7", "David", "Brown", "davidbrown", "password7", "davidbrown@example.com", "1231231234", "456 Elm St", "Dallas", R.drawable.icon)
        val user8 = User("8", "Emily", "Taylor", "emilytaylor", "password8", "emilytaylor@example.com", "4564564567", "789 Oak St", "Miami", R.drawable.icon)
        val user9 = User("9", "Michael", "Johnson", "michaeljohnson", "password9", "michaeljohnson@example.com", "9876543210", "123 Maple St", "Seattle", R.drawable.icon)
        val user10 = User("10", "Karen", "Lee", "karenlee", "password10", "karenlee@example.com", "1112223333", "456 Maple St", "Atlanta", R.drawable.icon)

        productList.add(Product("1", "T-shirt", 20.0, "Cotton t-shirt", R.drawable.a, "Clothing",user1,"recently added"))
        productList.add(Product("2", "Jeans", 50.0, "Denim jeans", R.drawable.a, "Clothing",user2, "top picks"))
        productList.add(Product("3", "Sneakers", 80.0, "Running shoes", R.drawable.a, "Shoes",user3, "nearby"))
        productList.add(Product("4", "Backpack", 35.0, "Waterproof backpack", R.drawable.a, "Bags",user4, "recently added"))
        productList.add(Product("5", "Watch", 120.0, "Digital wristwatch", R.drawable.a, "Accessories",user5, "top picks"))
        productList.add(Product("6", "Smartphone", 500.0, "Android smartphone", R.drawable.a, "Electronics",user6, "nearby"))
        productList.add(Product("7", "Laptop", 1000.0, "Windows laptop", R.drawable.a, "Electronics",user7, "recently added"))
        productList.add(Product("8", "Headphones", 50.0, "Over-ear headphones",R.drawable.a, "Accessories",user8, "top picks"))
        productList.add(Product("9", "Skirt", 30.0, "Floral print skirt", R.drawable.a, "Clothing",user9, "nearby"))
        productList.add(Product("10", "Sweatshirt", 45.0, "Hooded sweatshirt", R.drawable.a, "Clothing",user10, "recently added"))
    }
}
