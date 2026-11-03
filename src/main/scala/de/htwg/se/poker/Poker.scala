package de.htwg.se.poker

import scala.io.StdIn.readLine
import de.htwg.se.poker.aview.Tui
import de.htwg.se.poker.controller.Controller
import de.htwg.se.poker.model.Table

object Poker {
  def main(args: Array[String]): Unit = {
    val controller = new Controller(Table("Alex Max".split(" ").toList))
    val tui = new Tui(controller)
    controller.notifyObservers()
    run(tui)
  }

  def run(tui: Tui): Unit = {
    print(tui.prompt)
    val input = readLine()
    if (input != null && input != "q") {
      tui.processInput(input)
      run(tui)
    }
  }
}