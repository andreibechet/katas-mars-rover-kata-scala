package com.andreibechet.marsrover

class Grid() {
  def moveFromWith(position: Coordinate, translationVector: TranslationVector): Coordinate =
    Coordinate(position.x + translationVector.x, position.y + translationVector.y)

}
