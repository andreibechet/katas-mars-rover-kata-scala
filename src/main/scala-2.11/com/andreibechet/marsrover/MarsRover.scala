package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class MarsRover(initialPosition: Coordinate, initialDirection: Direction, theGrid: Grid) {

  val position = initialPosition
  val direction = initialDirection
  val processCommands = new ProcessCommands()
  val computeTranslationVector = new ComputeTranslationVector()
  val grid = theGrid

  def this(initialPosition: Coordinate, initialDirection: Direction) {
    this(initialPosition, initialDirection, new Grid(10, 10))
  }

  def execute(commands: Array[Char]): MarsRover = {
    processCommands(this, commands)
  }

  def turnRight(): MarsRover = {
    val newDirection = direction match {
      case Direction.NORTH => Direction.EAST
      case Direction.SOUTH => Direction.WEST
      case Direction.EAST => Direction.SOUTH
      case Direction.WEST => Direction.NORTH
    }
    new MarsRover(position, newDirection, grid)
  }

  def turnLeft(): MarsRover = {
    val newDirection = direction match {
      case Direction.NORTH => Direction.WEST
      case Direction.SOUTH => Direction.EAST
      case Direction.EAST => Direction.NORTH
      case Direction.WEST => Direction.SOUTH
    }
    new MarsRover(position, newDirection, grid)
  }

  def moveBackward(): MarsRover = {
    val movingForward = false
    val newPosition = grid.moveFromWith(position, computeTranslationVector(movingForward, direction))
    new MarsRover(newPosition, direction, grid)
  }

  def moveForward(): MarsRover = {
    val movingForward = true
    val newPosition = grid.moveFromWith(position, computeTranslationVector(movingForward, direction))
    new MarsRover(newPosition, direction, grid)
  }

  class ProcessCommands() {
    def apply(marsRover: MarsRover, commands: Array[Char]): MarsRover = {
      if (commands.isEmpty) return marsRover
      apply(processCommand(marsRover, commands.head), commands.tail)
    }

    def processCommand(marsRover: MarsRover, command: Char): MarsRover = command match {
      case 'f' => marsRover.moveForward()
      case 'b' => marsRover.moveBackward()
      case 'l' => marsRover.turnLeft()
      case 'r' => marsRover.turnRight()
    }
  }

}
