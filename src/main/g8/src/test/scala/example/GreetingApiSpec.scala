package example

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ ContentTypes, StatusCodes }
import akka.http.scaladsl.testkit.Specs2RouteTest
import api.GreetingApi
import example.domain.Greeting
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class GreetingApiSpec extends Specification with Specs2RouteTest {

  trait mockedScope extends GreetingApi with Scope {

    lazy val routes = greetingRoute
    implicit val system = ActorSystem("test")

  }

  "Greeting API" should {

    "return the greeting in json format" in new mockedScope {

      val greetingName = "Andrea"

      Get("/hello/"+greetingName) ~> routes ~> check {

        handled === true
        status === (StatusCodes.OK)
        contentType === (ContentTypes.`application/json`)
        val resultGreeting = entityAs[Greeting]
        resultGreeting.daGreeting === "Hello "+greetingName

      }
    }

  }

}