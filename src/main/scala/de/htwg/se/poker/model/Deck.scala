package de.htwg.se.poker.model

import scala.util.Random

case class Deck(cards: List[Card]) {
  def size: Int = cards.size

  def deal(n: Int): (List[Card], Deck) =
    (cards.take(n), Deck(cards.drop(n)))

  def shuffle: Deck = Deck(Random.shuffle(cards))
}

object Deck {
  val suits: List[String] = List("Pik", "Herz", "Karo", "Kreuz")
  val values: List[Int] = (2 to 14).toList

  def apply(): Deck = Deck(for {
    s <- suits
    v <- values
  } yield Card(s, v))
}