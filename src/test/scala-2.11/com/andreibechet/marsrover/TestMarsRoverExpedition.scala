package com.andreibechet.marsrover

import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{Assertion, FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  class CoordinatesAreEqual(right: Coordinate) extends Matcher[Coordinate]{
    def apply (left : Coordinate) : MatchResult =
      MatchResult(left === right, "The compared coordinates are not equal", "The compared coordinates are equal" )
  }
  def equalTheCoordinate(right: Coordinate) = new CoordinatesAreEqual(right)

  "A Mars rover expedition" should " have an initial position and direction" in {
    val marsRoverExpedition = new MarsRoverExpedition(Coordinate(1, 1), Direction.NORTH)
    marsRoverExpedition.location() should equalTheCoordinate (Coordinate(1, 1))
    marsRoverExpedition.direction() should equal (Direction.NORTH)
  }

  "A Mars rovers position" should " increase by one in the existing direction when receiving the command f" in {
    def checkIfTheRoverIsFacingNorth: Assertion = {
      val northHeadingMarsRoverExpedition = new MarsRoverExpedition(Coordinate(2, 2), Direction.NORTH)
      northHeadingMarsRoverExpedition.move("f")
      northHeadingMarsRoverExpedition.location() should equalTheCoordinate(Coordinate(2, 3))
      northHeadingMarsRoverExpedition.direction() should equal(Direction.NORTH)
    }

    def checkIfTheRoverIsFacingSouth: Assertion = {
      val southHeadingMarsRoverExpedition = new MarsRoverExpedition(Coordinate(2, 2), Direction.SOUTH)
      southHeadingMarsRoverExpedition.move("f")
      southHeadingMarsRoverExpedition.location() should equalTheCoordinate(Coordinate(2, 1))
      southHeadingMarsRoverExpedition.direction() should equal(Direction.SOUTH)
    }

    def checkIfTheRoverIsFacingEast: Assertion = {
      val eastHeadingMarsRoverExpedition = new MarsRoverExpedition(Coordinate(2, 2), Direction.EAST)
      eastHeadingMarsRoverExpedition.move("f")
      eastHeadingMarsRoverExpedition.location() should equalTheCoordinate(Coordinate(3, 2))
      eastHeadingMarsRoverExpedition.direction() should equal(Direction.EAST)
    }

    def checkIfTheRoverIsFacingWest: Assertion = {
      val westHeadingMarsRoverExpedition = new MarsRoverExpedition(Coordinate(2, 2), Direction.WEST)
      westHeadingMarsRoverExpedition.move("f")
      westHeadingMarsRoverExpedition.location() should equalTheCoordinate(Coordinate(1, 2))
      westHeadingMarsRoverExpedition.direction() should equal(Direction.WEST)
    }

    checkIfTheRoverIsFacingNorth

    checkIfTheRoverIsFacingSouth

    checkIfTheRoverIsFacingEast

    checkIfTheRoverIsFacingWest
  }




}
