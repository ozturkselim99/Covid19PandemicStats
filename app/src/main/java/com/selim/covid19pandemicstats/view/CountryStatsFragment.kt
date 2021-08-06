package com.selim.covid19pandemicstats.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.selim.covid19pandemicstats.R
import kotlinx.android.synthetic.main.fragment_country_stats.*


class CountryStatsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_country_stats, container, false)

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val country=CountryStatsFragmentArgs.fromBundle(it).selectedCountry
                detPopulationTxt.text=country.population
                detTotalCaseTxt.text=country.cases
                detTodayCasesTxt.text=country.todayCases
                detTotalDeathsTxt.text=country.deaths
                detTodayDeathsTxt.text=country.todayDeaths
                detRecorveredTxt.text=country.recovered
                detTodayRecorveredTxt.text=country.todayRecovered
                detActiveTxt.text=country.active
                detCriticalTxt.text=country.critical
                detCasesPerOneMillionTxt.text=country.casesPerOneMillion
                detDeathsPerOneMillionTxt.text=country.deathsPerOneMillion
                detTotalTestsTxt.text=country.tests
                detTestsPerOneMillionTxt.text=country.testsPerOneMillion
                detOneCasePerPeopleTxt.text=country.oneCasePerPeople
                detOneDeathPerPeopleTxt.text=country.oneDeathPerPeople
                detOneTestPerPeopleTxt.text=country.oneTestPerPeople
                detActivePerOneMillionTxt.text=country.activePerOneMillion
                detRecorveredPerOneMillionTxt.text=country.recoveredPerOneMillion
                detCriticalPerOneMillionTxt.text=country.criticalPerOneMillion
                detContinentTxt.text=country.continent
                detTitle.text="Coronavirus ${country.countryInfo.iso2} Detailed Statistics"
                Glide.with(view).load(country.countryInfo.flag).into(detFlag)
        }
    }

}