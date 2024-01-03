package themes

enum class GroupStyling(
    val value: String
) {
    REGULAR("Regular"),
    ITALIC("Italic"),
    BOLD("Bold"),
    BOLD_ITALIC("Bold Italic")
}

private val styleMappings = GroupStyling.values().associateBy { it.value }

fun String.toGroupStyle(): GroupStyling = styleMappings.getOrDefault(
    this, GroupStyling.REGULAR
)
