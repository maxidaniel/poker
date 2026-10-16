package de.htwg.se.poker.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class DeckSpec extends AnyWordSpec with Matchers {
  "A Deck" when {
    "new" should {
      val deck = Deck()
      "contain 52 cards" in {
        deck.size shouldBe 52
      }
      "contain no duplicate cards" in {
        deck.cards.distinct.size shouldBe 52
      }
      "contain 13 cards of each suit" in {
        deck.cards.groupBy(_.suit).values.map(_.size).toSet shouldBe Set(13)
      }
    }
    "dealing 2 cards" should {
      val (hand, rest) = Deck().deal(2)
      "return 2 cards" in {
        hand.size shouldBe 2
      }
      "leave 50 cards in the deck" in {
        rest.size shouldBe 50
      }
      "not leave the dealt cards in the deck" in {
        rest.cards should contain noElementsOf hand
      }
    }
    "shuffled" should {
      val shuffled = Deck().shuffle
      "still contain the same 52 cards" in {
        shuffled.cards should contain theSameElementsAs Deck().cards
      }
    }
  }
}