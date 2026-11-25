package de.htwg.se.poker.controller

import de.htwg.se.poker.model.Table
import de.htwg.se.poker.util.{Observable, UndoManager}

class Controller(var table: Table) extends Observable {
  var state: GameState = WaitingState
  private val undoManager = new UndoManager

  def newRound(): Unit = doStep((table, _) => (table.newRound, PreFlopState))

  def flop(): Unit = doStep((table, state) => state.flop(table))

  def turn(): Unit = doStep((table, state) => state.turn(table))

  def river(): Unit = doStep((table, state) => state.river(table))

  def undo(): Unit = {
    undoManager.undoStep()
    notifyObservers()
  }

  def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers()
  }

  private def doStep(step: (Table, GameState) => (Table, GameState)): Unit = {
    undoManager.doStep(new StepCommand(this, step))
    notifyObservers()
  }

  def tableToString: String = table.toString
}