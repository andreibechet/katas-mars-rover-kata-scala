package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{Assertion, FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  class MarsRoversAreEqualMatcher(right: MarsRover) extends Matcher[MarsRover] {
    def apply (left : MarsRover) : MatchResult =
      MatchResult(left.direction === right.direction && left.position === right.position,
        "The compared rovers are not the same", "The compared rovers are the same" )
  }
  def beTheSameAs(right: MarsRover) = new MarsRoversAreEqualMatcher(right)

  val ALL_DIRECTIONS: List[Direction.Value] = List(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
  val someCoordinate: Coordinate = Coordinate(2, 2)
  val someGrid: Grid = new Grid(3, 3)

  "A Mars rover " should " have an initial position and direction" in {
    val marsRover = new MarsRover(Coordinate(1, 1), Direction.NORTH)
    marsRover should beTheSameAs(new MarsRover(Coordinate(1, 1), Direction.NORTH))
  }

  it should " be able to move forward" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val marsRover = new MarsRover(someCoordinate, direction)
      direction match {
        case Direction.NORTH => marsRover.moveForward() should beTheSameAs(new MarsRover(Coordinate(2, 3), Direction.NORTH))
        case Direction.SOUTH => marsRover.moveForward() should beTheSameAs(new MarsRover(Coordinate(2, 1), Direction.SOUTH))
        case Direction.EAST => marsRover.moveForward() should beTheSameAs(new MarsRover(Coordinate(3, 2), Direction.EAST))
        case Direction.WEST => marsRover.moveForward() should beTheSameAs(new MarsRover(Coordinate(1, 2), Direction.WEST))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }


  it should " be able to move backwards" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val marsRover = new MarsRover(someCoordinate, direction)
      direction match {
        case Direction.NORTH => marsRover.moveBackward() should beTheSameAs(new MarsRover(Coordinate(2, 1), Direction.NORTH))
        case Direction.SOUTH => marsRover.moveBackward() should beTheSameAs(new MarsRover(Coordinate(2, 3), Direction.SOUTH))
        case Direction.EAST => marsRover.moveBackward() should beTheSameAs(new MarsRover(Coordinate(1, 2), Direction.EAST))
        case Direction.WEST => marsRover.moveBackward() should beTheSameAs(new MarsRover(Coordinate(3, 2), Direction.WEST))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }


  it should " be able to move left" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val marsRover = new MarsRover(someCoordinate, direction)
      direction match {
        case Direction.NORTH => marsRover.turnLeft() should beTheSameAs(new MarsRover(someCoordinate, Direction.WEST))
        case Direction.SOUTH => marsRover.turnLeft() should beTheSameAs(new MarsRover(someCoordinate, Direction.EAST))
        case Direction.EAST => marsRover.turnLeft() should beTheSameAs(new MarsRover(someCoordinate, Direction.NORTH))
        case Direction.WEST => marsRover.turnLeft() should beTheSameAs(new MarsRover(someCoordinate, Direction.SOUTH))
      }
    }

    ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }

  it should " be able to move right" in {
    def checkTheRoverIntel(direction: Direction): Assertion = {
      val marsRover = new MarsRover(someCoordinate, direction)
      direction match {
        case Direction.NORTH => marsRover.turnRight() should beTheSameAs(new MarsRover(someCoordinate, Direction.EAST))
        case Direction.SOUTH => marsRover.turnRight() should beTheSameAs(new MarsRover(someCoordinate, Direction.WEST))
        case Direction.EAST => marsRover.turnRight() should beTheSameAs(new MarsRover(someCoordinate, Direction.SOUTH))
        case Direction.WEST => marsRover.turnRight() should beTheSameAs(new MarsRover(someCoordinate, Direction.NORTH))
      }
    }

   ALL_DIRECTIONS.foreach(checkTheRoverIntel)
  }

  it should " execute a char array of commands: f, b, l, r" in {
    val commands: Array[Char] = Array('f', 'r', 'f', 'l', 'f', 'b', 'r', 'f')
    val marsRover = new MarsRover(Coordinate(0, 0), Direction.NORTH)
    marsRover.execute(commands) should beTheSameAs(new MarsRover(Coordinate(2, 1), Direction.EAST))
  }

  "A grid " should " wrap the edges around when a rover moves forwards on it" in {
    val marsRover = new MarsRover(Coordinate(2, 0), Direction.EAST, someGrid)
    marsRover.execute(Array('f')) should beTheSameAs(new MarsRover(Coordinate(0, 0), Direction.EAST, someGrid))
  }

  it should " wrap the edges around when a rover moves backwards on it" in {

    val marsRover = new MarsRover(Coordinate(0, 0), Direction.WEST, someGrid)
    marsRover.execute(Array('f')) should beTheSameAs(new MarsRover(Coordinate(2, 0), Direction.WEST, someGrid))
  }
}