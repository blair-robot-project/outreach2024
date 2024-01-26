package frc.team449.robot2024.subsystems

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.button.JoystickButton
import edu.wpi.first.wpilibj2.command.button.Trigger
import frc.team449.robot2024.Robot
import frc.team449.robot2024.constants.RobotConstants
import kotlin.math.PI

class ControllerBindings(
  private val controller: XboxController,
  private val robot: Robot
) {

  /*private fun robotBindings() {
    JoystickButton(driveController, XboxController.Button.kRightBumper.value).onTrue(
      robot.undertaker.intake()
    ).onFalse(
      robot.undertaker.stop()
    )

    JoystickButton(driveController, XboxController.Button.kLeftBumper.value).onTrue(
      robot.undertaker.outtake()
    ).onFalse(
      robot.undertaker.stop()
    )
  }*/

  private fun evergreenBindings() {
    // slow drive
    Trigger { controller.rightTriggerAxis >= .75 }.onTrue(
      InstantCommand({ robot.drive.maxLinearSpeed = 5.66 }).andThen(
        InstantCommand({ robot.drive.maxRotSpeed = PI })
      )
    ).onFalse(
      InstantCommand({ robot.drive.maxLinearSpeed = RobotConstants.MAX_LINEAR_SPEED }).andThen(
        InstantCommand({ robot.drive.maxRotSpeed = RobotConstants.MAX_ROT_SPEED })
      )
    )

    // reset gyro
    JoystickButton(controller, XboxController.Button.kStart.value).onTrue(
      InstantCommand({
        robot.drive.heading = Rotation2d()
      })
    )
  }

  fun bindButtons() {
    evergreenBindings()

    JoystickButton(controller, XboxController.Button.kA.value).onTrue(
      robot.intake.runIntakeForward().andThen(
        robot.shooter.runShooter()
      )
    ).onFalse(
      robot.intake.stopIntake().andThen(
        robot.shooter.stopShooter()
      )
    )

    JoystickButton(controller, XboxController.Button.kX.value).onTrue(
      InstantCommand(robot.intake::runIntakeForward)
    ).onFalse(
      InstantCommand(robot.intake::stopIntake)
    )
  }
}
