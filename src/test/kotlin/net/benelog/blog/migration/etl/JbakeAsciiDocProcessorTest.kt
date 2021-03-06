package net.benelog.blog.migration.etl

import net.benelog.blog.migration.item.Post
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class JbakeAsciiDocProcessorTest {
    val processor = JbakeAsciiDocProcessor()

    @Test
    fun `AsciiDoc형식으로 변환된다`() {
       val post = Post().apply {
           no = 2323
           title = "테스트 포스팅"
           tags = "테스트, Jbake, Asciidoc"
           content = "<h2>Hello</h2>"
           nick = "정상혁"
           createdAt = LocalDateTime.of(2018, 3, 1, 0, 0);
       }

        val adocFile = processor.process(post)

        assertThat(adocFile.filename).isEqualTo("2323.adoc")
        val content = adocFile.inputStream.bufferedReader().use {
            it.readText();
        }
        println(content)
    }
}