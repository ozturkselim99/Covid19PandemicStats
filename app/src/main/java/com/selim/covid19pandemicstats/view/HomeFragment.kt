package com.selim.covid19pandemicstats.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.selim.covid19pandemicstats.R
import com.selim.covid19pandemicstats.viewmodel.CovidStatsViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private val covidStatsViewModel: CovidStatsViewModel by viewModels()
    private var selectedCountry:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)

        view.detailBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_countryStatsFragment)
        }

        if (selectedCountry=="")
        {
            covidStatsViewModel.getAll()
        }
        covidStatsViewModel.allCountries.observe(viewLifecycleOwner, Observer { data ->
           recoveredTxt.text=data.recovered
            deathsTxt.text=data.deaths
            totalCases.text=data.cases
            activeCasesTxt.text=data.active
            statsTitle.text="CoronaVirus Cases, World"
            flag.setImageResource(R.drawable.png_transparent_globe_emoji_earth_americas_world_globe_miscellaneous_english_globe)
        })
        covidStatsViewModel._country.observe(viewLifecycleOwner, Observer { data ->
            recoveredTxt.text=data.recovered
            deathsTxt.text=data.deaths
            totalCases.text=data.cases
            activeCasesTxt.text=data.active
            statsTitle.text="CoronaVirus Cases, "+data.countryInfo.iso3
            Glide.with(view).load(data.countryInfo.flag).into(flag)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countries=resources.getStringArray(R.array.countries)
        val arrayAdapter=
                activity?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,countries) }
        autoCompleteTextView.setAdapter(arrayAdapter)
        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener{
            parent,view,position,id->
            val selectedItem = parent.getItemAtPosition(position).toString()
           selectedCountry=selectedItem
            getCountry(selectedCountry)
        }


    }
    fun getCountry(country:String)
    {
        covidStatsViewModel.getCountry(country)
    }

}