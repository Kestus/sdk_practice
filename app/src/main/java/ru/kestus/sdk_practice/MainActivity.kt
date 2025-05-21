package ru.kestus.sdk_practice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.WorkManager
import ru.kestus.sdk_practice.task1.FragmentRouter
import ru.kestus.sdk_practice.task1.Screens
import ru.kestus.sdk_practice.task2.ChargingStatusWorker

class MainActivity : AppCompatActivity() {

    private val workManager by lazy { WorkManager.getInstance(application) }

    val router by lazy {
        FragmentRouter(
            supportFragmentManager,
            R.id.fragment_container
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        router.navigateTo(Screens.FIRST_SCREEN)
        setupChargingWorker()
    }

    private fun setupChargingWorker() {
        workManager.enqueue(ChargingStatusWorker.makeRequest())
        Toast.makeText(this, "Worker Created", Toast.LENGTH_SHORT).show()
    }
}