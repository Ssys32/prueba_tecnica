package com.example.appblog.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.appblog.AppDataBase
import com.example.appblog.data.DataSource
import com.example.appblog.data.Entrada
import com.example.appblog.data.model.EntradaEntity
import com.example.appblog.databinding.FragmentDetailBinding
import com.example.appblog.domain.RepoImplements
import com.example.appblog.ui.viewmodel.MainViewModel
import com.example.appblog.ui.viewmodel.VMFactory

class DetailFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepoImplements(DataSource(AppDataBase.getDatabase(requireContext().applicationContext)))
        )
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var entrada: Entrada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            entrada = it.getParcelable("entrada")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.text = entrada.titulo
        binding.author.text = entrada.autor
        binding.description.text = entrada.contenido
        binding.date.text = entrada.fecha

        binding.btnDescarga.setOnClickListener {
            viewModel.guardarEntrada(
                EntradaEntity(
                    entrada.id,
                    entrada.titulo,
                    entrada.autor,
                    entrada.fecha,
                    entrada.contenido
                )
            )

            Toast.makeText(requireContext(), "Se descargo la entrada", Toast.LENGTH_SHORT).show()
        }
    }

}