package org.samcrow.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class DsdlFileType : LanguageFileType(DsdlLanguage.INSTANCE) {

    companion object {
        val INSTANCE = DsdlFileType()
    }

    override fun getName(): String {
        return "UAVCAN DSDL"
    }

    override fun getDescription(): String {
        return "UAVCAN data structure definition language file"
    }

    override fun getDefaultExtension(): String {
        return "uavcan"
    }

    override fun getIcon(): Icon? {
        return DsdlIcons.FILE
    }
}