package de.htwg.se.poker.controller

import de.htwg.se.poker.model.Table
import de.htwg.se.poker.util.Observable

class Controller(var table: Table) extends Observable {
  def newRound(): Unit = {
    table = table.newRound
    notifyObservers()
  }

  def flop(): Unit = {
    table = table.flop
    notifyObservers()
  }

  def turn(): Unit = {
    table = table.turn
    notifyObservers()
  }

  def river(): Unit = {
    table = table.river
    notifyObservers()
  }

  def tableToString: String = table.toString
}