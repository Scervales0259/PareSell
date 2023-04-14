package ph.edu.dlsu.mobdeve.cervales.santiago.paresell

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if(currentUser != null){

            val bundle = intent.extras
            var image = bundle!!.getInt("image", R.drawable.tops)
            var productName = bundle!!.getString("name", "product name")
            var productDescription = bundle!!.getString("description", "product description")
            var icon = bundle!!.getInt("icon", R.drawable.icon)
            var accountName = bundle!!.getString("sellerName", "Shams Cervales")
            var accountLocation = bundle!!.getString("sellerLoc", "Pasay City")
            binding.image.setImageResource(image)
            binding.productName.text = productName
            binding.productDescription.text = productDescription
            binding.icon.setImageResource(icon)
            binding.accountName.text = accountName
            binding.accountLocation.text = accountLocation

            binding.sellerContainer.setOnClickListener{
                val bundle1 = Bundle()

                bundle1.putString("sellerName", accountName)
                bundle1.putString("sellerLoc", accountLocation)
                bundle1.putInt("icon", icon)
                val intent = Intent(this, SellerActivity::class.java)
                intent.putExtras(bundle1)
                startActivity(intent)
                finish()
            }
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
}