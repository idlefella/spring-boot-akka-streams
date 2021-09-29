package ch.suva.example.controller

import akka.actor.ActorSystem
import ch.suva.example.config.ApplicationConfig
import ch.suva.example.streams.ExampleStream
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

import scala.jdk.FutureConverters._

case class RandomClass(strings: Seq[String])

@RestController
class AkkaStreamController(
                            meterRegistry: MeterRegistry,
                            exampleStream: ExampleStream,
                            applicationConfig: ApplicationConfig)(implicit system: ActorSystem) {

  val requestCounter = meterRegistry.counter("request-counter")

  @GetMapping(path = Array("/akka-stream"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def akkaStreamEndpoint(@RequestParam(value = "name", defaultValue = "World") name: String) = {
    requestCounter.increment()
    exampleStream.getStreamOfGreetings(name, 10).run().asJava.toCompletableFuture
  }

  @GetMapping(path = Array("/akka-stream2"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def akkaStreamEndpoint2() = {
    requestCounter.increment()
    exampleStream.exampleStream().run().asJava.toCompletableFuture
  }

  @GetMapping(Array("/test2"))
  def testEndpoint2() = {
    RandomClass(Seq("A", "B"))
  }

  @GetMapping(Array("/test4"))
  def testEndpoint4() = {
    Seq("A", "B")
  }

  @GetMapping(Array("/config"))
  def getConfig() = {
    applicationConfig
  }
}
