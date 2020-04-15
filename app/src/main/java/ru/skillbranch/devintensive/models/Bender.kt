package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> =
        "${validateAnswer(answer)}\n${question.question}" to status.color

    private fun validateAnswer(answer: String): String = when {
        question.validate(answer).isNotEmpty() -> question.validate(answer)
        question.answers.contains(answer.toLowerCase()) -> {
            question = question.nextQuestion()
            "Отлично - ты справился"
        }
        else -> {
            status = status.nextStatus()
            "Это неправильный ответ${if (status == Status.NORMAL) {
                question = Question.NAME
                ". Давай все по новой"
            } else ""}"
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun validate(answer: String): String =
                if (answer.firstOrNull()?.isLowerCase() == true) "Имя должно начинаться с заглавной буквы" else ""
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun validate(answer: String): String =
                if (answer.firstOrNull()?.isUpperCase() == true) "Профессия должна начинаться со строчной буквы" else ""
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun validate(answer: String): String =
                if (answer.matches(Regex(".*\\d.*"))) "Материал не должен содержать цифр" else ""
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun validate(answer: String): String =
                if (!answer.matches("[0-9]+".toRegex())) "Год моего рождения должен содержать только цифры" else ""
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): String =
                if (answer.length != 7 || !answer.matches("[0-9]+".toRegex())) "Серийный номер содержит только цифры, и их 7"
                else ""
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): String = "Отлично - ты справился"
        };

        abstract fun nextQuestion(): Question
        abstract fun validate(answer: String): String
    }
}