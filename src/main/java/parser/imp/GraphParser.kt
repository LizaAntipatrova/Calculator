package parser.imp

import constant.PerformedOperation
import parser.Parsable
import parser.listpriority.ListPriorityCreator
import parser.mathtree.MathSyntaxTree
import parser.mathtree.MathTreeCreator

class GraphParser : Parsable {


    override fun parsingExpression(expression: List<String>): Double {
        val mathTreeCreator: MathTreeCreator = MathTreeCreator(
                ListPriorityCreator().createListPriority(expression),
                expression
        )
        mathTreeCreator.createNewNode()
        return mathTreeTraversal(mathTreeCreator.mathSyntaxTree.topTree)
    }


    private fun mathTreeTraversal(currentNode: MathSyntaxTree.Node): Double {
        return if (currentNode.isOperation) {
            if (currentNode.operator == PerformedOperation.DIVISION) {
                val divisor = mathTreeTraversal(currentNode.rightChild)
                if (divisor == 0.0) {
                    throw ArithmeticException()
                } else {
                    PerformedOperation.calculate(
                            currentNode.operator,
                            mathTreeTraversal(currentNode.leftChild),
                            divisor)
                }
            } else {
                PerformedOperation.calculate(
                        currentNode.operator,
                        mathTreeTraversal(currentNode.leftChild),
                        mathTreeTraversal(currentNode.rightChild))
            }
        } else {
            currentNode.number
        }
    }
}