package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class MarsRoverExpedition(aPosition: Coordinate, aDirection: Direction) {

  var position = aPosition
  var direction = aDirection
  var processCommands = new ProcessCommands(this)

  def execute(commands: Array[Char]) = {
    processCommands(commands)
  }

  def turnRight() = {
    direction = direction match {
      case Direction.NORTH => Direction.EAST
      case Direction.SOUTH => Direction.WEST
      case Direction.EAST => Direction.SOUTH
      case Direction.WEST => Direction.NORTH
    }
  }

  def turnLeft() = {
    direction = direction match {
      case Direction.NORTH => Direction.WEST
      case Direction.SOUTH => Direction.EAST
      case Direction.EAST => Direction.NORTH
      case Direction.WEST => Direction.SOUTH
    }
  }

  def moveBackwards() = {
    position = direction match {
      case Direction.NORTH => Coordinate(position.x, position.y - 1)
      case Direction.SOUTH => Coordinate(position.x, position.y + 1)
      case Direction.EAST => Coordinate(position.x - 1, position.y)
      case Direction.WEST => Coordinate(position.x + 1, position.y)
    }
  }

  def moveForward() = {
    position = direction match {
      case Direction.NORTH => Coordinate(position.x, position.y + 1)
      case Direction.SOUTH => Coordinate(position.x, position.y - 1)
      case Direction.EAST => Coordinate(position.x + 1, position.y)
      case Direction.WEST => Coordinate(position.x - 1, position.y)
    }
  }

}
