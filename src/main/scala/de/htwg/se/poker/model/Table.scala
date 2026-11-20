package de.htwg.se.poker.model

case class Table(players: List[Player], deck: Deck, board: List[Card], pot: Int) {
  def newRound: Table = newRound(RandomShuffle)

  def newRound(strategy: ShuffleStrategy): Table = {
    val shuffled = Deck().shuffle(strategy)
    val seats = players.indices.toList
    val dealt = seats.map(i => players(i).copy(hand = shuffled.cards.slice(i * 2, i * 2 + 2)))
    Table(dealt, Deck(shuffled.cards.drop(players.size * 2)), Nil, pot)
  }

  def flop: Table = Flop.deal(this)
  def turn: Table = Turn.deal(this)
  def river: Table = River.deal(this)

  def reveal(n: Int): Table = {
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