tvshowupdater
=============

# TV Show Updater

A Java application that helps automate the management and downloading of TV show episodes using RSS feeds and qBittorrent integration.

## Features

- Automated TV show episode downloading via RSS feeds
- qBittorrent integration for torrent management
- Smart episode naming and organization
- Configurable show tracking with regex support
- Parallel processing for improved performance
- Robust error handling and logging

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- qBittorrent (configured with WebUI access)

## Building the Project

```bash
./mvnw clean package
```

This will create an executable JAR with all dependencies included.

## Configuration

The application requires a configuration file with the following settings:

- qBittorrent configuration:
  - WebUI host
  - WebUI port
  - Username
  - Password
- RSS feed URL format
- Show regex patterns for matching episodes

## Running Tests

```bash
./mvnw clean test
```

The project includes:
- Unit tests using JUnit 5 and Mockito
- Integration tests
- Code coverage reports using JaCoCo (minimum 70% coverage required)

## Dependencies

- JAXB for XML processing
- Jackson for JSON handling
- OkHttp for HTTP client operations
- Apache Commons libraries
- SLF4J and Log4j for logging
- Lombok for reducing boilerplate code
- JUnit 5, Mockito, and AssertJ for testing

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the GNU General Public License v2.0 - see the [LICENSE](LICENSE) file for details.
