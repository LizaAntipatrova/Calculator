package parser.mathtree

import constant.PerformedOperation
import parser.mathtree.MathSyntaxTree.Node

class MathTreeCreator(val priority: List<Int>, val expression: List<String>) {

    val mathSyntaxTree = MathSyntaxTree()

    fun createNewNode(listPriority: List<Int> = priority,
                      currentNode: Node = mathSyntaxTree.topTree,
                      startIndex: Int = 0,
                      endIndex: Int = expression.size) {

        var indexOfOperator: Int
        val maxPriority = listPriority.max()
        if (maxPriority > 0) {
            for (i in 1..maxPriority) {
                indexOfOperator = listPriority.indexOf(i)
                if (indexOfOperator != -1) {
                    currentNode.fillingNodeOperator(expression[startIndex + indexOfOperator])
                    createNewNode(
                            listPriority.subList(0, indexOfOperator),
                            currentNode.leftChild,
                            startIndex,
                            startIndex + indexOfOperator
                    )
                    createNewNode(
                            listPriority.subList(indexOfOperator + 1, listPriority.size),
                            currentNode.rightChild,
                            startIndex + indexOfOperator + 1,
                            endIndex
                    )
                    break
                }
            }
        } else {
            createLeaf(listPriority, startIndex, endIndex, currentNode)
        }
    }

    private fun parseNumber(stringsWithDigit: List<String>): Double {
        return java.lang.String.join("", stringsWithDigit).toDouble()
    }

    private fun createLeaf(priority: List<Int>, start: Int, end: Int, nodeOfLeaf: Node) {
        var start = start
        var end = end
        var indexToCheck = priority.size - 1
        while (priority[indexToCheck] == PerformedOperation.CLOSED_BRACKET_PRIORITY) {
            end -= 1
            indexToCheck -= 1
        }
        indexToCheck = 0
        while (priority[indexToCheck] == PerformedOperation.OPENED_BRACKET_PRIORITY) {
            start += 1
            indexToCheck += 1
        }
        nodeOfLeaf.number = parseNumber(expression.subList(start, end))
    }
}