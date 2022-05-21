package com.son.finalproject.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.son.finalproject.utils.findNavController
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> : Fragment(){
    protected val TAG by lazy { this::class.java.name }

    private var _binding: T? = null
    protected val binding get() = _binding!!

    abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutId: Int

    private var jopEventReceiver: Job? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return _binding!!.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        jopEventReceiver = lifecycleScope.launch {
            viewModel.eventReceiver.collectLatest {
                when (it) {
                    is AppEvent.OnNavigation -> navigateToDestination(it.destination, it.bundle)
                    AppEvent.OnCloseApp -> activity?.finish()
                    AppEvent.OnBackScreen -> onBackFragment()
                    is AppEvent.OnShowToast -> showToast(it.content, it.type)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    open fun showToast(content: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, content, duration).show()
    }

    open fun showToast(contentID: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, getString(contentID), duration).show()
    }

    open fun onBackFragment() {
        Log.d(TAG, "onBackFragment: ")
        findNavController().popBackStack()
    }

    open fun navigateToDestination(destination: Int, bundle: Bundle? = null) {
        Log.d(TAG, "navigateToDestination: ")
        bundle?.let {
            findNavController().navigate(destination, it)
        } ?: findNavController().navigate(destination)
    }

    open fun openAnotherApp(packageName: String, bundle: Bundle?) {
        val launch = context?.packageManager?.getLaunchIntentForPackage(packageName)
        launch?.let {
            it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(it, bundle)
        }
    }

    open fun closeApp() {
        activity?.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")
        jopEventReceiver?.cancel()
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }

    fun onClearViewModelInScopeActivity() {
        activity?.viewModelStore?.clear()
    }
}