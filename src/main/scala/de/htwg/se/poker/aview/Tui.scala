package de.htwg.se.poker.aview

import de.htwg.se.poker.model.Table

object Tui {
  val prompt: String = "Befehl (n = neue Runde, f = Flop, t = Turn, r = River, q = Ende): "

  def process(input: String, table: Table): Table = input match {
    case "n" => table.newRound
    case "f" => table.flop
    case "t" => table.turn
    case "r" => table.river
    case _   => table
  }
}