package com.example.appblog.ui.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appblog.AppDataBase
import com.example.appblog.R
import com.example.appblog.data.DataSource
import com.example.appblog.data.Entrada
import com.example.appblog.databinding.FragmentMainBinding
import com.example.appblog.domain.RepoImplements
import com.example.appblog.ui.viewmodel.MainViewModel
import com.example.appblog.ui.viewmodel.VMFactory
import com.example.appblog.vo.Resource


class MainFragment : Fragment(), EntradaAdapter.onItemClickListener {

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepoImplements(DataSource(AppDataBase.getDatabase(requireContext().applicationContext)))
        )
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var myList: List<Entrada>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnInsertar.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_insertFragment)
        }
        init()
    }

    private fun init() {
        if (checkForInternet(requireContext())) {
            setUpRecyclerView()
            setUpObservers()
            setUpSearchView()

        } else {
            binding.btnInsertar.isEnabled = false
            Toast.makeText(
                requireContext(),
                "No hay conexion a Internet, se muestran los datos guardados",
                Toast.LENGTH_LONG
            ).show()
            setupRecyclerViewRoom()
            setupObserversRoom()
            setUpSearchView()
        }
    }

    private fun setUpObservers() {
        viewModel.fetchEntradasList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.loadingView.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.loadingView.visibility = View.GONE
                    binding.rvEntradas.visibility = View.VISIBLE
                    binding.rvEntradas.adapter =
                        EntradaAdapter(requireContext(), result.data, this)
                    myList = result.data

                }
                is Resource.Failure -> {
                    binding.loadingView.visibility = View.GONE

                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error ${result.exception}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun setUpRecyclerView() {
        binding.rvEntradas.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpSearchView() {
        binding.svBlogs.addTextChangedListener {
            val filtro = myList.filter { entrada ->
                entrada.autor.lowercase().contains(it.toString().lowercase())
            }
            val fil = EntradaAdapter(requireContext(), filtro, this)
            binding.rvEntradas.adapter = fil

        }
    }


    override fun onItemClick(entrada: Entrada) {
        val bundle = Bundle()
        bundle.putParcelable("entrada", entrada)
        findNavController().navigate(R.id.detailFragment, bundle)
    }


    private fun setupObserversRoom() {
        viewModel.getEntradasLocal().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.loadingView.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.loadingView.visibility = View.GONE
                    binding.rvEntradas.visibility = View.VISIBLE
                    val lista: List<Entrada> = result.data.map {
                        Entrada(it.id, it.titulo, it.autor, it.fecha, it.contenido)
                    }
                    binding.rvEntradas.adapter = EntradaAdapter(requireContext(), lista, this)
                    myList = lista
                }
                is Resource.Failure -> {
                    binding.loadingView.visibility = View.GONE
                }
            }
        })
    }

    private fun setupRecyclerViewRoom() {
        binding.rvEntradas.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    override fun onResume() {
        super.onResume()
        init()
    }
}