package de.htwg.se.poker.controller

import de.htwg.se.poker.model.Table

trait GameState {
  def flop(table: Table): (Table, GameState) = (table, this)
  def turn(table: Table): (Table, GameState) = (table, this)
  def river(table: Table): (Table, GameState) = (table, this)
}

object WaitingState extends GameState

object PreFlopState extends GameState {
  override def flop(table: Table): (Table, GameState) = (table.flop, FlopState)
}

object FlopState extends GameState {
  override def turn(table: Table): (Table, GameState) = (table.turn, TurnState)
}

object TurnState extends GameState {
  override def river(table: Table): (Table, GameState) = (table.river, RiverState)
}

object RiverState extends GameState