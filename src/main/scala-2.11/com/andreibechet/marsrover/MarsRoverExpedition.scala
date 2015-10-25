package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class MarsRoverExpedition(aPosition: Coordinate, aDirection: Direction) {

  var position = aPosition
  var currentDirection = aDirection

  def turn(command: String) = {
    // TODO: check that the command can only be "l" or "r"

    if (command == "l") turnLeft()
    else turnRight()
  }

  def turnRight(): Unit = {
    currentDirection = currentDirection match {
      case Direction.NORTH => Direction.EAST
      case Direction.SOUTH => Direction.WEST
      case Direction.EAST => Direction.SOUTH
      case Direction.WEST => Direction.NORTH
    }
  }

  def turnLeft(): Unit = {
    currentDirection = currentDirection match {
      case Direction.NORTH => Direction.WEST
      case Direction.SOUTH => Direction.EAST
      case Direction.EAST => Direction.NORTH
      case Direction.WEST => Direction.SOUTH
    }
  }

  def move(command: String) = {
    // TODO: check that the command can only be "f" or "b"

    if (command == "f") moveForward()
    else moveBackwards()
  }

  def moveBackwards(): Unit = {
    currentDirection match {
      case Direction.NORTH => position = Coordinate(position.x, position.y - 1)
      case Direction.SOUTH => position = Coordinate(position.x, position.y + 1)
      case Direction.EAST => position = Coordinate(position.x - 1, position.y)
      case Direction.WEST => position = Coordinate(position.x + 1, position.y)
    }
  }

  def moveForward(): Unit = {
    currentDirection match {
      case Direction.NORTH => position = Coordinate(position.x, position.y + 1)
      case Direction.SOUTH => position = Coordinate(position.x, position.y - 1)
      case Direction.EAST => position = Coordinate(position.x + 1, position.y)
      case Direction.WEST => position = Coordinate(position.x - 1, position.y)
    }
  }

  def location(): Coordinate = position

  def direction(): Direction = currentDirection

}
