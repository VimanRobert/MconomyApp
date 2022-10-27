package com.example.mconomy.macrodir

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mconomy.databinding.FragmentRealTimeActiuniBinding
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.example.mconomy.StockAPI
import com.example.mconomy.StockData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val STOCKSURL = "https://api.polygon.io/v1/"

class RealTimeActiuniFragment : Fragment() {

    private lateinit var binding: FragmentRealTimeActiuniBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRealTimeActiuniBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teslaImg = binding.teslaImg
        val teslaUrl = "http://media.foxbusiness.com/BrightCove/854081161001/202009/3752/854081161001_6189119455001_6189125931001-vs.jpg"
        //val teslaUrl2 = "https://s3-symbol-logo.tradingview.com/tesla--big.svg"
        //Picasso.get().load(teslaUrl).into(teslaImg)
        Glide.with(requireContext()).load(teslaUrl)
            //.placeholder(R.drawable.ic_launcher_foreground)
            //.error(android.R.drawable.stat_notify_error)
            .into(teslaImg)


        getStockValues()

    }
    private fun getStockValues() {
        val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(STOCKSURL).build().create(StockAPI::class.java)
        val retrofitData = retrofit.getValues()

        retrofitData.enqueue(object : Callback<List<StockData>?> {
            override fun onResponse(
                call: Call<List<StockData>?>,
                response: Response<List<StockData>?>
            ) {
                val responseBody = response.body()!!
                val stringBuilder = StringBuilder()

                for (data in responseBody){
                    stringBuilder.append(data.symbol)
                    stringBuilder.append("\n")
                }
                binding.showStocks.text = stringBuilder
            }

            override fun onFailure(call: Call<List<StockData>?>, t: Throwable) {
                Log.i("retrofitData", "Datele nu sunt disponibile momentan")
            }
        })
    }
}