package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class MarsRover(initialPosition: Coordinate, initialDirection: Direction, theGrid: Grid) {

  var position = initialPosition
  var direction = initialDirection
  val processCommands = new ProcessCommands(this)
  val computeTranslationVector = new ComputeTranslationVector()
  val grid = theGrid

  def this(initialPosition: Coordinate, initialDirection: Direction) {
    this(initialPosition, initialDirection, new Grid(10, 10))
  }

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
    position = grid.moveFromWith(position, computeTranslationVector(movingForward, direction))
  }

  def moveForward() = {
    val movingForward = true
    position = grid.moveFromWith(position, computeTranslationVector(movingForward, direction))
  }

}
