package org.samcrow.language.psi

import com.intellij.psi.tree.IElementType
import org.samcrow.language.DsdlLanguage

class DsdlTokenType(debugName: String) : IElementType(debugName, DsdlLanguage.INSTANCE) {

    override fun toString(): String {
        return "DsdlTokenType." + super.toString()
    }
}