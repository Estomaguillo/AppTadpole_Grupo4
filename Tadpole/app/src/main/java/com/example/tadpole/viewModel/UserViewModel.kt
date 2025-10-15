package  com.example.tadpole.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tadpole.model.User
import com.example.tadpole.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    // Usuario de prueba por defecto
    init {
        repository.addUser(
            User(
                username = "admin",
                email = "admin@demo.com",
                password = "1234"
            )
        )
        _users.value = repository.getAllUsers()
    }

    fun login(username: String, password: String): Boolean {
        val user = repository.getAllUsers().find { it.username == username && it.password == password }
        _currentUser.value = user
        return user != null
    }

    fun logout() {
        _currentUser.value = null
    }

    fun addUser(user: User) {
        repository.addUser(user)
        _users.value = repository.getAllUsers()
    }

    fun updateUser(user: User) {
        repository.updateUser(user)
        _users.value = repository.getAllUsers()
    }

    fun deleteUser(id: Int) {
        repository.deleteUser(id)
        _users.value = repository.getAllUsers()
    }

    fun loadUsers() {
        _users.value = repository.getAllUsers()
    }
}