package ch3

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class ExpiryDateCalculatorTest {
    /**
     다음 규칙에 따라 서비스 만료일을 결정한다.
     1. 서비스를 사용하려면 매달 1만원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
     2. 2개월 이상 요금을 납부할 수 있다.
     3. 10만원을 납부하면 서비스를 1년 제공한다.
     **/

    @Test
    @DisplayName("1만원 선불하면 한 달 뒤가 서비스 만료일")
    fun calculateExpireDateInCaseOneMonth() {
        val result =
            ExpiryDateCalculator().calculateExpireDate(
                PayData(
                    billingDate = LocalDate.of(2023, 1, 31),
                    payAmount = 10000
                )

            )
        assertEquals(LocalDate.of(2023, 2, 28), result)
    }

    @Test
    @DisplayName("첫 납부일의 일자와 만료일의 일자가 다른 경우")
    fun calculateExpireDateInCaseDiffDay() {
        val result =
            ExpiryDateCalculator().calculateExpireDate(
                PayData(
                    billingDate = LocalDate.of(2023, 1, 31),
                    payAmount = 30000
                )
            )
        assertEquals(LocalDate.of(2023, 4, 30), result)
    }
}
