import org.scalatest.{FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  "A Mars rover expedition" should " have an initial position and direction" in {
    val marsRoverExpedition = new MarsRoverExpedition((1, 1), Direction.NORTH)
    marsRoverExpedition.location() should equal (1, 1)
    marsRoverExpedition.direction() should equal (Direction.NORTH)
  }

  "A Mars rovers position" should " increase by one in the existing direction when receiving the command f" in {
    val northHeadingMarsRoverExpedition = new MarsRoverExpedition((2, 2), Direction.NORTH)
    northHeadingMarsRoverExpedition.move("f")
    northHeadingMarsRoverExpedition.location() should equal (2, 3)
    northHeadingMarsRoverExpedition.direction() should equal (Direction.NORTH)

    val southHeadingMarsRoverExpedition = new MarsRoverExpedition((2, 2), Direction.SOUTH)
    southHeadingMarsRoverExpedition.move("f")
    southHeadingMarsRoverExpedition.location() should equal (2, 1)
    southHeadingMarsRoverExpedition.direction() should equal (Direction.SOUTH)

    val eastHeadingMarsRoverExpedition = new MarsRoverExpedition((2, 2), Direction.EAST)
    eastHeadingMarsRoverExpedition.move("f")
    eastHeadingMarsRoverExpedition.location() should equal (3, 2)
    eastHeadingMarsRoverExpedition.direction() should equal (Direction.EAST)

    val westHeadingMarsRoverExpedition = new MarsRoverExpedition((2, 2), Direction.WEST)
    westHeadingMarsRoverExpedition.move("f")
    westHeadingMarsRoverExpedition.location() should equal (1, 2)
    westHeadingMarsRoverExpedition.direction() should equal (Direction.WEST)
  }


}
