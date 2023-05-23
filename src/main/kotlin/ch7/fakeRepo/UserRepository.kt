package ch7.fakeRepo

interface UserRepository {

    fun save(user: User)

    fun findById(id: String): User?
}
