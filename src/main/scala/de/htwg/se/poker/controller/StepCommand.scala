package de.htwg.se.poker.controller

import de.htwg.se.poker.model.Table
import de.htwg.se.poker.util.Command

class StepCommand(controller: Controller, step: (Table, GameState) => (Table, GameState)) extends Command {
  private var before: (Table, GameState) = (controller.table, controller.state)
  private var after: (Table, GameState) = before

  override def doStep(): Unit = {
    before = (controller.table, controller.state)
    after = step(controller.table, controller.state)
    set(after)
  }

  override def undoStep(): Unit = set(before)

  override def redoStep(): Unit = set(after)

  private def set(snapshot: (Table, GameState)): Unit = {
    controller.table = snapshot._1
    controller.state = snapshot._2
  }
}