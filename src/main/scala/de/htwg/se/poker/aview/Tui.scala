package de.htwg.se.poker.aview

import de.htwg.se.poker.controller.Controller
import de.htwg.se.poker.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)

  val prompt: String = "Befehl (n = neue Runde, f = Flop, t = Turn, r = River, u = Undo, y = Redo, q = Ende): "

  def processInput(input: String): Unit = input match {
    case "n" => controller.newRound()
    case "f" => controller.flop()
    case "t" => controller.turn()
    case "r" => controller.river()
    case "u" => controller.undo()
    case "y" => controller.redo()
    case _   => println(s"Unbekannter Befehl: $input")
  }

  override def update(): Unit = println(controller.tableToString)
}