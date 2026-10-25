package de.htwg.se.poker.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "new without cards" should {
      val player = Player("Alex", 1000, Nil)
      "have a name" in {
        player.name shouldBe "Alex"
      }
      "have 1000 chips" in {
        player.chips shouldBe 1000
      }
      "have an empty hand" in {
        player.hand shouldBe empty
      }
      "print a dash instead of cards" in {
        player.toString shouldBe "Alex (1000 Chips): -"
      }
    }
    "holding two cards" should {
      val player = Player("Max", 500, List(Card("Pik", 14), Card("Herz", 13)))
      "print both cards" in {
        player.toString shouldBe "Max (500 Chips): 14 of Pik, 13 of Herz"
      }
    }
  }
}