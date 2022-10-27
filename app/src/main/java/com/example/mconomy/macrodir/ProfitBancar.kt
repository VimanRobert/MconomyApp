package com.example.mconomy.macrodir

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mconomy.databinding.FragmentProfitBancarBinding
import com.example.mconomy.utils.launchCoroutine
import kotlinx.coroutines.launch
import org.jsoup.Jsoup


class ProfitBancar : Fragment() {

    private lateinit var binding: FragmentProfitBancarBinding
    private lateinit var content: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfitBancarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scratchData()
    }

    //inner class WebScratch(override val coroutineContext: CoroutineContext) : CoroutineScope {
    private fun scratchData() {
        launchCoroutine().launch {
            try {
                val webLink =
                    Jsoup.connect("https://mobileapp.allianztiriac.ro/programe-investitionale/#")
                        .get()
                content = webLink.text()
                binding.webDataProfit.text = content
            }catch (e: Exception) {
                Log.i(e.message, e.message.toString())
            }
        }
    }
    //}

}