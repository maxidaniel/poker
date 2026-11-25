package de.htwg.se.poker.aview

import de.htwg.se.poker.controller.Controller
import de.htwg.se.poker.util.Observer
import scala.util.{Success, Try}

class Tui(controller: Controller) extends Observer {
  controller.add(this)

  val prompt: String = "Befehl (n = neue Runde, f = Flop, t = Turn, r = River, b <Betrag> = Setzen, u = Undo, y = Redo, q = Ende): "

  def processInput(input: String): Unit = input match {
    case "n" => controller.newRound()
    case "f" => controller.flop()
    case "t" => controller.turn()
    case "r" => controller.river()
    case "u" => controller.undo()
    case "y" => controller.redo()
    case s"b $amount" => bet(amount)
    case _   => println(s"Unbekannter Befehl: $input")
  }

  private def bet(amount: String): Unit = Try(amount.toInt) match {
    case Success(value) if value > 0 => controller.bet(value)
    case _ => println(s"Ungültiger Betrag: $amount")
  }

  override def update(): Unit = println(controller.tableToString)
}