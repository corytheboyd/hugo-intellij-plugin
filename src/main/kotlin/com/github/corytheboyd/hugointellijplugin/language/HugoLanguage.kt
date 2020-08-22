package com.github.corytheboyd.hugointellijplugin.language

import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.lang.InjectableLanguage
import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.psi.templateLanguages.TemplateLanguage

internal class HugoLanguage : Language("Hugo"), InjectableLanguage, TemplateLanguage {
    companion object {
        @JvmField val INSTANCE = HugoLanguage()

        fun getDefaultTemplateLang() : LanguageFileType {
            return HtmlFileType.INSTANCE;
        }
    }
}
