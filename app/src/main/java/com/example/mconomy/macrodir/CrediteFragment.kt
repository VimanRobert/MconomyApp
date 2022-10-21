package com.example.mconomy.macrodir

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R
import com.example.mconomy.databinding.CrediteListBinding


class CrediteFragment : Fragment() {

    private lateinit var binding: CrediteListBinding
    private lateinit var arrayList: ArrayList<CreditData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CrediteListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val listaCreditItem: List<CreditData>
        arrayList = ArrayList()

        val tipCreditArray = arrayOf(
            "Ipotecar",
            "Imobiliar",
            "Pe viata",
            "Obligatar",
            "De consum",
            "Bancar",
            "Comercial",
            "Nebancar"
        )
        val rataDobanziiArray = arrayOf(5.0, 7.0, 10.0, 20.0, 30.0, 35.0, 1.0, 8.5, 5.5, 6.0, 6.5)
        val perioadaImprumutArray =
            arrayOf(

                "maxim de 10 ani",
                "maxim de 15 ani",
                "maxim 20 de ani",
                "maxim 25 de ani",
                "maxim 30 de ani",
                "maxim 35 de ani",
                "pe toata durata vietii"

            )
        val descriereArray = arrayOf(

            "Pentru aprobarea creditului, salariul tău net lunar trebuie să fie de minimum 1.524 lei. Dacă obții venituri de la mai mulți angajatori, se acceptă cumularea acestora atât timp cât cel puțin unul din venituri este echivalentul salariului minim pe economie.",
            "Creditul ipotecar Patria Acasă este disponibil în lei sau euro și poate fi utilizat pentru achiziționarea unui imobil ( casă sau apartament) sau unui alt credit imobiliar deținut la altă bancă.",
            "Singurul cost al asigurării este prima lunară. Adică 0,138% din valoarea soldului creditului. Și ea va scădea odată cu rambursarea creditului. Un calcul orientativ: la un credit de 20.000 de lei, suma de plată pentru prima lună este 27,60 lei. Ulterior, ea va scădea proporțional cu suma rămasă de rambursat.",
            "Creditul obligatar reprezinta o grupa a operatiilor de credit cu existenta seculara care se refera la relatiile de credit in care partenerii sunt institutiile statale sau intreprinderile economice in calitate de debitori, care emit obligatiunile, in aceasta calitate, pe de o parte, si creditorii, subscriitori si detinatori ai acestor obligatiuni, care-si angajeaza astfel capitalurile, in vederea obtinerii unui venit sigur sub forma principala de dobanzi.",
            "Creditele de consum sunt acele împrumuturi făcute pentru achiziționarea unor bunuri de consum sau servicii. Se folosesc frecvent pentru a cumpăra mașini, călătorii sau obiecte de uz casnic.",
            "Credit prin care banca pune la dispozitia solicitantului sume de bani ce vor fi restituite la un termen determinat (imprumut de bani).Creditul bancar are la baza un contract intre banca si un client, care poate fi o persoana fizica, o persoana juridica sau institutie de stat.",
            "Creditul comercial este un instrument esențial pentru companiile care doresc să cucerească noi piețe și să clădească o relație comercială pe termen lung. Indispensabil în anumite sectoare, cum ar fi sectorul distribuției sau sectorul construcțiilor, creditul comercial implică diverse riscuri, însă există modalități de a le controla în mod eficient.",
            "Un credit nebancar se caracterizeaza urmatoarele: vei primi banii rapid; ai nevoie de un numar redus de documente pentru primirea imprumutului nebancar; toti pasii de realizare a creditului nebancar se completeaza online, direct din fata calculatorului. Un credit bancar, spre deosebire de imprumutul nebancar, te obliga la: prezentarea unui numar voluminos de acte care fac obiectul eligibilitatii in vederea primirii creditului; imprumuturile bancare se obtin mult mai greu spre deosebire de creditele nebancare; practicarea comisioanelor in baza carora angajatii bancilor verifica dosarul viitorului beneficiar; pasii pe care ii urmezi pentru acordarea imprumuturilor bancare sunt realizati la sediul bancii respective."

        )
        //0 - Ipotecar
        //1 - Imobiliar
        //2 - Pe viata
        //3 - Obligatar
        //4 - De consum
        //5 - Bancar
        //6 - Comercial
        //7 - Nebancar

        val creditIpotecar = CreditData(
            tipCreditArray[0],
            rataDobanziiArray[3],
            perioadaImprumutArray[2],
            descriereArray[0]
        )
        val creditImobiliar = CreditData(
            tipCreditArray[1],
            rataDobanziiArray[5],
            perioadaImprumutArray[2],
            descriereArray[1]
        )
        val creditPeViata = CreditData(
            tipCreditArray[2],
            rataDobanziiArray[6],
            perioadaImprumutArray[4],
            descriereArray[2]
        )
        val creditObligatar = CreditData(
            tipCreditArray[3],
            rataDobanziiArray[1],
            perioadaImprumutArray[1],
            descriereArray[3]
        )
        val creditConsum = CreditData(
            tipCreditArray[4],
            rataDobanziiArray[7],
            perioadaImprumutArray[2],
            descriereArray[4]
        )
        val creditBancar = CreditData(
            tipCreditArray[5],
            rataDobanziiArray[1],
            perioadaImprumutArray[1],
            descriereArray[5]
        )
        val creditComercial = CreditData(
            tipCreditArray[6],
            rataDobanziiArray[6],
            perioadaImprumutArray[5],
            descriereArray[6]
        )
        val creditNebancar = CreditData(
            tipCreditArray[7],
            rataDobanziiArray[3],
            perioadaImprumutArray[3],
            descriereArray[7]
        )
        Log.i("x", "rata dobanzii length ${rataDobanziiArray.size}")
        Log.i("x", "rata dobanzii index ${rataDobanziiArray[3]}")
        Log.i("y", "perioada imprumut length ${perioadaImprumutArray.size}")
        Log.i("y", "perioada imprumut index ${perioadaImprumutArray[3]}")

        arrayList.add(creditIpotecar)
        arrayList.add(creditImobiliar)
        arrayList.add(creditPeViata)
        arrayList.add(creditObligatar)
        arrayList.add(creditConsum)
        arrayList.add(creditBancar)
        arrayList.add(creditComercial)
        arrayList.add(creditNebancar)

        binding.crediteListView.isClickable = true
        binding.crediteListView.adapter = CreditAdapter(requireContext(), arrayList)
        // CreditAdapter(context = Fragment(), arrayList)
        binding.crediteListView.setOnItemClickListener { _, _, position, _ ->
            val tip = tipCreditArray[position]
            val rata = rataDobanziiArray[position]
            val perioada = perioadaImprumutArray[position]
            val descriere = descriereArray[position]

            val bundle = Bundle()
            bundle.putString("tip", tip)
            bundle.putDouble("rata", rata)
            bundle.putString("perioada", perioada)
            bundle.putString("descriere", descriere)

            findNavController().navigate(R.id.action_crediteFragment_to_creditDetail, bundle)
        }


    }
}