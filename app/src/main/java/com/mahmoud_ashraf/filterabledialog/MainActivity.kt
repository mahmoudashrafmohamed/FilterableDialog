package com.mahmoud_ashraf.filterabledialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mahmoud_ashraf.filterabledialog.FilterableDialog
import com.github.mahmoud_ashraf.filterabledialog.SearchUiModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countries = mutableListOf<SearchUiModel>()
        for (i in 0..24) {
            val searchListItem = SearchUiModel(i, "Country $i")
            countries.add(searchListItem)
        }
        val filterableDialog = FilterableDialog(this, countries, getString(R.string.select_country))

        // open dialog
        select_country_text.setOnClickListener {
        filterableDialog.show(onSearchItemSelected = { _, item ->
            // handle select item
            filterableDialog.dismiss()
            select_country_text.text = item.title
        })
    }
    }
}
