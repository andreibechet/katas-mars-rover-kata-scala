package com.andreibechet.marsrover

class Grid(m: Int, n: Int) {

  def moveFromWith(position: Coordinate, translationVector: TranslationVector): Coordinate =
    Coordinate(position.x + translationVector.x, position.y + translationVector.y)

}
