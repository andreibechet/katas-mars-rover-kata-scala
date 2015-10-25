package com.andreibechet.marsrover

class ProcessCommands(expedition: MarsRoverExpedition) {
  def apply(commands: Array[Char]) = {
    commands.foreach(processCommand)
  }

  def processCommand(command: Char) = command match {
    case 'f' => expedition.moveForward()
    case 'b' => expedition.moveBackwards()
    case 'l' => expedition.turnLeft()
    case 'r' => expedition.turnRight()
  }
}
