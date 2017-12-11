import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn
import scala.concurrent.ExecutionContextExecutor
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import models.Couple
import play.api.libs.json.Json

object Main extends App with PlayJsonSupport {

  implicit val system: ActorSystem = ActorSystem("StalkerSystem")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val routes = pathPrefix("couple") {
    path("add") {
      post {
        entity(as[Couple]) { entry =>

          complete(HttpEntity(ContentTypes.`application/json`, Json.stringify(Json.toJson(entry))))
        }
      }
    }
  }

  val bindingFuture = Http().bindAndHandle(routes, "localhost", 9000)
  println(s"Server online at http://localhost:9000/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}