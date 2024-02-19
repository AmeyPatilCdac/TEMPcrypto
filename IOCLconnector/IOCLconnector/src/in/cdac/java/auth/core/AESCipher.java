package in.cdac.java.auth.core;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Arrays;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESCipher {
	public static final int AES_KEY_SIZE_BITS = 256;
	public static final int IV_SIZE_BITS = 96;
	public static final int AAD_SIZE_BITS = 128;
	public static final int AUTH_TAG_SIZE_BITS = 128;
	private String algorithm = "SHA-256";


	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public byte[] encryptDecryptUsingSessionKey(boolean cipherOperation, byte[] skey, byte[] iv, byte[] aad,
			byte[] data) throws IllegalStateException, InvalidCipherTextException {
		AEADParameters aeadParam = new AEADParameters(new KeyParameter(skey), 128, iv, aad);
		GCMBlockCipher gcmb = new GCMBlockCipher(new AESEngine());
		gcmb.init(cipherOperation, aeadParam);
		int outputSize = gcmb.getOutputSize(data.length);
		byte[] result = new byte[outputSize];
		int processLen = gcmb.processBytes(data, 0, data.length, result, 0);
		gcmb.doFinal(result, processLen);
		return result;
	}

	public byte[] generateIv(String ts) throws UnsupportedEncodingException {
		return this.getLastBits(ts, 12);
	}

	public byte[] generateAad(String ts) throws UnsupportedEncodingException {
		return this.getLastBits(ts, 16);
	}

	private byte[] getLastBits(String ts, int bits) throws UnsupportedEncodingException {
		byte[] tsInBytes = ts.getBytes("UTF-8");
		return Arrays.copyOfRange(tsInBytes, tsInBytes.length - bits, tsInBytes.length);
	}

	public byte[] dataEncrypter(String pidXml, String timeStamp, byte[] sessionKey)
			throws IllegalStateException, Exception {
		AESCipher aesCipher = new AESCipher();
		byte[] inputData = pidXml.getBytes();
		return  aesCipher.encrypt(inputData, sessionKey, timeStamp);
	
	}

	public byte[] encrypt(byte[] inputData, byte[] sessionKey, String ts)
			throws IllegalStateException, InvalidCipherTextException, Exception {
		byte[] iv = this.generateIv(ts);
		byte[] aad = this.generateAad(ts);
		byte[] cipherText = this.encryptDecryptUsingSessionKey(true, sessionKey, iv, aad, inputData);
		byte[] tsInBytes = ts.getBytes("UTF-8");
		byte[] packedCipherData = new byte[cipherText.length + tsInBytes.length];
		System.arraycopy(tsInBytes, 0, packedCipherData, 0, tsInBytes.length);
		System.arraycopy(cipherText, 0, packedCipherData, tsInBytes.length, cipherText.length);
		return packedCipherData;
	}

	public byte[] decrypt(byte[] inputData, byte[] sessionKey, byte[] encSrcHash)
			throws IllegalStateException, Exception {
		byte[] bytesTs = Arrays.copyOfRange(inputData, 0, 19);
		String ts = new String(bytesTs);
		byte[] cipherData = Arrays.copyOfRange(inputData, bytesTs.length, inputData.length);
		byte[] iv = this.generateIv(ts);
		byte[] aad = this.generateAad(ts);
		byte[] plainText = this.encryptDecryptUsingSessionKey(false, sessionKey, iv, aad, cipherData);
		byte[] srcHash = this.encryptDecryptUsingSessionKey(false, sessionKey, iv, aad, encSrcHash);
		boolean result = this.validateHash(srcHash, plainText);
		if (!result) {
			throw new Exception(
					"Integrity Validation Failed : The original data at client side and the decrypted data at server side is not identical");
		} else {
			return plainText;
		}
	}

	private boolean validateHash(byte[] srcHash, byte[] plainTextWithTS) throws Exception {
		byte[] actualHash = this.generateHash(plainTextWithTS);
		return (new String(srcHash, "UTF-8")).equals(new String(actualHash, "UTF-8"));
	}

	public byte[] generateHash(byte[] message) throws Exception {

		try {
			MessageDigest digest = MessageDigest.getInstance(this.algorithm, "BC");
			digest.reset();
			byte[] hash = digest.digest(message);
			return hash;
		} catch (GeneralSecurityException var4) {
			throw new Exception("SHA-256 Hashing algorithm not available");
		}
	}
}
