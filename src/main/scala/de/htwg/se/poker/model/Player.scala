package de.htwg.se.poker.model

case class Player(name: String, chips: Int, hand: List[Card]) {
  override def toString: String = {
    val handText = if (hand.isEmpty) "-" else hand.mkString(", ")
    s"$name ($chips Chips): $handText"
  }
}