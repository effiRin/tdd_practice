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
    fun meetsAllCriteria_Then_String() {
        val result = PasswordStrengthMeter().passwordStrengthMeter("abcde123450!@#%^^000fghAAA")
        assertEquals(PasswordStrength.STRONG, result)
    }

    // https://www.techiedelight.com/conversion-between-char-and-int-in-kotlin/
    @Test
    fun intToCharTest() {
        val digit = 2
        val c = Character.forDigit(digit, 10)
        println(c) // 2

        val nums = (0..9).map { Character.forDigit(it, 10) }
        println(nums)
    }
}
