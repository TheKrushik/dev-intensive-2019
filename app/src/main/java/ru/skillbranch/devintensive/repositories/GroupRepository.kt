package ru.skillbranch.devintensive.repositories

import ru.skillbranch.devintensive.data.managers.CacheManager
import ru.skillbranch.devintensive.models.data.Chat
import ru.skillbranch.devintensive.models.data.User
import ru.skillbranch.devintensive.models.data.UserItem
import ru.skillbranch.devintensive.utils.DataGenerator

object GroupRepository {
    fun loadUser(): List<User> = DataGenerator.stabUsers
    fun createChat(items: List<UserItem>) {
        val ids = items.map { it.id }
        val users = CacheManager.findUsersByIds(ids)
        val titile = users.map { it.firstName }.joinToString(", ")
        val chat = Chat( CacheManager.nextChatId(), titile, users)
        CacheManager.insertChat(chat)
    }
}