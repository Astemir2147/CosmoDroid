package com.ilein.cosmodroid.search.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilein.cosmodroid.databinding.FragmentMenuSearchBinding


class MenuSearchFragment : Fragment() {

    private var _binding: FragmentMenuSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvSearch.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MenuSearchAdapter().apply {
                setClickListener(goToDetails)
            }
        }
        val adapter = (binding.rvSearch.adapter as MenuSearchAdapter)
        adapter.submitList(EnumSearchItems.values().toList())
        adapter.notifyDataSetChanged()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val goToDetails = object : MenuSearchAdapter.IOnItemClick {
        override fun onItemClick(searchItem: EnumSearchItems) {
            findNavController().navigate(
                MenuSearchFragmentDirections.actionToSearchFragmentWithParam(
                    searchItem.id,
                    searchItem.title
                )
            )
        }
    }
}