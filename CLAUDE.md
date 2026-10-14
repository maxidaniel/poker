# CLAUDE.md – Poker (Texas Hold'em)

## Projekt
- Software-Engineering-Projekt an der HTWG Konstanz (Max, GitHub: maxidaniel/poker)
- Ziel: Texas-Hold'em-Lernspiel – besser werden im Poker, Spielzüge simulieren, Chips
- Scala 3.3.1, sbt, Tests mit ScalaTest
- Paket: `de.htwg.se.poker`

## Befehle
- Kompilieren: `sbt compile`
- Starten: `sbt run`
- Tests: `sbt test`

## Arbeitsweise (nach den Karpathy-Regeln)
1. **Erst denken, dann coden:** Annahmen nennen. Bei Unklarheit nachfragen statt raten.
2. **Einfach halten:** Nur bauen, was verlangt ist. Keine Abstraktionen auf Vorrat.
3. **Gezielt ändern:** Nur Code anfassen, der zur Aufgabe gehört. Bestehenden Stil beibehalten.
4. **Ziel mit Test:** Jede neue Funktion bekommt einen ScalaTest. Fertig heißt: `sbt test` ist grün.

## Hinweise für Erklärungen
- Max lernt mit dem Projekt: Änderungen kurz auf Deutsch erklären, Schritt für Schritt.
- Immutable Datenstrukturen und `case class` bevorzugen (funktionaler Scala-Stil).
