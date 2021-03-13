package com.example.tagtrainermobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonLoginConfig()
    }

    fun setButtonLoginConfig() {
        val button = getView()?.findViewById<Button>(R.id.enterLoginId)
        button?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val paymentFragment = PaymentFragment()
              getFragmentManager()?.beginTransaction()?.add(R.id.fragmentPaymentId, paymentFragment)?.commit()
            }
        })

    }
}