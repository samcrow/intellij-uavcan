package org.samcrow.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.samcrow.language.parser.DsdlParser
import org.samcrow.language.psi.DsdlFile
import org.samcrow.language.psi.DsdlTypes

class DsdlParserDefinition : ParserDefinition {

    companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(DsdlTypes.COMMENT)
        val FILE = IFileElementType(DsdlLanguage.INSTANCE)
    }

    override fun getWhitespaceTokens(): TokenSet {
        return WHITE_SPACES
    }

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements {
        return ParserDefinition.SpaceRequirements.MAY
    }

    override fun createLexer(project: Project?): Lexer {
        return DsdlLexerAdapter()
    }

    override fun createParser(project: Project?): PsiParser {
        return DsdlParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun getCommentTokens(): TokenSet {
        return COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createElement(node: ASTNode?): PsiElement {
        return DsdlTypes.Factory.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider?): PsiFile {
        return DsdlFile(viewProvider!!)
    }
}