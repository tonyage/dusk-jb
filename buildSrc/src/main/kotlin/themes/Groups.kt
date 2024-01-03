package themes

import toOptional


enum class Groups(val value: String) {
    ATTRIBUTES("attributes"),
    COMMENTS("comments"),
    KEYWORDS("keywords")
}

private val groupMappings = Groups.values().associateBy { it.value }

fun String.toGroup(): Groups =
    groupMappings[this].toOptional().orElseThrow { IllegalStateException("Unknown grouping $this") }
