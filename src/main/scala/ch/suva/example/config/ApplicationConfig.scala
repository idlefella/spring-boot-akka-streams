package ch.suva.example.config

import org.springframework.boot.context.properties.{ConfigurationProperties, ConstructorBinding}

@ConfigurationProperties("application")
@ConstructorBinding
class ApplicationConfig(val example: String, val database: DatabaseConfig)

class DatabaseConfig(var hostname: String, var user: String, var password: String)
