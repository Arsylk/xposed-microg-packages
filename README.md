# xposed-microg-packages
Simple xposed module to change KNOWN_GOOGLE_PACKAGES map &amp; spoof app signatures


Currently fixes problems with EXTENDED_ACCESS and signature spoof fixes errors about no internet connection for  VancedYoutube.

Additionally this might be necessary for GET_ACCOUNTS permission

`pm grant "com.google.android.youtube" "android.permission.GET_ACCOUNTS"`

Future releases might include settings for custom packages & signatures, right now you can build it yourself and change FakeSignatures.java


# Tested on:

Android 9 ResurrectionRemix v7.0.1

MicroG Service Core v0.2.8.17785

VancedYoutube Magisk v14.21.54

EdXposed Framework 90.0-v0.4.51_beta (4463) (SandHook)
