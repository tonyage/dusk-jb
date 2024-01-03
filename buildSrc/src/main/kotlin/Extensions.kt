import java.util.*

fun <T> T?.toOptional() = Optional.ofNullable(this)