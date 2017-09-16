package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import api.GreetingApi
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{ ExecutionContext, Future }

object AppEntryPoint extends App with GreetingApi with LazyLogging {

  implicit val system: ActorSystem = ActorSystem("defaultActorSystem")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  lazy val routes: Route = greetingRoute

  lazy val config = com.typesafe.config.ConfigFactory.load()

  val bindAddress = config.getString("application.bindAddress")
  val bindPort = config.getInt("application.bindPort")

  logger.info(s"...binding to "+bindAddress+":"+bindPort)
  val serverBindingFuture: Future[ServerBinding] = Http().bindAndHandle(routes, bindAddress, bindPort)

}
