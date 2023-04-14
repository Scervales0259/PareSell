package ph.edu.dlsu.mobdeve.cervales.santiago.paresell

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.childEvents
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.databinding.ActivityRegisterBinding
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.model.User

@SuppressLint("CheckResult")
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    lateinit var editTextEmail: EditText
    lateinit var editTextUsername: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextConfirmPassword: EditText
    lateinit var editTextFirstName: EditText
    lateinit var editTextLastName: EditText
    lateinit var editTextPhoneNumber: EditText
    lateinit var editTextAddress: EditText
    lateinit var editTextCity: EditText

    lateinit var buttonReg: Button
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView

    lateinit var mAuth: FirebaseAuth
    lateinit var database: FirebaseDatabase

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        database = FirebaseDatabase.getInstance()

        if(currentUser != null){
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextEmail = binding.activityRegisterEtEmail
        editTextUsername = binding.activityRegisterEtUsername
        editTextPassword = binding.activityRegisterEtPassword
        editTextConfirmPassword = binding.activityRegisterEtConfirmPassword
        editTextFirstName = binding.activityRegisterEtFirstname
        editTextLastName = binding.activityRegisterEtLastname
        editTextPhoneNumber = binding.activityRegisterEtPhonenumber
        editTextAddress = binding.activityRegisterEtAddress
        editTextCity = binding.activityRegisterEtCity

        buttonReg = binding.activityRegisterBtnRegister
        progressBar = binding.activityRegisterPgProgressBar
        textView = binding.activityRegisterTvLoginHere


        mAuth = FirebaseAuth.getInstance()

        textView.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonReg.setOnClickListener{
            progressBar.visibility = View.VISIBLE

            var email: String
            var username: String
            var password: String
            var confirmPassword: String
            var firstName: String
            var lastName: String
            var phoneNumber: String
            var address: String
            var city: String

            email = editTextEmail.text.toString()
            username = editTextUsername.text.toString()
            password = editTextPassword.text.toString()
            confirmPassword = editTextConfirmPassword.text.toString()
            firstName = editTextFirstName.text.toString()
            lastName = editTextLastName.text.toString()
            phoneNumber = editTextPhoneNumber.text.toString()
            address = editTextAddress.text.toString()
            city = editTextCity.text.toString()

//            println("email :"+ email)
//            println("username: "+username)
//            println("password: "+password)
//            println("confirmPassword: "+confirmPassword)
//            println("firstName: "+firstName)
//            println("lastName: "+lastName)
//            println("phoneNumber: "+phoneNumber)
//            println("address: "+address)
//            println("city: "+city)

            if(TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(username)) {
                Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(this, "Enter confirm password", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(firstName)) {
                Toast.makeText(this, "Enter first name", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(lastName)) {
                Toast.makeText(this, "Enter last name", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(address)) {
                Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(city)) {
                Toast.makeText(this, "Enter city", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE

                    println("Task : " + task.isSuccessful)

                    if (task.isSuccessful) {
                        val user: User = User(mAuth.currentUser!!.uid, firstName, lastName, username, password, email, phoneNumber, address, city, R.drawable.icon)
                        val databaseRef = database.reference.child("users").child(mAuth.currentUser!!.uid)

                        databaseRef.setValue(user).addOnCompleteListener {
                            if(it.isSuccessful) {
                                Toast.makeText(this, "Account created. Please login.",
                                    Toast.LENGTH_SHORT).show()

                                var intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Something went wrong, try again",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Register failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}