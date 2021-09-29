package ch.suva.example.streams

import akka.stream.scaladsl.{Keep, RunnableGraph, Sink, Source}
import ch.suva.example.ports.MyDatabase
import ch.suva.example.values.Greeting
import org.springframework.stereotype.Component

import scala.concurrent.Future

@Component
class ExampleStream(myDatabase: MyDatabase) {

  def getStreamOfGreetings(name: String, times: Int): RunnableGraph[Future[Seq[Greeting]]] = {
    Source(1 to times).map(number => Greeting(number, myDatabase.getString(name))).toMat(Sink.seq)(Keep.right)
  }

  def scheduledStream(): RunnableGraph[Future[String]] = {
    Source.single("MyTestString").toMat(Sink.head)(Keep.right)
  }

  def exampleStream(): RunnableGraph[Future[Seq[String]]] = {
    Source(1 to 10).map(_.toString).toMat(Sink.seq)(Keep.right)
  }

}
