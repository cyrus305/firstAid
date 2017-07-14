package com.iaid.webservice.utils;

import io.jsonwebtoken.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;

/**
 * Created by Crawlers on 9/20/2016.
 */
public class JwtUtils {

  public static String wrkDir = "D:/Work Projects/FirstAid/src/app/config/keystore.jceks";
  public static String keyStrPwd = "javaci123";

  public static String keyName = "mySecretKey";
  public static String keyPwd = "pw-secret";


  private static KeyStore createKeyStore(String fileName, String pw) throws Exception {
    File file = new File(fileName);
    final KeyStore keyStore = KeyStore.getInstance("JCEKS");
    if (file.exists()) {
      // .keystore file already exists => load it
      keyStore.load(new FileInputStream(file), pw.toCharArray());
    } else {
      // .keystore file not created yet => create it
      keyStore.load(null, null);
      keyStore.store(new FileOutputStream(fileName), pw.toCharArray());
    }

    return keyStore;
  }

  public static KeyStore loadKeystore(String fileName, String password){
    try{
      InputStream readStream = new FileInputStream(fileName);
      KeyStore ks = KeyStore.getInstance("JCEKS");
      ks.load(readStream, password.toCharArray());
      return ks;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  public static void saveKey(KeyStore keyStore, String keyPath, String keyStrPwd, String keyName, String keyPwd) throws Exception{
    // generate a secret key for AES encryption
    SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
    // store the secret key
    KeyStore.SecretKeyEntry keyStoreEntry = new KeyStore.SecretKeyEntry(secretKey);
    KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection(keyPwd.toCharArray());
    keyStore.setEntry(keyName, keyStoreEntry, keyPassword);
    keyStore.store(new FileOutputStream(keyPath), keyStrPwd.toCharArray());
  }

  public static Key retriveKey(KeyStore keyStore, String keyName, String keyPwd) throws Exception{
    KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection(keyPwd.toCharArray());
    KeyStore.Entry entry = keyStore.getEntry(keyName, keyPassword);
    SecretKey keyFound = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
    return keyFound;
  }
  public static String getJwt(Key key){
    String jwt = Jwts.builder()
        .setSubject("Aid-Key")
        .claim("token", true)
        .compressWith(CompressionCodecs.DEFLATE)
        .signWith(SignatureAlgorithm.HS512, key)
        .compact();
    return jwt;
  }

  public static boolean isValid(Key key, String jwt){
    try {
      Jws<Claims> claims = Jwts.parser()
          .requireSubject("Aid-Key")
          .require("token", true)
          .setSigningKey(key)
          .parseClaimsJws(jwt);
      return true;
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  public static void main(String[] args) {
    KeyStore keyStore = loadKeystore(wrkDir, keyStrPwd);
    Key key = null;
    String jwt = null;
    try {
      key = retriveKey(keyStore, keyName, keyPwd);
      jwt = getJwt(key);
    }catch (Exception e){
      e.printStackTrace();
    }
    Boolean validTest = isValid(key, jwt);
  }
}
