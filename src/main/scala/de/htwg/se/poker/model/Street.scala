package de.htwg.se.poker.model

trait Street {
  // Template Method: der Ablauf ist fuer alle Streets gleich und fest
  final def deal(table: Table): Table =
    if (isReady(table)) table.reveal(cardsToReveal) else table

  // Schritte, die jede Street selbst festlegt
  protected def isReady(table: Table): Boolean
  protected def cardsToReveal: Int
}

object Flop extends Street {
  protected def isReady(table: Table): Boolean = table.board.isEmpty
  protected def cardsToReveal: Int = 3
}

object Turn extends Street {
  protected def isReady(table: Table): Boolean = table.board.size == 3
  protected def cardsToReveal: Int = 1
}

object River extends Street {
  protected def isReady(table: Table): Boolean = table.board.size == 4
  protected def cardsToReveal: Int = 1
}