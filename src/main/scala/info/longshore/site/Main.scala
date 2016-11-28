package info.longshore.site

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import info.longshore.site.libs.scalatagsMarshaller
import java.io.File
import scala.util.Try

object Main {
  def main(args: Array[String]): Unit = {
    implicit val actorSystem = ActorSystem()
    implicit val actorMaterializer = ActorMaterializer()
    implicit val ec = actorSystem.dispatcher
    implicit val marshaller = scalatagsMarshaller()

    val port = Try(args(0).toInt).getOrElse(8080)
    val resume = templates.resume()

    val home = System.getProperty("user.home")

    assert(home != null && home != "")

    val files = new File(
      home + File.separator + ".info" + File.separator + "longshore" + "site" + File.separator + "files"
    )

    assert(files.isDirectory || files.mkdirs())

    val route =
      pathEndOrSingleSlash {
        encodeResponse(
          complete(resume)
        )
      } ~
      pathPrefix("files") {
        getFromBrowseableDirectory(files.getAbsolutePath)
      }

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
