package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)

        var lastName = parts?.getOrNull(1)

        if (firstName.isNullOrBlank()) firstName = null
        if (lastName.isNullOrBlank()) lastName = null
//        return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""

        for (c in payload) {
            var char = c
            val isTitle = char.isUpperCase()
            if (isTitle) char = char.toLowerCase()

            var word = setCharToString(char.toString(), divider)

            if (isTitle) word = word.capitalize()

            result += word
        }
        return result
    }

    private fun setCharToString(char: String, divider: String): String {
        return when (char) {
            " " -> divider
            "а" -> "a"
            "б" -> "b"
            "в" -> "v"
            "г" -> "g"
            "д" -> "d"
            "е" -> "e"
            "ё" -> "e"
            "ж" -> "zh"
            "з" -> "z"
            "и" -> "i"
            "й" -> "i"
            "к" -> "k"
            "л" -> "l"
            "м" -> "m"
            "н" -> "n"
            "о" -> "o"
            "п" -> "p"
            "р" -> "r"
            "с" -> "s"
            "т" -> "t"
            "у" -> "u"
            "ф" -> "f"
            "х" -> "h"
            "ц" -> "c"
            "ч" -> "ch"
            "ш" -> "sh"
            "щ" -> "sh'"
            "ъ" -> ""
            "ы" -> "i"
            "ь" -> ""
            "э" -> "e"
            "ю" -> "yu"
            "я"-> "ya"
            else -> char
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName?.trim().isNullOrEmpty() && lastName?.trim().isNullOrEmpty()) return null
        val first = firstName?.trim()?.firstOrNull() ?: ""
        val last = lastName?.trim()?.firstOrNull() ?: ""
        return "$first$last".toUpperCase()
    }
}