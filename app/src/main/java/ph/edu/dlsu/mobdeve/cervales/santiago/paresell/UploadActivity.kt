package ph.edu.dlsu.mobdeve.cervales.santiago.paresell

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.databinding.ActivityUploadBinding


class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var editTextName: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var editTextCategory: EditText
    private lateinit var editTextDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if(currentUser != null){



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
