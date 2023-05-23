package ch7

import ch7.fakeRepo.MemoryUserRepository
import ch7.fakeRepo.UserRegister
import ch7.spy.EmailNotifier
import ch7.stub.WeakPasswordChecker
import ch7.stub.WeakPasswordException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito
import org.mockito.BDDMockito.then
import org.mockito.Mockito
import kotlin.test.assertEquals

class UserRegisterMockTest {
    private lateinit var userRegister: UserRegister
    // lateinit은 주요 생성사 허용 X / 생성자 바깥의 non-null property 허용

    private val mockPasswordChecker = Mockito.mock(WeakPasswordChecker::class.java)
    private val fakeRepository = MemoryUserRepository()
    private val mockEmailNotifier = Mockito.mock(EmailNotifier::class.java)

    @BeforeEach
    fun setUp() {
        userRegister = UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier)
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    fun weakPassword() {
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw"))
            .willReturn(true)

        assertThrows(WeakPasswordException::class.java) {
            userRegister.register("id", "pw", "email")
        }
    }

    @DisplayName("회원 가입 시 암호 검사 수행함")
    @Test
    fun checkPassword() {
        userRegister.register("id", "pw", "email")

        BDDMockito.then(mockPasswordChecker)
            .should()
            .checkPasswordWeak(BDDMockito.anyString())
    }

    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

    @DisplayName("가입하면 메일을 전송함")
    @Test
    fun whenRegisterThenSendMail() {
        userRegister.register("id,", "pw", "email@email.com")

        val captor: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)

//        BDDMockito.then(mockEmailNotifier)
//            .should().sendRegisterEmail(captor.capture())
//        captor.capture()가 계속 null 나옴 -> kotlin 이슈임
// https://stackoverflow.com/questions/34773958/kotlin-and-argumentcaptor-illegalstateexception

        then(mockEmailNotifier)
            .should().sendRegisterEmail(capture(captor))

        val realEmail = captor.value
        assertEquals("email@email.com", realEmail)
    }
}
