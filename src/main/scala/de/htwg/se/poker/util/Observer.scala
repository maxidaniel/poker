package de.htwg.se.poker.util

trait Observer {
  def update(): Unit
}

class Observable {
  var subscribers: Vector[Observer] = Vector.empty
  def add(s: Observer): Unit = subscribers = subscribers :+ s
  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)
  def notifyObservers(): Unit = subscribers.foreach(o => o.update())
}