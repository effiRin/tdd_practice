package ch7.fakeRepo

import ch7.spy.EmailNotifier
import ch7.stub.WeakPasswordChecker
import ch7.stub.WeakPasswordException

class UserRegister(
    private val passwordChecker: WeakPasswordChecker,
    private val userRepository: UserRepository,
    private val emailNotifer: EmailNotifier
) {

//    private lateinit var passwordChecker: WeakPasswordChecker
//    fun setUserRegister(passwordChecker: WeakPasswordChecker) {
//        this.passwordChecker = passwordChecker
//    }

    fun register(id: String, pw: String, email: String) {
        // 구현 전
        if (passwordChecker.checkPasswordWeak(pw)) throw WeakPasswordException()

        val user = userRepository.findById(id)
        if (user != null) throw DupIdException("같은 아이디가 존재합니다.")

        userRepository.save(User(id, pw, email))
        emailNotifer.sendRegisterEmail(email)
    }
}
