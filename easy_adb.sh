#!/bin/bash
alias adb='/Users/ramadan/Library/Android/sdk/platform-tools/adb'
PACKAGE="com.ramadan.takeaway"

print_blue(){
    printf "\e[1;34m$1\e[0m"
}
print_red(){
    printf "\e[1;31m$1\e[0m"
}
print_green(){
    printf "\e[1;32m$1\e[0m"
}
print_yellow(){
    printf "\e[1;33m$1\e[0m"
}


while test $# -gt 0; do
    case "$1" in
        device)
                print_blue "\n ====================================    Devices:   ===============================\n"
                adb devices
                print_blue "\n ==================================================================================================\n"
                exit 0
                ;;
        package)
                print_blue "\n ====================================    Packages:   ===============================\n"
                adb shell pm list packages
                print_blue "\n ==================================================================================================\n"
                exit 0
                ;;

        shell)
             print_blue "\n ====================================    Device Shell:   ===============================\n"
             adb shell
             print_blue "\n ==================================================================================================\n"
             exit 0
             ;;

        command )
             print_blue "\n ====================================    ADB Commands:   ===============================\n"
             adb shell ls  /system/bin
             print_blue "\n ==================================================================================================\n"
             exit 0
             ;;

        feature )
             print_blue "\n ====================================    Device Features:   ===============================\n"
             adb shell pm  list features
             print_blue "\n ==================================================================================================\n"
             exit 0
             ;;

         capture)
             print_blue "\n ====================================   Device ScreenShot:   ===============================\n"
             adb shell screencap /sdcard/screenshot.png
             print_green "screen is captured \n"
             print_blue "==================================================================================================\n"
             exit 0
             ;;
         picture)
             print_blue "\n ====================================   Pull ScreenShot:   ===============================\n"
             adb pull  /sdcard/screenshot.png
             print_green "check your project root folder\n"
             print_blue "==================================================================================================\n"
             exit 0
             ;;

         record)
             print_blue "\n ====================================   Device Recording:   ===============================\n"
             adb shell  screenrecord /sdcard/record.mp4
             print_blue "==================================================================================================\n"
             exit 0
             ;;

         video)
             print_blue "\n ====================================   Pull Video:   ===============================\n"
             adb pull  /sdcard/record.mp4
             print_green "check your project root folder\n"
             print_blue "==================================================================================================\n"
             exit 0
             ;;

         anim-off)
             print_blue "\n ====================================   disable Animation :   ===============================\n"
             adb shell settings put global window_animation_scale 0
	           adb shell settings put global transition_animation_scale 0
	           adb shell settings put global animator_duration_scale 0
	           print_green "Animation is off\n"
             print_blue "==================================================================================================\n"
             exit 0
             ;;

         anim-on)
             print_blue "\n ====================================   enable Animation :   ===============================\n"
             adb shell settings put global window_animation_scale 1
	           adb shell settings put global transition_animation_scale 1
	           adb shell settings put global animator_duration_scale 1
	           print_green "Animation is on\n"
             print_blue "==================================================================================================\n"
             exit 0
             ;;

        clear)
             print_blue "\n ====================================   clear App Data :   ===============================\n"
             adb shell pm clear $PACKAGE
	           print_green "App Data is Cleared \n"
             print_blue "==================================================================================================\n"
             exit 0
             ;;

        ip)
             print_blue "\n ====================================   IP Address Info :   ===============================\n"
             adb shell ip addr show
             print_blue "==================================================================================================\n"
             exit 0
             ;;

        dumpsys)
             print_blue "\n ====================================   Dumpsys starting :   ===============================\n"
             adb shell  dumpsys > ~/Desktop/dumpsys.txt
             print_green "Dumpsys is generated , check your Desktop \n"
             print_blue "==================================================================================================\n"
             exit 0
             ;;

        dumpsys-less)
             print_blue "\n ====================================   Dumpsys starting :   ===============================\n"
             adb shell  dumpsys | less
             print_blue "==================================================================================================\n"
             exit 0
             ;;

         battery)
             print_blue "\n ====================================   Battery Info :   ===============================\n"
             adb shell  dumpsys battery
             print_blue "==================================================================================================\n"
             exit 0
             ;;

        *)
           print_red "command is not supported \n"
           break
           ;;
        esac
 done