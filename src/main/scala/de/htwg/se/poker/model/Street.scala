package de.htwg.se.poker.model

trait Street {
  def boardSizeBefore: Int
  def cardsToReveal: Int

  final def deal(table: Table): Table =
    if (table.board.size == boardSizeBefore) table.reveal(cardsToReveal) else table
}

object Flop extends Street {
  val boardSizeBefore = 0
  val cardsToReveal = 3
}

object Turn extends Street {
  val boardSizeBefore = 3
  val cardsToReveal = 1
}

object River extends Street {
  val boardSizeBefore = 4
  val cardsToReveal = 1
}