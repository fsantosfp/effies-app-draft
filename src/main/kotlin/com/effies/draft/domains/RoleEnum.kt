package com.effies.draft.domains

enum class RoleEnum(val id: Int, private val description: String) {
    TOP(1, "Top"),
    JG(2, "Jungle" ),
    MID(3, "Mid"),
    SUP(4, "Support"),
    ADC(5, "Bottom");

    companion object{
        fun fromId(id: Int): String {
            return entries.find { it.id == id }?.description ?: "UNKNOWN"
        }

        fun fromDescription(description: String): Int? {
            return entries.find { it.description.equals(description, ignoreCase = true)}?.id
        }

        fun contains(id: Int): Boolean = entries.any{ it.id == id }
    }
}
