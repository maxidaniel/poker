package de.htwg.se.poker.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class CardSpec extends AnyWordSpec with Matchers {
  "A Card" when {
    "created as 14 of Pik" should {
      val card = Card("Pik", 14)
      "have suit Pik" in {
        card.suit shouldBe "Pik"
      }
      "have value 14" in {
        card.value shouldBe 14
      }
      "print as '14 of Pik'" in {
        card.toString shouldBe "14 of Pik"
      }
    }
  }
}