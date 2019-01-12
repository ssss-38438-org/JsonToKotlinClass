package wu.seal.jsontokotlin.codeelements

/**
 * Name class
 * Created by Seal.Wu on 2017/9/21.
 */


interface IKName {

    fun getName(rawName: String): String

}

abstract class KName : IKName {

    private val suffix = "X"

    protected val illegalNameList = listOf<String>(
            "as", "break", "class", "continue", "do", "else", "false", "for", "fun", "if", "in", "interface", "is", "null"
            , "object", "package", "return", "super", "this", "throw", "true", "try", "typealias", "val", "var", "when", "while"
    )


    protected val illegalCharacter = listOf<String>(
            "\\+", "\\-", "\\*", "/", "%", "=", "&", "\\|", "!", "\\[", "\\]", "\\{", "\\}", "\\(", "\\)", "\\\\", "\"", "_"
            , ",", "\\.", ":", "\\?", "\\>", "\\<", "@", ";", "'", "\\`", "\\~", "\\$", "\\^", "#", "\\", "/", " ", "\t", "\n"
    )


    protected val nameSeparator = listOf<String>(" ", "_", "\\-", ":")


    /**
     * remove the start number or whiteSpace characters in this string
     */
    protected fun removeStartNumberAndIllegalCharacter(it: String): String {

        val numberAndIllegalCharacters = listOf<String>(*illegalCharacter.toTypedArray(), "\\d")

        val firstNumberAndIllegalCharactersRegex = "^(${numberAndIllegalCharacters.toRegex()})+".toRegex()

        return it.trim().replaceFirst(firstNumberAndIllegalCharactersRegex, "")

    }

    protected fun toBeLegalName(name: String): String {
        val tempName = name.replace(illegalCharacter.toRegex(), "")

        val legalName = if (tempName in illegalNameList) {
            tempName + suffix
        } else {
            tempName
        }
        return legalName
    }

    /**
     * array string into regex match patten that could match any element of the array
     */
    protected fun Iterable<String>.toRegex() = joinToString(separator = "|").toRegex()


}
