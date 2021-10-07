package org.samcrow.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.samcrow.language.psi.DsdlTypes;
import com.intellij.psi.TokenType;

%%

%class DsdlLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

//CRLF=\R
//WHITE_SPACE=[\ \n\t\f]
//FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
//VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
//END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
//SEPARATOR=[:=]
//KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "

// Begin DSDL
DSDL_COMMENT = "#"[^\r\n]*
DSDL_END_OF_LINE = "\r"?"\n"
DSDL_WHITE_SPACE = [ \t]+
DSDL_IDENTIFIER = [a-zA-Z_][a-zA-Z0-9_]*
DSDL_LITERAL_STRING_SINGLE_QUOTED = '[^'\\]*(\\[^\r\n][^'\\]*)*'
DSDL_LITERAL_STRING_DOUBLE_QUOTED = "\""[^'\\]*(\\[^\r\n][^'\\]*)*"\""
DSDL_LITERAL_STRING = ({DSDL_LITERAL_STRING_SINGLE_QUOTED}|{DSDL_LITERAL_STRING_DOUBLE_QUOTED})
DSDL_DIRECTIVE = "@"{DSDL_IDENTIFIER}
DSDL_LITERAL_BOOL = ("true"|"false")
DSDL_LEX_SRM = "---""-"+
//DSDL_MAGIC_IDENTIFIER = "_"[a-zA-Z0-9_]+"_"
// End DSDL


%%

// Begin DSDL
<YYINITIAL> {DSDL_COMMENT}                                 { yybegin(YYINITIAL); return DsdlTypes.COMMENT; }
<YYINITIAL> {DSDL_LITERAL_STRING}                          { yybegin(YYINITIAL); return DsdlTypes.LITERAL_STRING; }
<YYINITIAL> {DSDL_DIRECTIVE}                               { yybegin(YYINITIAL); return DsdlTypes.STATEMENT_DIRECTIVE; }
//<YYINITIAL> {DSDL_MAGIC_IDENTIFIER}                        { yybegin(YYINITIAL); return DsdlTypes.MAGIC_IDENTIFIER; }
<YYINITIAL> {DSDL_LITERAL_BOOL}                            { yybegin(YYINITIAL); return DsdlTypes.LITERAL_BOOL; }
<YYINITIAL> {DSDL_IDENTIFIER}                              { yybegin(YYINITIAL); return DsdlTypes.IDENTIFIER; }
<YYINITIAL> {DSDL_LEX_SRM}                                 { yybegin(YYINITIAL); return DsdlTypes.STATEMENT_SERVICE_RESPONSE_MARKER; }

<YYINITIAL> ({DSDL_WHITE_SPACE}|{DSDL_END_OF_LINE})        { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
// End DSDL

//<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return DsdlTypes.KEY; }
//
//<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return DsdlTypes.SEPARATOR; }
//
//<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
//
//<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
//
//<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return DsdlTypes.VALUE; }
//
//({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }
