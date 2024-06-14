fun String.toSnakeCase(): String {
    val builder = StringBuilder()
    var previousCharWasLowerCase = false

    var isInBracket = false
    for (char in this) {
        when {

            //ignore text in brackets
            char == '(' -> isInBracket = true
            char == ')' -> isInBracket = false
            isInBracket -> continue

            char.isUpperCase() -> {
                if (builder.isNotEmpty() && previousCharWasLowerCase) {
                    builder.append('_')
                }
                builder.append(char.toLowerCase())
                previousCharWasLowerCase = false
            }
            char.isLetterOrDigit() -> {
                builder.append(char.toLowerCase())
                previousCharWasLowerCase = true
            }
            char.isWhitespace() || char == '_' || char == '-' || char == '\t' || char == '\n' -> {
                if (previousCharWasLowerCase) {
                    builder.append('_')
                    previousCharWasLowerCase = false
                }
            }
            else -> {
                previousCharWasLowerCase = false
            }
        }
    }

    if (builder.isNotEmpty() && (builder.last() == '_' || builder.last().isWhitespace())) {
        builder.deleteCharAt(builder.length - 1)
    }

    var output = builder.toString()
    if(output.contains("_enum_")) {
        val split = output.split("_enum_")
        output = "${split[0]}_enum:${split[1]}"
    }

    return output
}
