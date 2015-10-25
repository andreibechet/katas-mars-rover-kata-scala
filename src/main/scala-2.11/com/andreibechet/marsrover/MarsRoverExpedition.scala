package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class MarsRoverExpedition(aPosition: Coordinate, aDirection: Direction) {

  var position = aPosition
  var currentDirection = aDirection

  def turn(command: String) = {
    // TODO: check that the command can only be "l" or "r"
    currentDirection match {
      case Direction.NORTH => currentDirection = Direction.WEST
      case Direction.SOUTH => currentDirection = Direction.EAST
      case Direction.EAST => currentDirection = Direction.NORTH
      case Direction.WEST => currentDirection = Direction.SOUTH
    }
  }

  def move(command: String) = {
    // TODO: check that the command can only be "f" or "b"
    if (command == "f")
      currentDirection match {
        case Direction.NORTH => position = Coordinate(position.x, position.y + 1)
        case Direction.SOUTH => position = Coordinate(position.x, position.y - 1)
        case Direction.EAST => position = Coordinate(position.x + 1, position.y)
        case Direction.WEST => position = Coordinate(position.x - 1, position.y)
      }
    else {
      currentDirection match {
        case Direction.NORTH => position = Coordinate(position.x, position.y - 1)
        case Direction.SOUTH => position = Coordinate(position.x, position.y + 1)
        case Direction.EAST => position = Coordinate(position.x - 1, position.y)
        case Direction.WEST => position = Coordinate(position.x + 1, position.y)
      }
    }
  }

  def location(): Coordinate = position

  def direction(): Direction = currentDirection

}
