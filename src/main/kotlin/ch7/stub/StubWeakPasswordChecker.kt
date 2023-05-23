package ch7.stub

class StubWeakPasswordChecker() : WeakPasswordChecker {
    private var weak: Boolean = false
    // 'lateinit' modifier is not allowed on properties of primitive types

    fun setWeak(weak: Boolean) {
        this.weak = weak
    }

    override fun checkPasswordWeak(pw: String): Boolean {
        return weak
    }
}
