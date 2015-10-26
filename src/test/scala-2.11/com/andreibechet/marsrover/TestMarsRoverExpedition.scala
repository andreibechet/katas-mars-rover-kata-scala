package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{Assertion, FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  class ExpeditionsAreEqualMatcher(right: MarsRover) extends Matcher[MarsRover] {
    def apply (left : MarsRover) : MatchResult =
      MatchResult(left.direction === right.direction && left.position === right.position,
        "The compared expeditions are not equal", "The compared expeditions are equal" )
  }
  def beTheSameAs(right: MarsRover) = new ExpeditionsAreEqualMatcher(right)

  val ALL_DIRECTIONS: List[Direction.Value] = List(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)

  "A Mars rover expedition" should " have an initial position and direction" in {
    val marsRoverExpedition = new MarsRover(Coordinate(1, 1), Direction.NORTH)
    marsRoverExpedition should beTheSameAs(new MarsRover(Coordinate(1, 1), Direction.NORTH))
  }

  "A Mars rover " should " be able to move forward" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val marsRover = new MarsRover(Coordinate(2, 2), direction)
      marsRover.moveForward()
      direction match {
        case Direction.NORTH => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 3), Direction.NORTH))
        case Direction.SOUTH => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 1), Direction.SOUTH))
        case Direction.EAST => marsRover should beTheSameAs(new MarsRover(Coordinate(3, 2), Direction.EAST))
        case Direction.WEST => marsRover should beTheSameAs(new MarsRover(Coordinate(1, 2), Direction.WEST))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }


  "A Mars rover " should " be able to move backwards" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val marsRover = new MarsRover(Coordinate(2, 2), direction)
      marsRover.moveBackward()
      direction match {
        case Direction.NORTH => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 1), Direction.NORTH))
        case Direction.SOUTH => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 3), Direction.SOUTH))
        case Direction.EAST => marsRover should beTheSameAs(new MarsRover(Coordinate(1, 2), Direction.EAST))
        case Direction.WEST => marsRover should beTheSameAs(new MarsRover(Coordinate(3, 2), Direction.WEST))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }


  "A Mars rover " should " be able to move left" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val marsRover = new MarsRover(Coordinate(2, 2), direction)
      marsRover.turnLeft()
      direction match {
        case Direction.NORTH => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 2), Direction.WEST))
        case Direction.SOUTH => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 2), Direction.EAST))
        case Direction.EAST => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 2), Direction.NORTH))
        case Direction.WEST => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 2), Direction.SOUTH))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }

  "A Mars rover " should " be able to move right" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val marsRover = new MarsRover(Coordinate(2, 2), direction)
      marsRover.turnRight()
      direction match {
        case Direction.NORTH => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 2), Direction.EAST))
        case Direction.SOUTH => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 2), Direction.WEST))
        case Direction.EAST => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 2), Direction.SOUTH))
        case Direction.WEST => marsRover should beTheSameAs(new MarsRover(Coordinate(2, 2), Direction.NORTH))
      }
    }

   ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }

  "A Mars rover " should " execute a char array of commands: f, b, l, r" in {
    val expedition = new MarsRover(Coordinate(0, 0), Direction.NORTH)
    expedition.execute(Array('f', 'r', 'f', 'l', 'f', 'b', 'r', 'f'))
    expedition should beTheSameAs(new MarsRover(Coordinate(2, 1), Direction.EAST))
  }

}
