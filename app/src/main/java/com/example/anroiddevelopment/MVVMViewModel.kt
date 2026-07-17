package com.example.anroiddevelopment

import androidx.lifecycle.ViewModel

class MVVMViewModel : ViewModel() {
     val repository= MVVMRepository()

    fun increment() : Int{
        return repository.counter()
    }
    fun getcount(): Int{
        return repository.getcount()
    }
}