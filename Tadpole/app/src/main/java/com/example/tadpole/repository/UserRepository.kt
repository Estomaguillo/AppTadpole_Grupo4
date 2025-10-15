package com.example.tadpole.repository

import com.example.tadpole.model.User

class UserRepository {
    private val users = mutableListOf<User>()

    fun getAllUsers(): List<User> = users
    fun getUserById(id: Int): User? = users.find { it.id == id }

    fun addUser(user: User) {
        users.add(user.copy(id = users.size + 1))
    }

    fun updateUser(user: User) {
        val index = users.indexOfFirst { it.id == user.id }
        if (index != -1) users[index] = user
    }

    fun deleteUser(id: Int) {
        users.removeAll { it.id == id }
    }
}