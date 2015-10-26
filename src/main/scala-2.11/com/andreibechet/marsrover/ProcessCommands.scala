package com.andreibechet.marsrover

class ProcessCommands(rover: MarsRover) {
  def apply(commands: Array[Char]) = {
    commands.foreach(processCommand)
  }

  def processCommand(command: Char) = command match {
    case 'f' => rover.moveForward()
    case 'b' => rover.moveBackward()
    case 'l' => rover.turnLeft()
    case 'r' => rover.turnRight()
  }
}
