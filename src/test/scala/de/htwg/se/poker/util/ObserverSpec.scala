package de.htwg.se.poker.util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ObserverSpec extends AnyWordSpec with Matchers {
  class TestObserver extends Observer {
    var updates: Int = 0
    override def update(): Unit = updates += 1
  }

  "An Observable" when {
    "an observer is added" should {
      val observable = new Observable
      val observer = new TestObserver
      observable.add(observer)
      "have one subscriber" in {
        observable.subscribers should contain only observer
      }
      "notify the observer" in {
        observable.notifyObservers()
        observer.updates shouldBe 1
      }
    }
    "an observer is removed" should {
      val observable = new Observable
      val observer = new TestObserver
      observable.add(observer)
      observable.remove(observer)
      "have no subscribers" in {
        observable.subscribers shouldBe empty
      }
      "not notify the observer anymore" in {
        observable.notifyObservers()
        observer.updates shouldBe 0
      }
    }
  }
}