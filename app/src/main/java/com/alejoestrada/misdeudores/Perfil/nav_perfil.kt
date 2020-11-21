package com.alejoestrada.misdeudores.Perfil

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alejoestrada.misdeudores.Login.LogInActivity2
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentPerfilBinding

class nav_perfil : Fragment() {

    private lateinit var binding: FragmentPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPerfilBinding.bind(view)

        binding.cerrarPerfilButton.setOnClickListener {
            val intent = Intent(context, LogInActivity2::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)


        }
    }

    companion object
}