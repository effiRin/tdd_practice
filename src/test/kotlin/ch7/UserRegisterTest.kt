package ch7

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRegisterTest() {
    private var stubPasswordChecker = StubWeakPasswordChecker()
    private lateinit var userRegister: UserRegister
    // lateinit은 주요 생성사 허용 X / 생성자 바깥의 non-null property 허용

    @BeforeEach
    fun setUp() {
        userRegister = UserRegister(stubPasswordChecker)
    }

    @Test
    fun weakPassword() {
        stubPasswordChecker.setWeak(true) // 암호가 약하다고 설정

        assertThrows(WeakPasswordException::class.java) {
            userRegister.register("id", "pw", "email")
        }
    }
}
