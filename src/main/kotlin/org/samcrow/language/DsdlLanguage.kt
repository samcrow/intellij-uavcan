package org.samcrow.language

import com.intellij.lang.Language

class DsdlLanguage : Language("Data Structure Definition Language") {

    companion object {
        val INSTANCE = DsdlLanguage()
    }

}