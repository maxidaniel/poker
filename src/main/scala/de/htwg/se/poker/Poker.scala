package de.htwg.se.poker

import scala.io.StdIn.readLine
import de.htwg.se.poker.aview.Tui
import de.htwg.se.poker.model.Table

object Poker {
  def main(args: Array[String]): Unit = run(Table("Alex Max".split(" ").toList))

  def run(table: Table): Unit = {
    println(table)
    print(Tui.prompt)
    val input = readLine()
    if (input != null && input != "q") run(Tui.process(input, table))
  }
}