import org.scalatest.{FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  "A Mars rover expedition" should " have an initial position and direction" in {
    val marsRoverExpedition = new MarsRoverExpedition((1, 1), "N")
    marsRoverExpedition.location() should equal (1, 1)
    marsRoverExpedition.direction() should equal ("N")
  }

  "A Mars rovers position" should " increase by one in the existing direction when receiving the command f" in {
    val northHeadingMarsRoverExpedition = new MarsRoverExpedition((2, 2), "N")
    northHeadingMarsRoverExpedition.move("f")
    northHeadingMarsRoverExpedition.location() should equal (2, 3)
    northHeadingMarsRoverExpedition.direction() should equal ("N")

    val southHeadingMarsRoverExpedition = new MarsRoverExpedition((2, 2), "S")
    southHeadingMarsRoverExpedition.move("f")
    southHeadingMarsRoverExpedition.location() should equal (2, 1)
    southHeadingMarsRoverExpedition.direction() should equal ("S")

    val eastHeadingMarsRoverExpedition = new MarsRoverExpedition((2, 2), "E")
    eastHeadingMarsRoverExpedition.move("f")
    eastHeadingMarsRoverExpedition.location() should equal (3, 2)
    eastHeadingMarsRoverExpedition.direction() should equal ("E")

    val westHeadingMarsRoverExpedition = new MarsRoverExpedition((2, 2), "W")
    westHeadingMarsRoverExpedition.move("f")
    westHeadingMarsRoverExpedition.location() should equal (1, 2)
    westHeadingMarsRoverExpedition.direction() should equal ("W")
  }


}
