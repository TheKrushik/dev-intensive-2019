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

    fun transliterations(payload: String, divider: String = " "): String {
        var result = ""

        for (c in payload) {
            var char = c
            val isTitle = char.isUpperCase()
            if (isTitle) char = char.toLowerCase()

            var word = setCharToString(char)

            if (isTitle) word = word.capitalize()

            result += word
        }
        return result
    }

    private fun setCharToString(char: Char): String {
        return when (char) {
            ' ' -> ""
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е' -> "e"
            'ё' -> "jo"
            'ж' -> "zh"
            'з' -> "z"
            'и' -> "i"
            'й' -> "j"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "shh"
            'ъ' -> ""
            'ы' -> "y"
            'ь' -> "'"
            'э' -> "je"
            'ю' -> "yu"
            'я' -> "ya"
            else -> char.toString()
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first = firstName?.firstOrNull()
        val last = lastName?.first()
        return "$first$last".toUpperCase()
    }
}