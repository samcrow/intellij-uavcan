package org.samcrow.language

import com.intellij.lexer.FlexAdapter

class DsdlLexerAdapter : FlexAdapter(DsdlLexer(null)) {
}