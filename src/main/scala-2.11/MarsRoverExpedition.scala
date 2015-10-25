import Direction.Direction

class MarsRoverExpedition(var position: (Int, Int), val currentDirection: Direction) {

  def move(command: String) = {
    if (command == "f")
      currentDirection match {
        case Direction.NORTH => position = (position._1, position._2 + 1)
        case Direction.SOUTH => position = (position._1, position._2 - 1)
        case Direction.EAST => position = (position._1 + 1, position._2)
        case Direction.WEST => position = (position._1 - 1, position._2)
      }
  }

  def location(): (Int, Int) = position

  def direction(): Direction = currentDirection

}
