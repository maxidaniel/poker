package de.htwg.se.poker.model

case class Card(suit: String, value: Int) {
  override def toString: String = s"$value of $suit"
}