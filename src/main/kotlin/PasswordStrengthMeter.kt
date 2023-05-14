import java.security.InvalidParameterException

class PasswordStrengthMeter {

    fun passwordStrengthMeter(password: String): PasswordStrength {
        if (password.isEmpty()) return PasswordStrength.INVALID

        val passwordChars = password.toCharArray()

        val result = listOf(
            lengthMeter(passwordChars),
            containNumMeter(passwordChars),
            passwordChars.any { it.isUpperCase() }
        ).filter { it }

        return when (result.size) {
            3 -> PasswordStrength.STRONG
            2 -> PasswordStrength.NORMAL
            1 -> PasswordStrength.WEAK
            0 -> PasswordStrength.INVALID
            else -> throw InvalidParameterException()
        }
    }

    private fun lengthMeter(passwordChars: CharArray): Boolean =
        // 1. 길이가 8글자 이상
        when (passwordChars.size) {
            in 0..7 -> false
            else -> true
        }

    private fun containNumMeter(passwordChars: CharArray): Boolean {
        // 2. 0부터 9 사이의 숫자를 포함
        var containNumMeter = false
        passwordChars.forEach {
            if (it in '0'..'9') {
                containNumMeter = true
                return@forEach
            }
        } // 충격적,, char를 이렇게 범위로 줄 수 있었음

        return containNumMeter
    }
}
