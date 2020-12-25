package shared.turboeditor.home

import android.graphics.Color
import shared.turboeditor.home.texteditor.Patterns
import shared.turboeditor.util.MimeTypes
import java.util.regex.Pattern

class HighlightDriver(private val colorProvider: HighlightColorProvider,
                      private val fileExtension: String) {

    fun highlightText(textToHighlight: CharSequence, firstColoredIndex: Int): MutableList<HighlightInfo> {
        val highlights = mutableListOf<HighlightInfo>()

        if(fileExtension.contains(AsmExtension)) {
            highlights.addAll(color(Patterns.GENERAL_REGISTERS, textToHighlight, firstColoredIndex))
            highlights.addAll(color(Patterns.INSTRUCTIONS, textToHighlight, firstColoredIndex))
            highlights.addAll(color(Patterns.NUMBERS_OR_SYMBOLS, textToHighlight, firstColoredIndex))
            highlights.addAll(color(Patterns.CAPS_INSTRUCTIONS, textToHighlight, firstColoredIndex))
        }

        return highlights
    }

    private fun color(pattern: Pattern, textToHighlight: CharSequence, firstColoredIndex: Int): List<HighlightInfo> {
        var color = 0
        if (pattern == Patterns.GENERAL_COMMENTS
                || pattern == Patterns.GENERAL_COMMENTS_NO_SLASH) {
            color = colorProvider.commentColor
        } else if (pattern == Patterns.GENERAL_STRINGS) {
            color = colorProvider.stringColor
        } else if (pattern == Patterns.NUMBERS
                || pattern == Patterns.SYMBOLS
                || pattern == Patterns.NUMBERS_OR_SYMBOLS) {
            color = Color.rgb(33, 150, 243)//colorProvider.numberColor
        } else if (pattern == Patterns.GENERAL_REGISTERS) {
            color = Color.rgb(255, 87, 34)//colorProvider.registerColor
        }else if (pattern == Patterns.INSTRUCTIONS) {
            color = Color.rgb(163, 43, 5)//colorProvider.instructionColor
        }else if (pattern == Patterns.CAPS_INSTRUCTIONS) {
            color = Color.rgb(163, 5, 5)//colorProvider.capsInstructionColor
        }

        val m = pattern.matcher(textToHighlight)
        val highlights = mutableListOf<HighlightInfo>()

        while (m.find()) {
            highlights.add(HighlightInfo(color,
                    firstColoredIndex + m.start(),
                    firstColoredIndex + m.end()))
        }

        return highlights
    }

    companion object {
        private const val AsmExtension = "asm"
    }
}

data class HighlightInfo(
        val color: Int,
        val start: Int,
        val end: Int
)
