package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class MarsRoverExpedition(aPosition: Coordinate, aDirection: Direction) {

  var position = aPosition
  var direction = aDirection
  val processCommands = new ProcessCommands(this)

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

  def moveBackward() = {
    val translationVector = getTranslationVector(false)
    position = Coordinate(position.x + translationVector.x, position.y + translationVector.y)
  }

  def moveForward() = {
    val translationVector = getTranslationVector(true)
    position = Coordinate(position.x + translationVector.x, position.y + translationVector.y)
  }

  def getTranslationVector(movingForward: Boolean): TranslationVector = {
    val step = if (movingForward) 1 else -1
    direction match {
      case Direction.NORTH => TranslationVector(0, step)
      case Direction.SOUTH => TranslationVector(0, -step)
      case Direction.EAST => TranslationVector(step, 0)
      case Direction.WEST => TranslationVector(-step, 0)
    }
  }

}
