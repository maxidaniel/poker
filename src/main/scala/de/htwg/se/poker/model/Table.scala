package de.htwg.se.poker.model

case class Table(players: List[Player], deck: Deck, board: List[Card], pot: Int) {
  def newRound: Table = {
    val shuffled = Deck().shuffle
    val seats = players.indices.toList
    val dealt = seats.map(i => players(i).copy(hand = shuffled.cards.slice(i * 2, i * 2 + 2)))
    Table(dealt, Deck(shuffled.cards.drop(players.size * 2)), Nil, pot)
  }

  def flop: Table = if (board.isEmpty) reveal(3) else this
  def turn: Table = if (board.size == 3) reveal(1) else this
  def river: Table = if (board.size == 4) reveal(1) else this

  private def reveal(n: Int): Table = {
    val dealt = deck.deal(n)
    copy(board = board ++ dealt._1, deck = dealt._2)
  }

  override def toString: String = {
    val boardText = if (board.isEmpty) "-" else board.mkString(", ")
    val header = s"Pot: $pot | Board: $boardText"
    val playerLines = players.map(p => p.toString)
    playerLines.prepended(header).mkString("\n")
  }
}

object Table {
  def apply(names: List[String]): Table =
    Table(names.map(name => Player(name, 1000, Nil)), Deck(), Nil, 0)
}