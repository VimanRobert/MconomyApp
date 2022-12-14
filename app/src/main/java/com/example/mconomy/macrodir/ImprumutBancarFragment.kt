package com.example.mconomy.macrodir

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentImprumutBancarBinding
import com.example.mconomy.utils.launchCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale


class ImprumutBancarFragment : Fragment() {

    private lateinit var binding: FragmentImprumutBancarBinding
    private lateinit var adapter: ImprumutAdapter
    private lateinit var recyclerView: RecyclerView
    private val perioadaArrayRO = arrayOf("Lunar", "Anual", "Semestrial", "Trimestrial")
    private val perioadaArrayEN = arrayOf("Monthly", "Yearly", "Semester", "Quarterly")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImprumutBancarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isAllFieldsChecked: Boolean

        binding.rataPick.minValue = 2
        binding.rataPick.maxValue = 10


        recyclerView = view.findViewById(R.id.list_ramburs)
        recyclerView.setHasFixedSize(true)
        adapter = ImprumutAdapter()
        recyclerView.adapter = adapter
        /*
        val rata = binding.rataPick.toString().toDouble()
        val perioada = binding.perioadaPick.toString().toDouble()
        val suma = binding.sumaImprumurata.toString().toDouble()

         */
        loanTypePreferences()

        binding.calculeazaAmortizareImprumut.setOnClickListener {
            isAllFieldsChecked = checkTimeType()
            if (isAllFieldsChecked) {
                binding.tipPerioada.text = binding.tipPerioadaInput.toString()
                val rambursList = mutableListOf<ImprumutData>()
                launchCoroutine().launch {
                    var count = 0
                    var suma: Double = binding.sumaImprumurata.text.toString().toDouble()
                    val rataDobanzii: Double = binding.rataPick.value.toString().toDouble()
                    val suma2: Double = rataDobanzii / 100
                    val suma3: Double = suma2 * suma
                    while (suma > 0) {

                        suma -= suma3

                        count++
                        val data = ImprumutData(
                            perioada = count,
                            rata = rataDobanzii.toInt(),
                            suma = suma
                        )
                        rambursList.add(data)
                    }

                    val copy = rambursList.last().copy(
                        perioada = 0,
                        rata = 0,
                        suma = 0.0
                    )

                    rambursList.removeLast()
                    rambursList.add(copy)

                    withContext(Dispatchers.Main) {
                        adapter.updateRambursList(rambursList)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkTimeType(): Boolean {

        //if (binding.tipPerioadaInput.text.toString() != "lunar" || binding.tipPerioadaInput.text.toString() != "trimestrial" || binding.tipPerioadaInput.text.toString() != "semestrial" || binding.tipPerioadaInput.text.toString() != "anual"){

        /*
        if (binding.tipPerioadaInput.length() == 0) {
            binding.tipPerioadaInput.error =
                "trebuie sa scrieti 'lunar, trimestrial, semestrial sau anual'"
            return false
        } else if (binding.tipPerioadaInput.text.toString() == "lunar") {
            binding.tipPerioada.text = "Rata lunara"
        } else if (binding.tipPerioadaInput.text.toString() == "trimestrial") {
            binding.tipPerioada.text = "Rata trimestriala"

        } else if (binding.tipPerioadaInput.text.toString() == "semestrial") {
            binding.tipPerioada.text = "Rata semestriala"
        } else if (binding.tipPerioadaInput.text.toString() == "anual") {
            binding.tipPerioada.text = "Rata anuala"
        } else {
            binding.tipPerioadaInput.error =
                "trebuie sa scrieti 'lunar, trimestrial, semestrial sau anual'"
            return false
        }

         */
        if(binding.sumaImprumurata.length() < 2){
            binding.sumaImprumurata.error = "Suma este prea mica!"
            return false
        }
        return true
    }
    private fun loanTypePreferences() {
        val localLanguage = Locale.getDefault().displayLanguage
        Log.i("Language", localLanguage)

        val perioadaAdapterEN: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, perioadaArrayEN)
        perioadaAdapterEN.setDropDownViewResource(android.R.layout.simple_gallery_item)
        val perioadaAdapterRO: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, perioadaArrayRO)
        perioadaAdapterRO.setDropDownViewResource(android.R.layout.simple_gallery_item)
        val setPerioada = binding.tipPerioadaInput
        if(binding.calculeazaAmortizareImprumut.text == "Calculeaza"){
            setPerioada.adapter = perioadaAdapterRO
            setPerioada.setSelection(0)
        }else {
            setPerioada.adapter = perioadaAdapterEN
            setPerioada.setSelection(0)
        }
    }
}