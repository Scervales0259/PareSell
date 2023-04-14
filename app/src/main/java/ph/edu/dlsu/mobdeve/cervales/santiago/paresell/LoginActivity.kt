package ph.edu.dlsu.mobdeve.cervales.santiago.paresell

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.databinding.ActivityLoginBinding

@SuppressLint("CheckResult")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var buttonLogin: Button
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView

    lateinit var mAuth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = mAuth.currentUser

        Firebase.auth.signOut()

        if(currentUser != null){
            println("current user is not null")
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextEmail = binding.activityLoginEtEmail
        editTextPassword = binding.activityLoginEtPassword
        buttonLogin = binding.activityLoginBtnLogin
        progressBar = binding.activityLoginPgProgressBar
        textView = binding.activityLoginTvRegisterHere

        mAuth = FirebaseAuth.getInstance()

        textView.setOnClickListener{
            println("Clicked Register")
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonLogin.setOnClickListener{
            println("Clicked Login")

            progressBar.visibility = View.VISIBLE

            var email: String
            var password: String

            email = editTextEmail.text.toString()
            password = editTextPassword.text.toString()

            if(TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.d("emong", "$email, $password")
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "Login successful",
                            Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}