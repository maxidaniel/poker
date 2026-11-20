package de.htwg.se.poker.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class StreetSpec extends AnyWordSpec with Matchers {
  val preFlop = Table(List("Alex", "Max")).newRound(NoShuffle)
  val afterFlop = Flop.deal(preFlop)
  val afterTurn = Turn.deal(afterFlop)
  val afterRiver = River.deal(afterTurn)

  "The Flop" should {
    "reveal 3 cards on an empty board" in {
      afterFlop.board shouldBe preFlop.deck.cards.take(3)
    }
    "do nothing if the board is not empty" in {
      Flop.deal(afterFlop) shouldBe afterFlop
    }
  }

  "The Turn" should {
    "reveal 1 card after the flop" in {
      afterTurn.board shouldBe preFlop.deck.cards.take(4)
    }
    "do nothing before the flop" in {
      Turn.deal(preFlop) shouldBe preFlop
    }
  }

  "The River" should {
    "reveal 1 card after the turn" in {
      afterRiver.board shouldBe preFlop.deck.cards.take(5)
    }
    "do nothing before the turn" in {
      River.deal(afterFlop) shouldBe afterFlop
    }
  }
}