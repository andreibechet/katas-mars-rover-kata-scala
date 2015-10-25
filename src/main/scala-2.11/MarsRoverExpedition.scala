class MarsRoverExpedition(var position: (Int, Int), val currentDirection: String) {

  def move(command: String) = {
    if (command == "f")
      currentDirection match {
        case "N" => position = (position._1, position._2 + 1)
        case "S" => position = (position._1, position._2 - 1)
        case "E" => position = (position._1 + 1, position._2)
        case "W" => position = (position._1 - 1, position._2)
      }
  }

  def location(): (Int, Int) = position

  def direction(): String = currentDirection

}
