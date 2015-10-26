package com.andreibechet.marsrover

class ComputeTranslationVector(rover: MarsRover) {

  def apply(movingForward: Boolean): TranslationVector = {
    val step = if (movingForward) 1 else -1
    processCurrentDirection(step)
  }

  def processCurrentDirection(step: Int): TranslationVector = rover.direction match {
      case Direction.NORTH => TranslationVector(0, step)
      case Direction.SOUTH => TranslationVector(0, -step)
      case Direction.EAST => TranslationVector(step, 0)
      case Direction.WEST => TranslationVector(-step, 0)
  }
}
