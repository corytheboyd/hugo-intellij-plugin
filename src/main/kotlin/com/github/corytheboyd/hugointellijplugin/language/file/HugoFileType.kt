package com.github.corytheboyd.hugointellijplugin.language.file

import com.github.corytheboyd.hugointellijplugin.language.HugoIcons
import com.github.corytheboyd.hugointellijplugin.language.HugoLanguage
import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.CharsetUtil
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.TemplateLanguageFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings
import java.nio.charset.Charset
import javax.swing.Icon

class HugoFileType: LanguageFileType(HugoLanguage.INSTANCE), TemplateLanguageFileType {
    companion object {
        @JvmField val INSTANCE = HugoFileType()

        const val DEFAULT_EXTENSION = "html;md"
        const val NAME = "Hugo"
        const val DESCRIPTION = "Hugo HTML/Markdown template language support"

        @JvmStatic private fun getAssociatedFileType(file: VirtualFile, project: Project?): LanguageFileType? {
            if (project == null) {
                return null;
            }

            val language: Language? = TemplateDataLanguageMappings.getInstance(project).getMapping(file);

            var associatedFileType: LanguageFileType? = null;
            if (language != null) {
                associatedFileType = language.associatedFileType;
            }

            if (language == null || associatedFileType == null) {
                associatedFileType = HugoLanguage.getDefaultTemplateLang();
            }
            return associatedFileType;
        }
    }

    override fun getName(): String {
        return NAME
    }

    override fun getDescription(): String {
        return DESCRIPTION
    }

    override fun getDefaultExtension(): String {
        return DEFAULT_EXTENSION;
    }

    override fun getIcon(): Icon? {
        return HugoIcons.FILE;
    }

    /**
     * TODO Unclear if we need to override this, per the Mustache implementation:
     *  https://github.com/JetBrains/intellij-plugins/blob/057903ec52881fc6e194cee4c16468b09597a4f5/handlebars/src/com/dmarcotte/handlebars/file/HbFileType.java#L56-L67
     * */
    override fun extractCharsetFromFileContent(project: Project?, file: VirtualFile?, content: CharSequence): Charset? {
        val associatedFileType = getAssociatedFileType(file!!, project) ?: return null

        return CharsetUtil.extractCharsetFromFileContent(project, file, associatedFileType, content)
    }
}
