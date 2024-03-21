package org.example.parser.subparser

import org.example.ast.nodes.*
import org.example.parser.Parser
import org.example.parser.subparsers.OperationCropper
import org.example.parser.TokenSearcher
import org.example.token.Token
import org.example.token.TokenType

class AssignationParser (private val tokens: List<Token>): Parser {

    override fun parse(): Node {
        val valueNode = OperationParser.createValueNode(
            OperationCropper.crop(tokens, TokenType.ASSIGNATOR).listIterator()
        )
            ?: throw Exception("Expected value after assignment operator")
        return AssignationNode(createVariableNode(), valueNode)
    }


    private fun createVariableNode(): Node {
        val idNode = IdentifierNode(TokenSearcher.searchForToken(tokens, listOf(TokenType.IDENTIFIER)))
        val typeNode = TypeNode(TokenSearcher.searchForToken(tokens, listOf(TokenType.TYPE_STRING, TokenType.TYPE_NUMBER)))
        return DeclarationNode(idNode, typeNode)
    }
}