package ph.edu.dlsu.mobdeve.cervales.santiago.paresell

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.databinding.ActivityHomeBinding
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.model.Product
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.model.User

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var topPicksRecyclerView : RecyclerView
    private lateinit var recentylAddedRecyclerView : RecyclerView
    private lateinit var nearbyRecyclerView : RecyclerView
    private var topPicksProductList : MutableList<Product> = mutableListOf()
    private var recentylAddedProductList : MutableList<Product> = mutableListOf()
    private var nearbyProductList : MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if(currentUser != null){
            loadData()

            var topPicksAdapter = ItemAdapter(topPicksProductList)
            topPicksRecyclerView = binding.topPicksRv
            topPicksRecyclerView.layoutManager = LinearLayoutManager(this)
            topPicksRecyclerView.setHasFixedSize(true)
            topPicksRecyclerView.adapter = topPicksAdapter

            var recentlyAddedAdapter = ItemAdapter(recentylAddedProductList)
            recentylAddedRecyclerView = binding.recentlyAddedRv
            recentylAddedRecyclerView.layoutManager = LinearLayoutManager(this)
            recentylAddedRecyclerView.setHasFixedSize(true)
            recentylAddedRecyclerView.adapter = recentlyAddedAdapter

            var nearbyAdapter = ItemAdapter(nearbyProductList)
            nearbyRecyclerView = binding.nearbyRv
            nearbyRecyclerView.layoutManager = LinearLayoutManager(this)
            nearbyRecyclerView.setHasFixedSize(true)
            nearbyRecyclerView.adapter = nearbyAdapter

            binding.homeContainer.setOnClickListener {
                Firebase.auth.signOut()
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
            binding.favs1.setOnClickListener {
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
                finish()
            }

            topPicksAdapter.setOnItemClickListener(object : ItemAdapter.OnItemClickListener{
                override fun onItemClick(position: Int, boolean: Boolean) {
                    if(boolean)
                    {
                        val bundle = Bundle()

                        bundle.putString("name", topPicksProductList[position].name)
                        bundle.putString("description", topPicksProductList[position].description)
                        bundle.putString("category", topPicksProductList[position].category)
                        bundle.putInt("image", topPicksProductList[position].imageUrl)
                        bundle.putInt("icon", topPicksProductList[position].user.imageUrl)
                        bundle.putString("sellerName", topPicksProductList[position].user.firstName + " " + topPicksProductList[position].user.lastName)
                        bundle.putString("sellerLoc", topPicksProductList[position].user.address + ", " + topPicksProductList[position].user.city)

                        var intent = Intent(this@HomeActivity, ProductActivity::class.java)

                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    else
                    {
                        val bundle = Bundle()

                        bundle.putInt("icon", topPicksProductList[position].user.imageUrl)
                        bundle.putString("sellerName", topPicksProductList[position].user.firstName + " " + topPicksProductList[position].user.lastName)
                        bundle.putString("sellerLoc", topPicksProductList[position].user.address + ", " + topPicksProductList[position].user.city)

                        var intent = Intent(this@HomeActivity, SellerActivity::class.java)

                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }
            })


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

        recentylAddedProductList.add(Product("1", "T-shirt", 20.0, "Cotton t-shirt", R.drawable.tops, "Tops",user1,"recently added"))
        topPicksProductList.add(Product("2", "Jeans", 50.0, "Denim jeans", R.drawable.bottoms, "Tops",user2, "top picks"))
        nearbyProductList.add(Product("3", "Sneakers", 80.0, "Running shoes", R.drawable.shoes, "Shoes",user3, "nearby"))
        recentylAddedProductList.add(Product("4", "Backpack", 35.0, "Waterproof backpack", R.drawable.a, "Accessories",user4, "recently added"))
        topPicksProductList.add(Product("5", "Watch", 120.0, "Digital wristwatch", R.drawable.accessories, "Accessories",user5, "top picks"))
        nearbyProductList.add(Product("6", "Skirt", 30.0, "Floral print skirt", R.drawable.a, "Bottoms",user6, "nearby"))
        recentylAddedProductList.add(Product("7", "Sweatshirt", 45.0, "Hooded sweatshirt", R.drawable.a, "Tops",user7, "recently added"))
        topPicksProductList.add(Product("8", "Headphones", 50.0, "Over-ear headphones",R.drawable.a, "Accessories",user8, "top picks"))
        nearbyProductList.add(Product("9", "Skirt", 30.0, "Floral print skirt", R.drawable.a, "Bottoms",user9, "nearby"))
        recentylAddedProductList.add(Product("10", "Sweatshirt", 45.0, "Hooded sweatshirt", R.drawable.a, "Tops",user10, "recently added"))
    }
}
