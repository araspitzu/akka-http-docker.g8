package example

import java.time.LocalDateTime

package object domain {

  case class Greeting(daGreeting: String, greetingTime: LocalDateTime = LocalDateTime.now)

  object Greeting {
    def apply(name: String): Greeting = new Greeting(s"Hello "+name)
  }

}

