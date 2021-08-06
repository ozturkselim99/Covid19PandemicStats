package com.selim.covid19pandemicstats.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.selim.covid19pandemicstats.R
import com.selim.covid19pandemicstats.data.CountryNames
import com.selim.covid19pandemicstats.model.Countries
import com.selim.covid19pandemicstats.viewmodel.CovidStatsViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private val covidStatsViewModel: CovidStatsViewModel by viewModels()
    private var selectedCountry:String=""
    private lateinit var country:Countries

    override fun onCreate(savedInstanceState: Bundle?) {
        country=Countries()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)

        view.detailBtn.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToCountryStatsFragment(country)
            findNavController().navigate(action)
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
            testsTxt.text=data.tests
            detailBtn.visibility=View.INVISIBLE
            flag.setImageResource(R.drawable.world)
        })
        covidStatsViewModel._country.observe(viewLifecycleOwner, Observer { data ->
            recoveredTxt.text=data.recovered
            deathsTxt.text=data.deaths
            totalCases.text=data.cases
            activeCasesTxt.text=data.active
            statsTitle.text="CoronaVirus Cases, "+data.countryInfo.iso3
            testsTxt.text=data.tests
            Glide.with(view).load(data.countryInfo.flag).into(flag)
            detailBtn.visibility=View.VISIBLE
            country=data
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter=
                activity?.let { ArrayAdapter(it,android.R.layout.simple_list_item_1,CountryNames.getAll()) }
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
