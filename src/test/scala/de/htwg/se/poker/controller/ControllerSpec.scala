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
      "wait for a new round" in {
        controller.state shouldBe WaitingState
      }
    }
    "asked for flop, turn or river before a new round" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      "ignore it and keep the board empty" in {
        controller.flop()
        controller.turn()
        controller.river()
        controller.table.board shouldBe empty
        controller.state shouldBe WaitingState
      }
    }
    "playing a full round" should {
      val controller = new Controller(Table(List("Alex", "Max")))
      val observer = new TestObserver
      controller.add(observer)
      "deal two cards to every player on newRound" in {
        controller.newRound()
        controller.table.players.map(_.hand.size) shouldBe List(2, 2)
        controller.state shouldBe PreFlopState
      }
      "show 3 board cards after flop" in {
        controller.flop()
        controller.table.board.size shouldBe 3
        controller.state shouldBe FlopState
      }
      "show 4 board cards after turn" in {
        controller.turn()
        controller.table.board.size shouldBe 4
        controller.state shouldBe TurnState
      }
      "show 5 board cards after river" in {
        controller.river()
        controller.table.board.size shouldBe 5
        controller.state shouldBe RiverState
      }
      "notify its observers after every step" in {
        observer.updates shouldBe 4
      }
    }
  }
}