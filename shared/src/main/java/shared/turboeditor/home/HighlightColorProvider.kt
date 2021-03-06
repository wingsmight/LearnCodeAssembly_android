package shared.turboeditor.home

import shared.turboeditor.R

interface HighlightColorProvider {

    val keywordColor: Int

    val attrColor: Int

    val attrValueColor: Int

    val commentColor: Int

    val stringColor: Int

    val numberColor: Int

    val variableColor: Int

    val registerColor: Int

    val instructionColor: Int

    val capsInstructionColor: Int
}

class AndroidHighlightColorProvider : HighlightColorProvider {
    override val keywordColor: Int
        get() = R.color.syntax_keyword

    override val attrColor: Int
        get() = R.color.syntax_attr

    override val attrValueColor: Int
        get() = R.color.syntax_attr_value

    override val commentColor: Int
        get() = R.color.syntax_comment

    override val stringColor: Int
        get() = R.color.syntax_string

    override val numberColor: Int
        get() = R.color.syntax_number

    override val variableColor: Int
        get() = R.color.syntax_variable

    override val registerColor: Int
        get() = R.color.syntax_register

    override val instructionColor: Int
        get() = R.color.syntax_instruction

    override val capsInstructionColor: Int
        get() = R.color.syntax_caps_instruction
}