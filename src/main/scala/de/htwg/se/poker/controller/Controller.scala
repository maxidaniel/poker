package de.htwg.se.poker.controller

import de.htwg.se.poker.model.Table
import de.htwg.se.poker.util.Observable

class Controller(var table: Table) extends Observable {
  var state: GameState = WaitingState

  def newRound(): Unit = {
    table = table.newRound
    state = PreFlopState
    notifyObservers()
  }

  def flop(): Unit = change(state.flop(table))

  def turn(): Unit = change(state.turn(table))

  def river(): Unit = change(state.river(table))

  private def change(result: (Table, GameState)): Unit = {
    table = result._1
    state = result._2
    notifyObservers()
  }

  def tableToString: String = table.toString
}