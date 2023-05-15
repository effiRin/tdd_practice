package ch3

import java.time.LocalDate

data class PayData(
    val billingDate: LocalDate,
    val payAmount: Int
)
