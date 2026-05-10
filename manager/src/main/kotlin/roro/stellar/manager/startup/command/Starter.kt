package roro.stellar.manager.startup.command

import roro.stellar.manager.StellarSettings
import roro.stellar.manager.application
import java.io.File

object Starter {

    private val starterFile = File(application.applicationInfo.nativeLibraryDir, "libstellar.so")

    val userCommand: String = starterFile.absolutePath

    val adbCommand = "adb shell $userCommand"

    val internalCommand: String
        get() {
            val baseCommand = "$userCommand --apk=${application.applicationInfo.sourceDir}"
            val dropPrivileges = StellarSettings.getPreferences()
                .getBoolean(StellarSettings.DROP_PRIVILEGES, false)
            return if (dropPrivileges) {
                "${Chid.path} 2000,2000,1004,1007,1011,1015,1028,1078,1079,3001,3002,3003,3006,3009,3011,3012 $baseCommand"
            } else {
                baseCommand
            }
        }
}

