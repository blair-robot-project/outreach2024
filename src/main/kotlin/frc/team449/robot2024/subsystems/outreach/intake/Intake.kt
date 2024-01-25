package frc.team449.robot2024.subsystems.outreach.intake

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.team449.robot2023.subsystems.outreach.intake.IntakeConstants
import frc.team449.system.encoder.NEOEncoder
import frc.team449.system.motor.WrappedMotor
import frc.team449.system.motor.createSparkMax

class Intake(
  private val intakeMotor: WrappedMotor
) : SubsystemBase() {

  // Run intake motor forwards.
  fun runIntakeForward(): Command {
    return this.runOnce { intakeMotor.setVoltage(IntakeConstants.INTAKE_VOLTAGE) }
  }

  // Run intake motor in reverse.
  fun runIntakeReverse(): Command {
    return this.runOnce { intakeMotor.setVoltage(-IntakeConstants.INTAKE_VOLTAGE) }
  }

  // Stop intake motor.
  fun stopIntake(): Command {
    return this.runOnce { intakeMotor.stopMotor() }
  }

  companion object {
    fun createIntake(): Intake {
      val intakeMotor = createSparkMax(
        "Intake",
        IntakeConstants.INTAKE_ID,
        NEOEncoder.creator(IntakeConstants.INTAKE_UPR, IntakeConstants.INTAKE_GEARING)
      )

      return Intake(intakeMotor)
    }
  }
}
