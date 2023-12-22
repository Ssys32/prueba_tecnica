package com.example.appblog.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.appblog.AppDataBase
import com.example.appblog.data.DataSource
import com.example.appblog.databinding.FragmentInsertBinding
import com.example.appblog.domain.RepoImplements
import com.example.appblog.ui.viewmodel.MainViewModel
import com.example.appblog.ui.viewmodel.VMFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class InsertFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepoImplements(DataSource(AppDataBase.getDatabase(requireContext().applicationContext)))
        )
    }

    private var _binding: FragmentInsertBinding? = null
    private val binding get() = _binding!!
    var fecha: Date = Date()
    @SuppressLint("SimpleDateFormat")
    var df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    private var salida: String = df.format(fecha)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtT = binding.textTitulo.text
        val txtA = binding.textAutor.text
        val txtC = binding.textContenido.text
        binding.btnGuardar.setOnClickListener {

            if (txtT.isNullOrEmpty() || txtA.isNullOrEmpty() || txtC.isNullOrEmpty())
            {
                Toast.makeText(
                    requireContext(),
                    "Completa todos los campos",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                viewModel.insertarEntrada(txtT.toString(), txtA.toString(), salida, txtC.toString()
                )
                binding.textTitulo.text = null
                binding.textAutor.text = null
                binding.textContenido.text = null
               findNavController().navigateUp()
                Toast.makeText(
                    requireContext(),
                    "Se inserto la entrada",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}