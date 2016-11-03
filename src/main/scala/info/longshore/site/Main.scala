package info.longshore.site

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import info.longshore.site.libs.scalatagsMarshaller

object Main extends App {
  implicit val actorSystem = ActorSystem()
  implicit val actorMaterializer = ActorMaterializer()
  implicit val ec = actorSystem.dispatcher
  implicit val marshaller = scalatagsMarshaller()

  val resume = templates.Resume()
  val route = pathEndOrSingleSlash(complete(resume))
  val future = Http().bindAndHandle(route, "0.0.0.0", 8080)

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
