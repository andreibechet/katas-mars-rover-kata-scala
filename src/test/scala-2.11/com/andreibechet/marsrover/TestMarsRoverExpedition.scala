package com.andreibechet.marsrover

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

  "A Mars rovers position" should " increase by one in the existing direction when receiving the command f" in {
    def checkIfTheRoverIsFacingNorth: Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), Direction.NORTH)
      expedition.move("f")
      expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 3), Direction.NORTH))
    }

    def checkIfTheRoverIsFacingSouth: Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), Direction.SOUTH)
      expedition.move("f")
      expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(2, 1), Direction.SOUTH))
    }

    def checkIfTheRoverIsFacingEast: Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), Direction.EAST)
      expedition.move("f")
      expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(3, 2), Direction.EAST))
    }

    def checkIfTheRoverIsFacingWest: Assertion = {
      val expedition = new MarsRoverExpedition(Coordinate(2, 2), Direction.WEST)
      expedition.move("f")
      expedition should beTheSameAs(new MarsRoverExpedition(Coordinate(1, 2), Direction.WEST))
    }

    checkIfTheRoverIsFacingNorth

    checkIfTheRoverIsFacingSouth

    checkIfTheRoverIsFacingEast

    checkIfTheRoverIsFacingWest
  }




}
