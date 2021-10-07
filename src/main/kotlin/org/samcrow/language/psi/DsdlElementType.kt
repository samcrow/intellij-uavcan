package org.samcrow.language.psi

import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls
import org.samcrow.language.DsdlLanguage

class DsdlElementType(@NonNls debugName: String) : IElementType(debugName, DsdlLanguage.INSTANCE) {
}