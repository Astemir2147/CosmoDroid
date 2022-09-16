package com.ilein.cosmodroid.search

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.FragmentMenuSearchBinding


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
        val sa3 = SearchItem(3, "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/static/home/thespacedevs/images/ll2features/astronauts.jpg",
            "Astronauts")
        val sa4 = SearchItem(4, "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/static/home/thespacedevs/images/ll2features/agencies.jpeg",
            "Agencies")
        val sa5 = SearchItem(5, "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/static/home/thespacedevs/images/ll2features/spacestations.jpg",
            "Space Stations")
        val sItems = ArrayList<SearchItem>()
        sItems.add(sa1)
        sItems.add(sa2)
        sItems.add(sa3)
        sItems.add(sa4)
        sItems.add(sa5)

        binding.rvSearch.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MenuSearchAdapter().apply {
                setClickListener(goToDetails)
            }
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

    private val goToDetails = object : MenuSearchAdapter.IOnItemClick {
        override fun onItemClick(searchItem: SearchItem) {
            findNavController().navigate(R.id.action_toSearchFragmentWithParam)
        }
    }
}