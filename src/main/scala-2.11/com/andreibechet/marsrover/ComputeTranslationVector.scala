package com.andreibechet.marsrover

import com.andreibechet.marsrover.Direction.Direction

class ComputeTranslationVector() {

  def apply(movingForward: Boolean, direction: Direction): TranslationVector = {
    val step = if (movingForward) 1 else -1
    processCurrentDirection(step, direction)
  }

  def processCurrentDirection(step: Int, direction: Direction): TranslationVector = direction match {
      case Direction.NORTH => TranslationVector(0, step)
      case Direction.SOUTH => TranslationVector(0, -step)
      case Direction.EAST => TranslationVector(step, 0)
      case Direction.WEST => TranslationVector(-step, 0)
  }
}
