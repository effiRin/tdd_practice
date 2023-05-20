package ch7

class UserRegister(
    private var passwordChecker: WeakPasswordChecker
) {

//    private lateinit var passwordChecker: WeakPasswordChecker
//    fun setUserRegister(passwordChecker: WeakPasswordChecker) {
//        this.passwordChecker = passwordChecker
//    }

    fun register(id: String, pw: String, email: String) {
        // 구현 전
        if (passwordChecker.checkPasswordWeak(pw)) throw WeakPasswordException()
    }
}
