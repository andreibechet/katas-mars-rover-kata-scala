class MarsRoverExpedition(var position: (Int, Int), val currentDirection: String) {

  def move(command: String) = {
    if (command == "f") 
      position = (position._1, position._2 + 1)
  }

  def location(): (Int, Int) = position

  def direction(): String = currentDirection

}
