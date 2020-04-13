package ru.skillbranch.devintensive.extensions

fun String.truncate(numb: Int = 16): String {
    val result = this.trim()

    return if (result.length < numb) result
    else result.removeRange(numb, result.length).trim() + "..."
}


