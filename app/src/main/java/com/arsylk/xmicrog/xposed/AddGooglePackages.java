package com.arsylk.xmicrog.xposed;

import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.lang.reflect.Method;
import java.util.Map;

import static com.arsylk.xmicrog.FakeSignatures.FAKE_SIGNATURE_MAP;

public class AddGooglePackages implements IXposedHookZygoteInit, IXposedHookLoadPackage {
    private Class<?> classPackageUtils;
    private Map<String, String> knownGooglePackages;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        Class<?> classPackageInfo = XposedHelpers.findClass("android.content.pm.PackageInfo", null);
        XposedBridge.hookAllConstructors(classPackageInfo, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                PackageInfo packageInfo = (PackageInfo) param.thisObject;
                if(packageInfo != null) {
                    if(FAKE_SIGNATURE_MAP.containsKey(packageInfo.packageName)) {
                        packageInfo.signatures = new Signature[] {FAKE_SIGNATURE_MAP.get(packageInfo.packageName)};

                        param.setResult(packageInfo);
                    }
                }
            }
        });
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(!lpparam.packageName.equals("com.google.android.gms"))
            return;

        classPackageUtils = XposedHelpers.findClass("org.microg.gms.common.PackageUtils", lpparam.classLoader);
        knownGooglePackages = (Map<String, String>) XposedHelpers.getStaticObjectField(classPackageUtils, "KNOWN_GOOGLE_PACKAGES");


        for(Map.Entry<String, Signature> fakePackage : FAKE_SIGNATURE_MAP.entrySet()) {
            String signatureSha1 = String.valueOf(XposedHelpers.callStaticMethod(classPackageUtils, "sha1sum", fakePackage.getValue().toByteArray()));
            knownGooglePackages.put(fakePackage.getKey(), signatureSha1);
        }

        XposedHelpers.setStaticObjectField(classPackageUtils, "KNOWN_GOOGLE_PACKAGES", knownGooglePackages);
    }

    private void disableGooglePackageSignatureCheck(final boolean allPackages) {
        Method isGooglePackage = XposedHelpers.findMethodExact(classPackageUtils, "isGooglePackage", String.class, String.class);
        isGooglePackage.setAccessible(true);
        XposedBridge.hookMethod(isGooglePackage, new XC_MethodHook() {
        @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                String packageName = String.valueOf(param.args[0]);
                param.setResult(knownGooglePackages.containsKey(packageName) || allPackages);
            }
        });
    }
}
