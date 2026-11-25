package de.htwg.se.poker.util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class UndoManagerSpec extends AnyWordSpec with Matchers {
  class Counter {
    var value = 0
  }

  class IncrCommand(counter: Counter) extends Command {
    override def doStep(): Unit = counter.value = counter.value + 1
    override def undoStep(): Unit = counter.value = counter.value - 1
    override def redoStep(): Unit = counter.value = counter.value + 1
  }

  "An UndoManager" should {
    "do a step" in {
      val counter = new Counter
      val manager = new UndoManager
      manager.doStep(new IncrCommand(counter))
      counter.value shouldBe 1
    }
    "undo and redo a step" in {
      val counter = new Counter
      val manager = new UndoManager
      manager.doStep(new IncrCommand(counter))
      manager.undoStep()
      counter.value shouldBe 0
      manager.redoStep()
      counter.value shouldBe 1
    }
    "undo several steps in reverse order" in {
      val counter = new Counter
      val manager = new UndoManager
      manager.doStep(new IncrCommand(counter))
      manager.doStep(new IncrCommand(counter))
      manager.undoStep()
      counter.value shouldBe 1
      manager.undoStep()
      counter.value shouldBe 0
    }
    "do nothing on undo or redo when there is nothing to undo or redo" in {
      val counter = new Counter
      val manager = new UndoManager
      manager.undoStep()
      manager.redoStep()
      counter.value shouldBe 0
    }
    "forget the redo steps after a new step" in {
      val counter = new Counter
      val manager = new UndoManager
      manager.doStep(new IncrCommand(counter))
      manager.undoStep()
      manager.doStep(new IncrCommand(counter))
      manager.redoStep()
      counter.value shouldBe 1
    }
  }
}