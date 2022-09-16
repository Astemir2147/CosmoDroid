package com.ilein.cosmodroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilein.cosmodroid.databinding.FragmentMenuSearchBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuSearchFragment : Fragment() {

    private var _binding: FragmentMenuSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        val sa1 = SearchItem(1, "https://nyc3.digitaloceanspaces.com/spacelaunchnow-prod-east/media/event_images/soyuz_ms-21_und_image_20210922181306.jpeg",
            "Events")
        val sa2 = SearchItem(2, "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/static/home/thespacedevs/images/ll2features/launchvehicles.jpg",
            "Launches")
        val sItems = ArrayList<SearchItem>()
        sItems.add(sa1)
        sItems.add(sa2)

        binding.rvSearch.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MenuSearchAdapter()
        }
        val adapter = (binding.rvSearch.adapter as MenuSearchAdapter)
        adapter.submitList(sItems)
        adapter.notifyDataSetChanged()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}