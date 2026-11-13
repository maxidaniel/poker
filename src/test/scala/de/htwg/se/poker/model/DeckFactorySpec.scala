package de.htwg.se.poker.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class DeckFactorySpec extends AnyWordSpec with Matchers {
  "The DeckFactory" when {
    "asked for a standard deck" should {
      "create the normal 52 card deck" in {
        DeckFactory("standard") shouldBe Deck()
      }
    }
    "asked for a short deck" should {
      val deck = DeckFactory("short")
      "create a deck with 36 cards" in {
        deck.size shouldBe 36
      }
      "contain no card below 6" in {
        deck.cards.map(_.value).min shouldBe 6
      }
    }
    "asked for an unknown kind" should {
      "create the normal 52 card deck" in {
        DeckFactory("xyz") shouldBe Deck()
      }
    }
  }
}