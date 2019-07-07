package com.arsylk.xmicrog;

import android.content.pm.Signature;
import java.util.HashMap;
import java.util.Map;

public class FakeSignatures {
    public final static Map<String, Signature> FAKE_SIGNATURE_MAP = new HashMap<>();
    static {
        FAKE_SIGNATURE_MAP.put("com.google.android.youtube", new Signature("30820252308201bb02044934987e300d06092a864886f70d01010405003070310b3009060355040613025553310b3009060355040813024341311630140603550407130d4d6f756e7461696e205669657731143012060355040a130b476f6f676c652c20496e6331143012060355040b130b476f6f676c652c20496e633110300e06035504031307556e6b6e6f776e301e170d3038313230323032303735385a170d3336303431393032303735385a3070310b3009060355040613025553310b3009060355040813024341311630140603550407130d4d6f756e7461696e205669657731143012060355040a130b476f6f676c652c20496e6331143012060355040b130b476f6f676c652c20496e633110300e06035504031307556e6b6e6f776e30819f300d06092a864886f70d010101050003818d00308189028181009f48031990f9b14726384e0453d18f8c0bbf8dc77b2504a4b1207c4c6c44babc00adc6610fa6b6ab2da80e33f2eef16b26a3f6b85b9afaca909ffbbeb3f4c94f7e8122a798e0eba75ced3dd229fa7365f41516415aa9c1617dd583ce19bae8a0bbd885fc17a9b4bd2640805121aadb9377deb40013381418882ec52282fc580d0203010001300d06092a864886f70d0101040500038181004086669ed631da4384ddd061d226e073b98cc4b99df8b5e4be9e3cbe97501e83df1c6fa959c0ce605c4fd2ac6d1c84cede20476cbab19be8f2203aff7717ad652d8fcc890708d1216da84457592649e0e9d3c4bb4cf58da19db1d4fc41bcb9584f64e65f410d0529fd5b68838c141d0a9bd1db1191cb2a0df790ea0cb12db3a4"));
    }
}
