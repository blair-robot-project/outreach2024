package frc.team449

import com.ctre.phoenix6.Orchestra
import com.ctre.phoenix6.hardware.TalonFX
import edu.wpi.first.hal.FRCNetComm
import edu.wpi.first.hal.HAL
import edu.wpi.first.math.MatBuilder
import edu.wpi.first.math.Nat
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.button.CommandXboxController
import monologue.Logged
import monologue.Monologue
import org.littletonrobotics.urcl.URCL
import kotlin.jvm.optionals.getOrNull

/** The main class of the robot, constructs all the subsystems and initializes default commands. */
class RobotLoop : TimedRobot(), Logged {
  private val motor1 = TalonFX(45, "rio")
  private val motor2 = TalonFX(46, "rio")

  val c = XboxController(0)

  val o = Orchestra()

  override fun robotInit() {

    motor1.configurator
    o.addInstrument(motor1, 2)
    o.addInstrument(motor2, 2)
    val status = o.loadMusic("cancan.chrp")
    System.out.println(status.toString())

  }

  override fun robotPeriodic() {

  }

  override fun autonomousInit() {

  }

  override fun autonomousPeriodic() {}

  override fun teleopInit() {
    o.play()

  }

  override fun teleopPeriodic() {
    System.out.println(o.isPlaying)
    if (c.xButton) {
      o.loadMusic("cancan.chrp")
      o.play()
    }
  }

  override fun disabledInit() {

  }

  override fun disabledPeriodic() {
  }

  override fun testInit() {

  }

  override fun testPeriodic() {}

  override fun simulationInit() {}

  override fun simulationPeriodic() {
  }
}
