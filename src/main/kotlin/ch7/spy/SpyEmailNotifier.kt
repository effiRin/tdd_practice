package ch7.spy

class SpyEmailNotifier() : EmailNotifier {
    var called: Boolean = false
        get() {
            return field
        }
    var email: String? = null
        get() {
            return field
        }

    override fun sendRegisterEmail(email: String) {
        this.called = true
        this.email = email
    }
}
// https://hongku.tistory.com/347
