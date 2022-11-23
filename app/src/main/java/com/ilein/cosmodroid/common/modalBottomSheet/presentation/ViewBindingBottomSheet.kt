package com.ilein.cosmodroid.common.modalBottomSheet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Astemir Zashaev on 2022-11-8
 */
abstract class ViewBindingBottomSheet<T : ViewBinding> : BottomSheetDialogFragment() {

    private var binding: T? = null

    /**
     * Функция для инициализации биндинга
     */
    abstract val initBinding: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> T

    /**
     * Для единоразового доступа
     * Может выкинуть [IllegalStateException] если [binding] будет равен null
     */
    protected val nonNullBinding: T
        get() = binding ?: error("Unable to get access to binding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding.invoke(inflater, container, false)
        return binding?.root
    }

    /**
     * Если нужно применить несколько действий к ViewBinding'у
     */
    protected fun withSafeBinding(block: T.() -> Unit) = binding?.let(block)

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}