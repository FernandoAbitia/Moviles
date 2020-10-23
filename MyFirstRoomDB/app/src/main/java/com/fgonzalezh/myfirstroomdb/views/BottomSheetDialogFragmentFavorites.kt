package com.fgonzalezh.myfirstroomdb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fgonzalez.myfirstroomdb.interfaces.BottomDialogListener
import com.fgonzalezh.myfirstroomdb.R
import com.fgonzalezh.myfirstroomdb.adapters.RowFavoriteAdapter
import com.fgonzalezh.myfirstroomdb.viewmodels.BottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_favorites.*

class BottomSheetDialogFragmentFavorites(val contentId: Long): BottomSheetDialogFragment() {

    private val bottomSheetViewModel: BottomSheetViewModel by viewModels()

    private val clickListener = object: BottomDialogListener{
        override fun onClick(contentId: Long) {
            bottomSheetViewModel.deleteFavorite(this@BottomSheetDialogFragmentFavorites.contentId, contentId)
            Toast.makeText(context,"Favorito Borrado",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.bottomsheet_favorites,container,false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewBottomDialog)
        recyclerView.layoutManager = LinearLayoutManager(context)

        bottomSheetViewModel.getFavorites(contentId).observe(
            viewLifecycleOwner, { contents ->
                val rowFavoriteAdapter = RowFavoriteAdapter(contents, clickListener)
                recyclerView.adapter = rowFavoriteAdapter

                rowFavoriteAdapter.notifyDataSetChanged()
            }
        )
        return view
    }

}