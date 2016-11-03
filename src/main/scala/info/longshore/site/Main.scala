package info.longshore.site

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import info.longshore.site.libs.scalatagsMarshaller
import scala.util.Try

object Main {
  def main(args: Array[String]): Unit = {
    implicit val actorSystem = ActorSystem()
    implicit val actorMaterializer = ActorMaterializer()
    implicit val ec = actorSystem.dispatcher
    implicit val marshaller = scalatagsMarshaller()

    val port = Try(args(0).toInt).getOrElse(8080)
    val resume = templates.Resume()
    val route = pathEndOrSingleSlash(complete(resume))

    println("Starting server on port: " + port)

    val future = Http().bindAndHandle(route, "0.0.0.0", port)

    Runtime.getRuntime.addShutdownHook(
      new Thread() {
        override def run(): Unit = {
          future
            .flatMap(_.unbind())
            .onComplete(_ => actorSystem.terminate())
        }
      }
    )
  }
}
