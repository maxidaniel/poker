package de.htwg.se.poker

import java.io.{ByteArrayOutputStream, StringReader}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PokerSpec extends AnyWordSpec with Matchers {
  def play(input: String): String = {
    val out = new ByteArrayOutputStream()
    Console.withIn(new StringReader(input)) {
      Console.withOut(out) {
        Poker.main(Array())
      }
    }
    out.toString
  }

  "The Poker game" when {
    "the user types 'q'" should {
      "show the table once and end" in {
        val output = play("q\n")
        output should include("Alex (1000 Chips): -")
        output should include("Befehl")
      }
    }
    "the user starts a round and then quits" should {
      "show the table again with cards" in {
        val output = play("n\nq\n")
        output should include("Pot: 0 | Board: -")
        output.split("Alex \\(1000 Chips\\): -").length shouldBe 2
      }
    }
    "the input ends without 'q'" should {
      "end the game" in {
        play("") should include("Befehl")
      }
    }
  }
}