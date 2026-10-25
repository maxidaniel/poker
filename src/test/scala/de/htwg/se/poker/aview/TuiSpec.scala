package de.htwg.se.poker.aview

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.poker.model.Table

class TuiSpec extends AnyWordSpec with Matchers {
  "The Tui" when {
    val table = Table(List("Alex", "Max"))
    "receiving 'n'" should {
      "start a new round" in {
        Tui.process("n", table).players.map(_.hand.size) shouldBe List(2, 2)
      }
    }
    "receiving 'f', 't' and 'r'" should {
      "show the flop, the turn and the river" in {
        val round = Tui.process("n", table)
        val flop = Tui.process("f", round)
        val turn = Tui.process("t", flop)
        val river = Tui.process("r", turn)
        List(flop, turn, river).map(_.board.size) shouldBe List(3, 4, 5)
      }
    }
    "receiving an unknown command" should {
      "leave the table unchanged" in {
        Tui.process("x", table) shouldBe table
      }
    }
    "asked for the prompt" should {
      "list all commands" in {
        Tui.prompt should include("n = neue Runde")
        Tui.prompt should include("q = Ende")
      }
    }
  }
}