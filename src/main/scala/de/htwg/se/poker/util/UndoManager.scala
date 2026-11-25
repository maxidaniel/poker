package de.htwg.se.poker.util

trait Command {
  def doStep(): Unit
  def undoStep(): Unit
  def redoStep(): Unit
}

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(command: Command): Unit = {
    undoStack = undoStack.prepended(command)
    redoStack = Nil
    command.doStep()
  }

  def undoStep(): Unit = undoStack match {
    case Nil =>
    case head :: stack =>
      head.undoStep()
      undoStack = stack
      redoStack = redoStack.prepended(head)
  }

  def redoStep(): Unit = redoStack match {
    case Nil =>
    case head :: stack =>
      head.redoStep()
      redoStack = stack
      undoStack = undoStack.prepended(head)
  }
}