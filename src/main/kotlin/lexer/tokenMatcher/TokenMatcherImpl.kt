package org.example.lexer.tokenMatcher

import org.example.token.Token
import org.example.token.TokenType

class TokenMatcherImpl : TokenMatcher {
    private val regexMap: Map<Regex, TokenType> = mapOf(
        Regex("let") to TokenType.KEYWORD_LET,
        Regex("[a-zA-Z][a-zA-Z0-9_]*") to TokenType.IDENTIFIER,
        Regex("=") to TokenType.ASSIGNATOR,
        Regex(";") to TokenType.SEMICOLON,
        Regex("[0-9]+") to TokenType.LITERAL_NUMBER,
        Regex("\".*\"") to TokenType.LITERAL_STRING,
        Regex("string") to TokenType.TYPE_STRING,
        Regex("number") to TokenType.TYPE_NUMBER,
        Regex("\\+") to TokenType.OPERATOR_PLUS,
        Regex("-") to TokenType.OPERATOR_MINUS,
        Regex("\\*") to TokenType.OPERATOR_MULTIPLY,
        Regex("/") to TokenType.OPERATOR_DIVIDE,
        Regex("println") to TokenType.OPERATOR_PRINTLN
    )
    override fun getToken(input: String): Token {
        for ((regex, tokenType) in regexMap) {
            if (regex.matches(input)) {
                return Token(tokenType, input, 0)
            }
        }
        throw IllegalArgumentException("No matching token for input: $input")
    }

    override fun isValidToken(input: String): Boolean {
        try {
            getToken(input)
            return true
        } catch (e: IllegalArgumentException) {
            return false
        }
    }
}