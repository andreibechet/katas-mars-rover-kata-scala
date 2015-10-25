package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class MarsRoverExpedition(var position: Coordinate, val currentDirection: Direction) {

  def move(command: String) = {
    if (command == "f")
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
