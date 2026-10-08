// 1. Zuerst die Klasse definieren
case class Card(suit: String, value: Int) {
  override def toString: String = s"$value of $suit"
}

// 2. Dann die Listen
val suits = List("Pik", "Herz", "Karo", "Kreuz")
val values = List(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)

// 3. Dann das Deck generieren
val deck = for {
  s <- suits
  v <- values
} yield Card(s, v)

// 4. Testen
deck.length
deck.take(5)