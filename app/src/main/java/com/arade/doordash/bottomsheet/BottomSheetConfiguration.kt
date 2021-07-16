package com.arade.doordash.bottomsheet

/**
 * This is a configuration object used to set configurable properties of the bottom sheet.
 * @param header: is used to set properties for the header section of the bottom sheet
 * @param body : is used to set the
 */
data class BottomSheetConfiguration(
    val header: HeaderConfiguration = HeaderConfiguration(),
    val body: BodyConfiguration = BodyConfiguration(),
    val behavior: BehaviourConfiguration = BehaviourConfiguration()

) {

    data class HeaderConfiguration(
        val headerThemeResId: Int? = null,
        val headerTitleAppearanceResId: Int? = null,
        val headerSubTitleAppearanceResId: Int? = null
    )

    data class BodyConfiguration(
        val someBodyParameter: Int? = null,
    )

    data class BehaviourConfiguration(
        val peekRatio: Float = 0.5f,
        val skipCollapsed: Boolean = false,
        val isHideable: Boolean = true,
        val dismissWithAnimation: Boolean = true,
        val cancelable: Boolean = true,
        val cancelableOnTOuchOutside: Boolean = true,
    )
}