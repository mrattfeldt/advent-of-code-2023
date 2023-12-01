import java.io.InputStream

fun readLines(resource: InputStream): List<String> {
    return String(resource.readAllBytes()).split("\n")
}
