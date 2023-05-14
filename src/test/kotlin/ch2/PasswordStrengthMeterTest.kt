package ch2

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordStrengthMeterTest {
    /**
     검사 규칙
     1. 길이가 8글자 이상
     2. 0부터 9 사이의 숫자를 포함
     3. 대문자 포함

     - 세 규칙을 모두 충족하면 암호는 강함
     - 2개의 규칙을 충족하면 암호는 보통
     - 1개 이하의 규칙을 충족하면 암호는 약함
     **/
    @Test
    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 '강함'")
    fun meetsAllCriteria_Then_Strong() {
        assertStrength("abcde123450!@#%^^000fghAAA", PasswordStrength.STRONG)
    }

    @Test
    @DisplayName("암호가 조건 두 개를 충족하면 암호 강도는 '보통'")
    fun meetsTwoCriteria_Then_Normal() {
        assertStrength("abcde123450!@#%^^000fgh", PasswordStrength.NORMAL)
    }

    @Test
    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 '약함'")
    fun meetsOneCriteria_Then_Weak() {
        assertStrength("abcdefgh", PasswordStrength.WEAK)
    }

    @Test
    @DisplayName("패스워드를 입력하지 않으면 invalid 반환")
    fun nullInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID)
    }

    @Test
    @DisplayName("성능 측정")
    fun performanceTest() {
        val start = System.currentTimeMillis()
        assertStrength("abcdefgh", PasswordStrength.WEAK)
        println(System.currentTimeMillis() - start)
    }
    // 40
    // 24

    // https://www.techiedelight.com/conversion-between-char-and-int-in-kotlin/
    @Test
    fun intToCharTest() {
        val digit = 2
        val c = Character.forDigit(digit, 10)
        println(c) // 2

        val nums = (0..9).map { Character.forDigit(it, 10) }
        println(nums)
    }

    private fun assertStrength(password: String, expectedStrength: PasswordStrength) {
        assertEquals(expectedStrength, PasswordStrengthMeter().passwordStrengthMeter(password))
    }
}
