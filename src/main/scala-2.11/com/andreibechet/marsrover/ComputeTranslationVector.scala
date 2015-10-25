package com.andreibechet.marsrover

class ComputeTranslationVector(expedition: MarsRoverExpedition) {

  def apply(movingForward: Boolean): TranslationVector = {
    val step = if (movingForward) 1 else -1
    processCurrentDirection(step)
  }

  def processCurrentDirection(step: Int): TranslationVector = expedition.direction match {
      case Direction.NORTH => TranslationVector(0, step)
      case Direction.SOUTH => TranslationVector(0, -step)
      case Direction.EAST => TranslationVector(step, 0)
      case Direction.WEST => TranslationVector(-step, 0)
  }
}
