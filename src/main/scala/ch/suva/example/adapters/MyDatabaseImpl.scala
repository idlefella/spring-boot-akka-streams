package ch.suva.example.adapters

import ch.suva.example.config.ApplicationConfig
import ch.suva.example.ports.MyDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MyDatabaseImpl extends MyDatabase {

  @Autowired
  private val applicationConfig: ApplicationConfig = null

  override def getString(name: String): String = {
    s"Hello ${name}, ${applicationConfig.database.hostname}"
  }
}

