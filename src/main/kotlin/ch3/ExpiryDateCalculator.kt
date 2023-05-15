package ch3

import java.time.LocalDate

class ExpiryDateCalculator {

    fun calculateExpireDate(payData: PayData): LocalDate {
        val expireDate = payData.billingDate.plusMonths(
            if (payData.payAmount >= 100000) 12
            else payData.payAmount / 10000L
        )
        println(expireDate)
        return expireDate
    }
}
