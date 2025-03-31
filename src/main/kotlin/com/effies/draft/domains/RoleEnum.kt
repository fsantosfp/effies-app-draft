package com.effies.draft.domains

enum class RoleEnum(val id: Int, private val description: String) {
    TOP(1, "Top"),
    JG(2, "Jungle" ),
    MID(3, "Mid"),
    SUP(4, "Support"),
    ADC(5, "Bottom");

    companion object{
        fun fromId(id: Int): RoleEnum? {
            return entries.find { it.id == id }
        }

        fun fromDescription(description: String): RoleEnum? {
            return entries.find { it.description.equals(description, ignoreCase = true)}
        }
    }
}
