package de.htwg.se.poker.model

case class Player(name: String, chips: Int, hand: List[Card]) {
  def bet(amount: Int): Player = copy(chips = chips - amount.min(chips))
  override def toString: String = {
    val handText = if (hand.isEmpty) "-" else hand.mkString(", ")
    s"$name ($chips Chips): $handText"
  }
}