package de.htwg.se.poker.model

object DeckFactory {
  def apply(kind: String): Deck = kind match {
    case "short" => Deck(Deck().cards.filter(card => card.value >= 6))
    case _       => Deck()
  }
}