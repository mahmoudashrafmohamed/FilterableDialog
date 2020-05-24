package com.github.mahmoud_ashraf.filterabledialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class FilterableDialog(var context : Context, var searchListItems: List<SearchUiModel>, private var dialogTitle: String) {

    private lateinit var alertDialog: AlertDialog
    private val adapter by lazy { SearchAdapter() }
    private lateinit var recyclerView: RecyclerView



    fun show( onSearchItemSelected: ((position: Int, searchListItem: SearchUiModel) -> Unit)? = null) {
        val adb = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.filterable_dialog_layout, null)
        val rippleViewClose = view.findViewById<View>(R.id.close) as TextView
        val title = view.findViewById<View>(R.id.spinnerTitle) as TextView
        title.text = dialogTitle
        recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        val searchBox = view.findViewById<View>(R.id.searchBox) as EditText
        adapter.onSearchItemSelected = {position, searchListItem -> onSearchItemSelected?.invoke(position, searchListItem) }
        recyclerView.adapter = adapter
        adapter.submitList(searchListItems)
        adb.setView(view)
        adb.setCancelable(false)
        rippleViewClose.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog = adb.create()
        searchBox.addTextChangedListener{
            performFilter(searchBox)
        }

        alertDialog.show()
    }

    private fun performFilter(searchBox: EditText) {
        var filteredValues =
                searchListItems.filter { searchUiModel ->
                    searchUiModel.title
                            .toLowerCase(Locale.getDefault())
                            .contains(searchBox.text.toString()
                                    .toLowerCase(Locale.getDefault()))
                } as MutableList<SearchUiModel>

        adapter.submitList(filteredValues)
    }


    fun dismiss() {
        alertDialog.dismiss()
    }


}