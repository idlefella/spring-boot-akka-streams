package ch.suva.example

import akka.actor.ActorSystem
import ch.suva.example.streams.ExampleStream
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import scala.util.{Failure, Success}

@Component
class MyScheduler(exampleStream: ExampleStream)(implicit val actorSystem: ActorSystem) {
  val logger: Logger = LoggerFactory.getLogger(classOf[MyScheduler])

  @Scheduled(cron = "*/5 * * * * *")
  def doSomething(): Unit = {
    logger.info("Starting stream")
    exampleStream.scheduledStream().run().onComplete{
      case Success(value) => logger.info(s"Got $value")
      case Failure(exception) => logger.error("Got an exception", exception)
    }(actorSystem.dispatcher)

  }
}
