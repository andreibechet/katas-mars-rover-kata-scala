package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{Assertion, FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  class MarsRoversAreEqualMatcher(right: MarsRover) extends Matcher[MarsRover] {
    def apply (left : MarsRover) : MatchResult =
      MatchResult(left.direction === right.direction && left.position === right.position,
        "The compared rovers are not equal", "The compared rovers are equal" )
  }
  def beTheSameAs(right: MarsRover) = new MarsRoversAreEqualMatcher(right)

  val ALL_DIRECTIONS: List[Direction.Value] = List(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)

  "A Mars rover " should " have an initial position and direction" in {
    val marsRover = new MarsRover(Coordinate(1, 1), Direction.NORTH)
    marsRover should beTheSameAs(new MarsRover(Coordinate(1, 1), Direction.NORTH))
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
    val marsRover = new MarsRover(Coordinate(0, 0), Direction.NORTH)
    marsRover.execute(Array('f', 'r', 'f', 'l', 'f', 'b', 'r', 'f'))
    marsRover should beTheSameAs(new MarsRover(Coordinate(2, 1), Direction.EAST))
  }

  "A grid " should " wrap the edges around when a rover moves on it" in {
    val marsRover = new MarsRover(Coordinate(2, 0), Direction.EAST, new Grid(3, 3))
    marsRover.execute(Array('f'))
    marsRover should beTheSameAs(new MarsRover(Coordinate(0, 0), Direction.EAST, new Grid(3, 3)))
  }

}
