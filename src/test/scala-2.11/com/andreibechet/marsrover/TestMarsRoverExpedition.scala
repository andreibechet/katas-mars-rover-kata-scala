package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{Assertion, FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  class ExpeditionsAreEqualMatcher(right: MarsRoverExpedition) extends Matcher[MarsRoverExpedition] {
    def apply (left : MarsRoverExpedition) : MatchResult =
      MatchResult(left.direction === right.direction && left.position === right.position,
        "The compared expeditions are not equal", "The compared expeditions are equal" )
  }
  def beTheSameAs(right: MarsRoverExpedition) = new ExpeditionsAreEqualMatcher(right)

  val ALL_DIRECTIONS: List[Direction.Value] = List(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)

  "A Mars rover expedition" should " have an initial position and direction" in {
    val marsRoverExpedition = new MarsRoverExpedition(Coordinate(1, 1), Direction.NORTH)
    marsRoverExpedition should beTheSameAs(new MarsRoverExpedition(Coordinate(1, 1), Direction.NORTH))
  }

  "A Mars rover " should " be able to move forward" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), direction)
      expedition.moveForward()
      direction match {
        case Direction.NORTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 3), Direction.NORTH))
        case Direction.SOUTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 1), Direction.SOUTH))
        case Direction.EAST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(3, 2), Direction.EAST))
        case Direction.WEST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(1, 2), Direction.WEST))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }


  "A Mars rover " should " be able to move backwards" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), direction)
      expedition.moveBackward()
      direction match {
        case Direction.NORTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 1), Direction.NORTH))
        case Direction.SOUTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 3), Direction.SOUTH))
        case Direction.EAST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(1, 2), Direction.EAST))
        case Direction.WEST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(3, 2), Direction.WEST))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }


  "A Mars rover " should " be able to move left" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), direction)
      expedition.turnLeft()
      direction match {
        case Direction.NORTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.WEST))
        case Direction.SOUTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.EAST))
        case Direction.EAST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.NORTH))
        case Direction.WEST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.SOUTH))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }

  "A Mars rover " should " be able to move right" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), direction)
      expedition.turnRight()
      direction match {
        case Direction.NORTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.EAST))
        case Direction.SOUTH => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.WEST))
        case Direction.EAST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.SOUTH))
        case Direction.WEST => expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 2), Direction.NORTH))
      }
    }

   ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }

  "A Mars rover " should " execute a char array of commands: f, b, l, r" in {
    val expedition = new MarsRoverExpedition(Coordinate(0, 0), Direction.NORTH)
    expedition.execute(Array('f', 'r', 'f', 'l', 'f', 'b', 'r', 'f'))
    expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 1), Direction.EAST))
  }


}
