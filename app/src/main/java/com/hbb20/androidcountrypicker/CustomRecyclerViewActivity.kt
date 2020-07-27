package com.hbb20.androidcountrypicker

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.countrypicker.config.CPListConfig
import com.hbb20.countrypicker.config.CPRowConfig
import com.hbb20.countrypicker.datagenerator.CPDataStoreGenerator
import com.hbb20.countrypicker.models.CPCountry
import com.hbb20.countrypicker.recyclerview.CPRecyclerViewHelper
import com.hbb20.countrypicker.recyclerview.loadCountries
import kotlinx.android.synthetic.main.activity_custom_recycler_view.*

class CustomRecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_recycler_view)
        setupRecyclerView()
    }


    private fun setupRecyclerView() {

        val primaryTextGenerator = { cpCountry: CPCountry ->
            "${cpCountry.name} (${cpCountry.alpha3})"
        }

        val secondaryTextGenerator = { cpCountry: CPCountry ->
            cpCountry.capitalEnglishName
        }

        val highlightedTextGenerator = { cpCountry: CPCountry ->
            "+${cpCountry.phoneCode}"
        }

        val cpListConfig = CPListConfig(
            preferredCountryCodes = "IN,US,NZ,AU"
        )

        recyclerView.loadCountries { selectedCountry: CPCountry ->
            // your code to handle selected country
        }


        recyclerView.loadCountries(
            preferredCountryCodes = "IN,US,NZ,AU"
        ) { selectedCountry ->
            Toast.makeText(this, selectedCountry.name, Toast.LENGTH_SHORT).show()
        }

        val dataFileReader = CPDataStoreGenerator.defaultCountryFileReader
        val customFlagImageProvider = CPRowConfig.defaultFlagProvider
        val filterQueryEditText = EditText(this)

        //        recyclerView.loadCountries { selectedCountry: CPCountry ->
        //            // your code to handle selected country
        //        }
        //
        //        recyclerView.loadCountries(
        //            customMasterCountries = "IN,AD,GE,CZ,US,GB,AL,NZ",
        //            customExcludedCountries = "HU,KM",
        //            countryFileReader = dataFileReader,
        //            useCache = false,
        //            customDataStoreModifier = { dataStore -> /*Modify dataStore*/ },
        //            preferredCountryCodes = "GE,CZ",
        //            filterQueryEditText = filterQueryEditText,
        //            cpFlagProvider = customFlagImageProvider,
        //            primaryTextGenerator = { country -> country.name },
        //            secondaryTextGenerator = { country -> country.capitalEnglishName },
        //            highlightedTextGenerator = { country -> country.alpha2 }
        //        ) { selectedCountry: CPCountry ->
        //            // your code to handle selected country
        //        }


        //        val cpListConfig: CPListConfig = CPListConfig()

        // Check CP Row config for available
        val cpRowConfig: CPRowConfig = CPRowConfig()

        // See CPDataStore for available configuration
        val cpDataStore = CPDataStoreGenerator.generate(this)

        val cpRecyclerViewHelper = CPRecyclerViewHelper(
            cpDataStore = cpDataStore, // required
            cpListConfig = cpListConfig, // Default: CPListConfig()
            cpRowConfig = cpRowConfig // Default: CPRowConfig()
        ) { selectedCountry: CPCountry ->
            // required: handle selected country
        }

        // attach recyclerView to show list in recyclerView
        cpRecyclerViewHelper.attachRecyclerView(recyclerView)

        // attach query edit text (optional) to update filtered list when query updated
        cpRecyclerViewHelper.attachFilterQueryEditText(filterQueryEditText)

    }

    fun loadUsingCPRecyclerViewHelper() {
        val filterQueryEditText = EditText(this)


        // Check CPDataStore for available configuration
        val cpDataStore = CPDataStoreGenerator.generate(this)

        // Check CPListConfig for available configuration
        val cpListConfig = CPListConfig()

        // Check CPRowConfig for available configuration
        val cpRowConfig: CPRowConfig = CPRowConfig()

        val cpRecyclerViewHelper = CPRecyclerViewHelper(
            cpDataStore = cpDataStore, // required
            cpListConfig = cpListConfig, // Default: CPListConfig()
            cpRowConfig = cpRowConfig // Default: CPRowConfig()
        ) { selectedCountry: CPCountry ->
            // required: handle selected country
        }

        // attach recyclerView to show list in recyclerView
        cpRecyclerViewHelper.attachRecyclerView(recyclerView)

        // attach query edit text (optional) to update filtered list when query updated
        cpRecyclerViewHelper.attachFilterQueryEditText(filterQueryEditText)
    }
}
