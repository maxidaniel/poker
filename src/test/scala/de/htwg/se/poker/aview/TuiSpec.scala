package de.htwg.se.poker.aview

import java.io.ByteArrayOutputStream
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.poker.controller.Controller
import de.htwg.se.poker.model.Table

class TuiSpec extends AnyWordSpec with Matchers {
  def silently(action: => Unit): String = {
    val out = new ByteArrayOutputStream()
    Console.withOut(out)(action)
    out.toString
  }

  "The Tui" when {
    "created" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      val tui = new Tui(controller)
      "register itself at the controller" in {
        controller.subscribers should contain(tui)
      }
      "list all commands in the prompt" in {
        tui.prompt should include("n = neue Runde")
        tui.prompt should include("b <Betrag> = Setzen")
        tui.prompt should include("u = Undo")
        tui.prompt should include("y = Redo")
        tui.prompt should include("q = Ende")
      }
    }
    "receiving 'n', 'f', 't' and 'r'" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      val tui = new Tui(controller)
      "start a round and show flop, turn and river" in {
        silently {
          tui.processInput("n")
          tui.processInput("f")
          tui.processInput("t")
          tui.processInput("r")
        }
        controller.table.players.map(_.hand.size) shouldBe List(2, 2)
        controller.table.board.size shouldBe 5
      }
      "print the table after every step" in {
        silently(tui.processInput("n")) should include("Pot: 0 | Board: -")
      }
    }
    "receiving 'u' and 'y'" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      val tui = new Tui(controller)
      "undo and redo the last step" in {
        silently {
          tui.processInput("n")
          tui.processInput("f")
          tui.processInput("u")
        }
        controller.table.board shouldBe empty
        silently(tui.processInput("y"))
        controller.table.board.size shouldBe 3
      }
    }
    "receiving a bet" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      val tui = new Tui(controller)
      "let every player bet a valid amount" in {
        silently(tui.processInput("b 50"))
        controller.table.pot shouldBe 100
      }
      "reject an amount that is not a number" in {
        silently(tui.processInput("b abc")) should include("Ungültiger Betrag: abc")
        controller.table.pot shouldBe 100
      }
      "reject an amount that is not positive" in {
        silently(tui.processInput("b 0")) should include("Ungültiger Betrag: 0")
        controller.table.pot shouldBe 100
      }
    }
    "receiving an unknown command" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      val tui = new Tui(controller)
      "say so and leave the table unchanged" in {
        silently(tui.processInput("x")) should include("Unbekannter Befehl: x")
        controller.table shouldBe Table(List("Alex", "Max"))
      }
    }
  }
}