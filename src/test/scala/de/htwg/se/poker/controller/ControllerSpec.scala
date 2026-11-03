package de.htwg.se.poker.controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.poker.model.Table
import de.htwg.se.poker.util.Observer

class ControllerSpec extends AnyWordSpec with Matchers {
  class TestObserver extends Observer {
    var updates: Int = 0
    override def update(): Unit = updates += 1
  }

  "A Controller" when {
    "created with a new table" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      "show the table as text" in {
        controller.tableToString shouldBe
          "Pot: 0 | Board: -\nAlex (1000 Chips): -\nMax (1000 Chips): -"
      }
    }
    "playing a full round" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      val observer = new TestObserver
      controller.add(observer)
      "deal two cards to every player on newRound" in {
        controller.newRound()
        controller.table.players.map(_.hand.size) shouldBe List(2, 2)
      }
      "show 3 board cards after flop" in {
        controller.flop()
        controller.table.board.size shouldBe 3
      }
      "show 4 board cards after turn" in {
        controller.turn()
        controller.table.board.size shouldBe 4
      }
      "show 5 board cards after river" in {
        controller.river()
        controller.table.board.size shouldBe 5
      }
      "notify its observers after every step" in {
        observer.updates shouldBe 4
      }
    }
  }
}