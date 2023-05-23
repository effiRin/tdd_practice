package ch7.fakeRepo

class MemoryUserRepository : UserRepository {

    private val users = HashMap<String, User>()

    override fun save(user: User) {
        users[user.id] = user
    }

    override fun findById(id: String) = users[id]
}
