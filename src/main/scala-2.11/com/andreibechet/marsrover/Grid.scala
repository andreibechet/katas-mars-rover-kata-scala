package com.andreibechet.marsrover

class Grid(m: Int, n: Int) {

  def moveFromWith(position: Coordinate, translationVector: TranslationVector): Coordinate =
    wrapAroundEdges(Coordinate(position.x + translationVector.x, position.y + translationVector.y))

  def wrapAroundEdges(traveledDistance: Coordinate):Coordinate =
    Coordinate((traveledDistance.x + m) % m, (traveledDistance.y + n) % n)

}
