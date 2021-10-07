package org.samcrow.language

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.samcrow.language.psi.DsdlTypes
import com.intellij.psi.TokenType


class DsdlSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        val SEPARATOR: TextAttributesKey =
            createTextAttributesKey("DSDL_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val KEY: TextAttributesKey = createTextAttributesKey("DSDL_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val VALUE: TextAttributesKey = createTextAttributesKey("DSDL_VALUE", DefaultLanguageHighlighterColors.STRING)
        val COMMENT: TextAttributesKey =
            createTextAttributesKey("DSDL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHARACTER: TextAttributesKey =
            createTextAttributesKey("DSDL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)


        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
        private val KEY_KEYS = arrayOf(KEY)
        private val VALUE_KEYS = arrayOf(VALUE)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOfNulls<TextAttributesKey>(0)

        // DSDL
        private val DIRECTIVE_KEYS =
            arrayOf(createTextAttributesKey("DSDL_DIRECTIVE", DefaultLanguageHighlighterColors.METADATA))
        private val STRING_KEYS = arrayOf(createTextAttributesKey("DSDL_STRING_LITERAL", DefaultLanguageHighlighterColors.STRING))
        private val BOOL_KEYS = arrayOf(createTextAttributesKey("DSDL_BOOL_LITERAL", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL))

    }

    override fun getHighlightingLexer(): Lexer {
        return DsdlLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey?> {
        return when (tokenType) {
            DsdlTypes.DSDL_DIRECTIVE -> DIRECTIVE_KEYS
            DsdlTypes.LITERAL_STRING -> STRING_KEYS
            DsdlTypes.LITERAL_BOOL -> BOOL_KEYS
            DsdlTypes.COMMENT -> COMMENT_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> EMPTY_KEYS
        }
    }
}