package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{Assertion, FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  class ExpeditionsAreEqualMatcher(right: MarsRoverExpedition) extends Matcher[MarsRoverExpedition] {
    def apply (left : MarsRoverExpedition) : MatchResult =
      MatchResult(left.direction() === right.direction() && left.location() === right.location(),
        "The compared expeditions are not equal", "The compared expeditions are equal" )
  }
  def beTheSameAs(right: MarsRoverExpedition) = new ExpeditionsAreEqualMatcher(right)

  "A Mars rover expedition" should " have an initial position and direction" in {
    val marsRoverExpedition = new MarsRoverExpedition(Coordinate(1, 1), Direction.NORTH)
    marsRoverExpedition should beTheSameAs(new MarsRoverExpedition(Coordinate(1, 1), Direction.NORTH))
  }

  "A Mars rover " should " be able to move forward" in {
    def checkIfTheRoverIsFacing(direction: Direction): Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), direction)
      expedition.move("f")
      direction match {
        case Direction.NORTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 3), Direction.NORTH))
        case Direction.SOUTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 1), Direction.SOUTH))
        case Direction.EAST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(3, 2), Direction.EAST))
        case Direction.WEST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(1, 2), Direction.WEST))
      }
    }

    List(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST).foreach(checkIfTheRoverIsFacing)
  }

  "A Mars rover " should " be able to move backwards" in {
    def checkIfTheRoverIsFacing(direction: Direction): Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), direction)
      expedition.move("b")
      direction match {
        case Direction.NORTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 1), Direction.NORTH))
        case Direction.SOUTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 3), Direction.SOUTH))
        case Direction.EAST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(1, 2), Direction.EAST))
        case Direction.WEST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(3, 2), Direction.WEST))
      }
    }

    List(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST).foreach(checkIfTheRoverIsFacing)
  }


  "A Mars rover " should " be able to move left" in {
    def checkIfTheRoverIsFacing(direction: Direction): Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), direction)
      expedition.turn("l")
      direction match {
        case Direction.NORTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.WEST))
        case Direction.SOUTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.EAST))
        case Direction.EAST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.NORTH))
        case Direction.WEST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.SOUTH))
      }
    }

    List(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST).foreach(checkIfTheRoverIsFacing)
  }



}
