package com.kricko.tvshowupdater.parser;

import com.kricko.tvshowupdater.model.Shows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TvShowParserTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Should parse valid shows file")
    void shouldParseValidShowsFile() throws IOException, URISyntaxException {
        // Arrange
        File showsFile = createValidShowsFile();

        // Act
        Shows shows = TvShowParser.parseShows(showsFile);

        // Assert
        assertThat(shows)
            .isNotNull()
            .satisfies(s -> {
                assertThat(s.shows()).hasSize(1);
                assertThat(s.shows().getFirst())
                    .satisfies(show -> {
                        assertThat(show.getName()).isEqualTo("Test Show");
                        assertThat(show.getPath()).isEqualTo("/test/path");
                    });
            });
    }

    @Test
    @DisplayName("Should throw exception when shows file is invalid")
    void shouldThrowExceptionWhenShowsFileIsInvalid() throws IOException {
        // Arrange
        File showsFile = createInvalidShowsFile();

        // Act & Assert
        assertThatThrownBy(() -> TvShowParser.parseShows(showsFile))
            .isInstanceOf(IOException.class);
    }

    private File createValidShowsFile() throws IOException {
        File showsFile = tempDir.resolve("shows.json").toFile();
        String validJson = """
            {
                "shows": [
                    {
                        "name": "Test Show",
                        "path": "/test/path"
                    }
                ]
            }
            """;
        Files.writeString(showsFile.toPath(), validJson);
        return showsFile;
    }

    private File createInvalidShowsFile() throws IOException {
        File showsFile = tempDir.resolve("invalid-shows.json").toFile();
        String invalidJson = "{ invalid json }";
        Files.writeString(showsFile.toPath(), invalidJson);
        return showsFile;
    }
}
