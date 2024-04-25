package com.example.parmegianocounter.domain

import com.example.parmegianocounter.data.model.Dish
import okhttp3.internal.toImmutableList

fun List<Dish>.frontParmegiano(): List<Dish> {
    val list = this.toMutableList()
    val element = list.find { it.name.contains("parmegiano") || it.name.contains("Parmegiano") }
    if(element != null){
        list.remove(element);
        val element2 = element.copy(isIconShown = true)
        list.add(0, element2);
    }
    return list.toImmutableList()
}