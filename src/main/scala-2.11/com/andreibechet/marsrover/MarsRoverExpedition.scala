package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class MarsRoverExpedition(aPosition: Coordinate, aDirection: Direction) {

  var position = aPosition
  var direction = aDirection
  val processCommands = new ProcessCommands(this)
  val computeTranslationVector = new ComputeTranslationVector(this)
  val grid = new Grid()

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
    val movingForward = false
    position = grid.moveFromWith(position, computeTranslationVector(movingForward))
  }

  def moveForward() = {
    val movingForward = true
    position = grid.moveFromWith(position, computeTranslationVector(movingForward))
  }

}
