package ph.edu.dlsu.mobdeve.cervales.santiago.paresell

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.databinding.ActivityChatsBinding

class ChatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}