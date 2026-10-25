package de.htwg.se.poker.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class TableSpec extends AnyWordSpec with Matchers {
  "A Table" when {
    "created with two names" should {
      val table = Table(List("Alex", "Max"))
      "have two players with 1000 chips and no cards" in {
        table.players.map(_.name) shouldBe List("Alex", "Max")
        table.players.map(_.chips) shouldBe List(1000, 1000)
        table.players.forall(_.hand.isEmpty) shouldBe true
      }
      "have a full deck, an empty board and an empty pot" in {
        table.deck.size shouldBe 52
        table.board shouldBe empty
        table.pot shouldBe 0
      }
      "print the pot, the board and the players" in {
        table.toString shouldBe
          "Pot: 0 | Board: -\nAlex (1000 Chips): -\nMax (1000 Chips): -"
      }
    }
    "a new round starts" should {
      val table = Table(List("Alex", "Max")).newRound
      "give every player two cards" in {
        table.players.map(_.hand.size) shouldBe List(2, 2)
      }
      "give different cards to the players" in {
        table.players.flatMap(_.hand).distinct.size shouldBe 4
      }
      "leave 48 cards in the deck" in {
        table.deck.size shouldBe 48
      }
      "not have any dealt card left in the deck" in {
        table.deck.cards should contain noElementsOf table.players.flatMap(_.hand)
      }
    }
    "playing flop, turn and river" should {
      val start = Table(List("Alex", "Max")).newRound
      val flop = start.flop
      val turn = flop.turn
      val river = turn.river
      "show 3 cards after the flop" in {
        flop.board.size shouldBe 3
        flop.deck.size shouldBe 45
      }
      "show 4 cards after the turn" in {
        turn.board.size shouldBe 4
      }
      "show 5 cards after the river" in {
        river.board.size shouldBe 5
        river.deck.size shouldBe 43
      }
      "print the board cards" in {
        river.toString should startWith(s"Pot: 0 | Board: ${river.board.mkString(", ")}")
      }
    }
    "a step comes in the wrong order" should {
      val start = Table(List("Alex", "Max")).newRound
      "not deal a second flop" in {
        start.flop.flop.board.size shouldBe 3
      }
      "not deal a turn before the flop" in {
        start.turn shouldBe start
      }
      "not deal a river before the turn" in {
        start.river shouldBe start
      }
    }
  }
}