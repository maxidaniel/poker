package de.htwg.se.poker.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ShuffleStrategySpec extends AnyWordSpec with Matchers {
  val cards = Deck().cards

  "NoShuffle" should {
    "keep the cards in the same order" in {
      NoShuffle.shuffle(cards) shouldBe cards
    }
  }

  "RandomShuffle" should {
    "keep the same 52 cards" in {
      RandomShuffle.shuffle(cards) should contain theSameElementsAs cards
    }
  }

  "A Deck" should {
    "use the given strategy to shuffle" in {
      Deck().shuffle(NoShuffle) shouldBe Deck()
    }
  }

  "A Table" should {
    "use the given strategy for a new round" in {
      val table = Table(List("Alex", "Max")).newRound(NoShuffle)
      table.players.head.hand shouldBe cards.take(2)
      table.players(1).hand shouldBe cards.slice(2, 4)
      table.deck.size shouldBe 48
    }
  }
}