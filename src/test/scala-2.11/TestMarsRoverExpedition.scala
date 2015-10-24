import org.scalatest.{FlatSpec, Matchers}

class TestMarsRoverExpedition extends FlatSpec with Matchers {

  "A Mars rover expedition" should " have an initial position and direction" in {
    val marsRoverExpedition = new MarsRoverExpedition((1, 1), "N")
    marsRoverExpedition.location() should equal (1, 1)
    marsRoverExpedition.direction() should equal ("N")
  }

}
