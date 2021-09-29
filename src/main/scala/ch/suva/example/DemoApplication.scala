package ch.suva.example

import ch.suva.example.config.ApplicationConfig
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
//@EnableScheduling
@EnableConfigurationProperties(Array(classOf[ApplicationConfig]))
class DemoApplication {

  @Bean
  def createObjectMapper: ObjectMapper = {
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper
  }
}

object DemoApplication extends App {
  SpringApplication.run(classOf[DemoApplication])
}
