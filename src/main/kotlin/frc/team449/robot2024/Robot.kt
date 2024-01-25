package frc.team449.robot2024

import edu.wpi.first.wpilibj.PowerDistribution
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.XboxController
import frc.team449.RobotBase
import frc.team449.control.holonomic.mecanum.MecanumDrive
import frc.team449.control.holonomic.mecanum.MecanumDriveCommand
import frc.team449.control.holonomic.mecanum.MecanumOI.Companion.createMecanumOI
import frc.team449.robot2023.subsystems.outreach.shooter.Shooter.Companion.createShooter
import frc.team449.robot2024.constants.RobotConstants
import frc.team449.robot2024.subsystems.outreach.indexer.Indexer.Companion.createIndexer
import frc.team449.robot2024.subsystems.outreach.intake.Intake.Companion.createIntake
import frc.team449.system.AHRS
import frc.team449.system.light.Light
import monologue.Annotations.Log
import monologue.Logged

class Robot : RobotBase(), Logged {

  val driveController = XboxController(0)

  val ahrs = AHRS(SPI.Port.kMXP)

  // Instantiate/declare PDP and other stuff here

  @Log.NT
  override val powerDistribution: PowerDistribution = PowerDistribution(
    RobotConstants.PDH_CAN,
    PowerDistribution.ModuleType.kRev
  )

  @Log.NT
  override val drive = MecanumDrive.createMecanum(ahrs)

  @Log.NT
  val oi = createMecanumOI(drive, driveController)

  @Log.NT
  override val driveCommand = MecanumDriveCommand(drive, oi)

  val light = Light.createLight()

  val intake = createIntake()

  val shooter = createShooter()

  val indexer = createIndexer()
//
//  val infrared = DigitalInput(RobotConstants.IR_CHANNEL)
}
