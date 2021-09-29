package ch.suva.example.ports

import org.springframework.stereotype.Component
;

trait MyDatabase {
  def getString(name: String): String
}

object MyDatabase {

  class Fake extends MyDatabase {
    override def getString(name: String): String = s"Fake ${name}"
  }
}