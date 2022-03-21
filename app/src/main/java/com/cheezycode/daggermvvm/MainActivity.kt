package com.cheezycode.daggermvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cheezycode.daggermvvm.db.FakerDB
import com.cheezycode.daggermvvm.viewmodels.MainViewModel
import com.cheezycode.daggermvvm.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var fakerDB1 : FakerDB

    @Inject
    lateinit var fakerDB2 : FakerDB

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val products: TextView
    get() = findViewById(R.id.products)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as FakerApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.productsLiveData.observe(this, Observer {
           products.text =  it.joinToString { x -> x.title + "\n\n" }
        })
    }
}



















