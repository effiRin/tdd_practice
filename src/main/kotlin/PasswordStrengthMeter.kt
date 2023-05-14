import java.security.InvalidParameterException

class PasswordStrengthMeter {

    fun passwordStrengthMeter(password: String): PasswordStrength {
        val passwordChars = password.toCharArray()

        // 1. 길이가 8글자 이상
        val lengthMeter = when (passwordChars.size) {
            0 -> throw InvalidParameterException()
            in 1..7 -> false
            else -> true
        }

        // 2. 0부터 9 사이의 숫자를 포함
        var containNumMeter = false
        passwordChars.forEach {
            if (it in '0'..'9') {
                containNumMeter = true
                return@forEach
            }
        } // 충격적 char를 이렇게도 검사할 수 있었군
//        val nums = (0..9).map { Character.forDigit(it, 10) }
//        val containNumMeter = passwordChars.all { it in nums }

        // 3. 대문자 포함
        val capitalLetterMeter = passwordChars.any { it.isUpperCase() }
        println(listOf(lengthMeter, containNumMeter, capitalLetterMeter))

        val result = listOf(lengthMeter, containNumMeter, capitalLetterMeter).filter { it }

        return when (result.size) {
            3 -> PasswordStrength.STRONG
            2 -> PasswordStrength.NORMAL
            1 -> PasswordStrength.WEAK
            else -> throw InvalidParameterException()
        }
    }
}
