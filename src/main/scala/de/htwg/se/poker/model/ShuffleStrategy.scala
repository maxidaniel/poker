package de.htwg.se.poker.model

import scala.util.Random

trait ShuffleStrategy {
  def shuffle(cards: List[Card]): List[Card]
}

object RandomShuffle extends ShuffleStrategy {
  def shuffle(cards: List[Card]): List[Card] = Random.shuffle(cards)
}

object NoShuffle extends ShuffleStrategy {
  def shuffle(cards: List[Card]): List[Card] = cards
}