package org.samcrow.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.samcrow.language.DsdlFileType
import org.samcrow.language.DsdlLanguage

class DsdlFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, DsdlLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return DsdlFileType.INSTANCE
    }

    override fun toString(): String {
        return "DSDL File"
    }
}