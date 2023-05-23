package ch7

import ch7.fakeRepo.DupIdException
import ch7.fakeRepo.MemoryUserRepository
import ch7.fakeRepo.User
import ch7.fakeRepo.UserRegister
import ch7.spy.SpyEmailNotifier
import ch7.stub.StubWeakPasswordChecker
import ch7.stub.WeakPasswordException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserRegisterTest() {
    private lateinit var userRegister: UserRegister
    // lateinit은 주요 생성사 허용 X / 생성자 바깥의 non-null property 허용

    private val stubPasswordChecker = StubWeakPasswordChecker()
    private val fakeRepository = MemoryUserRepository()
    private val spyEmailNotifier = SpyEmailNotifier()

    @BeforeEach
    fun setUp() {
        userRegister = UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier)
    }

    @Test
    fun weakPassword() {
        stubPasswordChecker.setWeak(true) // 암호가 약하다고 설정

        assertThrows(WeakPasswordException::class.java) {
            userRegister.register("id", "pw", "email")
        }
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    fun dupIdExists() {
        fakeRepository.save(User("id", "pw1", "email@email.com"))

        assertThrows(DupIdException::class.java) {
            userRegister.register("id", "pw2", "email")
        }
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    fun noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email")

        val savedUser = fakeRepository.findById("id")
        assertEquals("id", savedUser?.id)
        assertEquals("email", savedUser?.email)
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    fun whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com")

        assertTrue(spyEmailNotifier.called)
        assertEquals("email@email.com", spyEmailNotifier.email)
    }
}
