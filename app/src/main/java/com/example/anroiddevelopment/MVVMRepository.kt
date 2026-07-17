package com.example.anroiddevelopment

class MVVMRepository {
    var count : Int=0
    fun counter() : Int{
        count++
        return count
    }
    fun getcount(): Int{
        return count
    }
}