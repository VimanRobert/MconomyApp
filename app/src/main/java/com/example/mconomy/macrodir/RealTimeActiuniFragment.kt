package com.example.mconomy.macrodir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentRealTimeActiuniBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

const val STOCKSURL =
    "https://host:port/ic/api/integration/v1/flows/rest/ORCL-R-REST_STOCK_SERVICE/1.0/v1/"

@GlideModule
class RealTimeActiuniFragment : Fragment() {
    private lateinit var binding: FragmentRealTimeActiuniBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRealTimeActiuniBinding.inflate(layoutInflater, container, false)
        getPhoto()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getStockValues()

    }

    /*
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
                    stringBuilder.append(data.high)
                    stringBuilder.append("\n")
                    stringBuilder.append(data.low)
                    stringBuilder.append("\n")
                    stringBuilder.append(data.open)
                    stringBuilder.append("\n")
                    stringBuilder.append(data.volume)
                    stringBuilder.append("\n")
                }
                binding.showStocks.text = stringBuilder
            }

            override fun onFailure(call: Call<List<StockData>?>, t: Throwable) {
                Log.i("retrofitData", "Datele nu sunt disponibile momentan")
            }
        })
    }

     */
    private fun getPhoto() {
        val dbRef: DatabaseReference = Firebase.database.reference
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val img = snapshot.getValue<Photo>()
                img?.let {
                    if (img.Img == "") {
                        binding.teslaImg.setImageResource(R.drawable.ic_launcher_background)
                    } else {
                        context?.let { it1 ->
                            Glide.with(it1).load(img.Img.toString()).into(binding.teslaImg)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to load Img.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    data class Photo(var Img: String? = null)
}
