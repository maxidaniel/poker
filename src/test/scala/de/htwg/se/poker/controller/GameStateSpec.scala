package de.htwg.se.poker.controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.poker.model.Table

class GameStateSpec extends AnyWordSpec with Matchers {
  val preFlop: Table = Table(List("Alex", "Max")).newRound
  val afterFlop: Table = preFlop.flop
  val afterTurn: Table = afterFlop.turn
  val afterRiver: Table = afterTurn.river

  "The WaitingState" should {
    "ignore flop, turn and river" in {
      WaitingState.flop(preFlop) shouldBe ((preFlop, WaitingState))
      WaitingState.turn(preFlop) shouldBe ((preFlop, WaitingState))
      WaitingState.river(preFlop) shouldBe ((preFlop, WaitingState))
    }
  }
  "The PreFlopState" should {
    "deal the flop and switch to FlopState" in {
      PreFlopState.flop(preFlop) shouldBe ((afterFlop, FlopState))
    }
    "ignore turn and river" in {
      PreFlopState.turn(preFlop) shouldBe ((preFlop, PreFlopState))
      PreFlopState.river(preFlop) shouldBe ((preFlop, PreFlopState))
    }
  }
  "The FlopState" should {
    "deal the turn and switch to TurnState" in {
      FlopState.turn(afterFlop) shouldBe ((afterTurn, TurnState))
    }
    "ignore flop and river" in {
      FlopState.flop(afterFlop) shouldBe ((afterFlop, FlopState))
      FlopState.river(afterFlop) shouldBe ((afterFlop, FlopState))
    }
  }
  "The TurnState" should {
    "deal the river and switch to RiverState" in {
      TurnState.river(afterTurn) shouldBe ((afterRiver, RiverState))
    }
    "ignore flop and turn" in {
      TurnState.flop(afterTurn) shouldBe ((afterTurn, TurnState))
      TurnState.turn(afterTurn) shouldBe ((afterTurn, TurnState))
    }
  }
  "The RiverState" should {
    "ignore flop, turn and river" in {
      RiverState.flop(afterRiver) shouldBe ((afterRiver, RiverState))
      RiverState.turn(afterRiver) shouldBe ((afterRiver, RiverState))
      RiverState.river(afterRiver) shouldBe ((afterRiver, RiverState))
    }
  }
}