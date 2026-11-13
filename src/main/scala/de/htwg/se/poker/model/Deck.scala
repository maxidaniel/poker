package de.htwg.se.poker.model

case class Deck(cards: List[Card]) {
  def size: Int = cards.size

  def deal(n: Int): (List[Card], Deck) =
    (cards.take(n), Deck(cards.drop(n)))

  def shuffle: Deck = shuffle(RandomShuffle)

  def shuffle(strategy: ShuffleStrategy): Deck = Deck(strategy.shuffle(cards))
}

object Deck {
  val suits: List[String] = "Pik Herz Karo Kreuz".split(" ").toList
  val values: List[Int] = (2 to 14).toList

  def apply(): Deck = Deck(for {
    s <- suits
    v <- values
  } yield Card(s, v))
}