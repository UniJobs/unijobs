package web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import web.controller.UniUserController;
import web.dtos.UniUsersDTO;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

@Controller
@RequestMapping("/api/security")
public class RSAController {
    private static final Logger log = LoggerFactory.getLogger(RSAController.class);

    private BigInteger p;
    private BigInteger q;
    public static BigInteger N;
    private BigInteger phi;
    public static BigInteger e;
    public static BigInteger d;
    private int bitlength = 1024;
    private Random r;

    public RSAController() {
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public RSAController(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }

//    @SuppressWarnings("deprecation")
//    public static void main(String[] args) throws IOException {
//        RSA rsa = new RSA();
//        DataInputStream in = new DataInputStream(System.in);
//        String teststring;
//        System.out.println("Enter the plain text:");
//        teststring = in.readLine();
//        System.out.println("Encrypting String: " + teststring);
//        System.out.println("String in Bytes: "
//                + bytesToString(teststring.getBytes()));
//        // encrypt
//        byte[] encrypted = rsa.encrypt(teststring.getBytes());
//        // decrypt
//        byte[] decrypted = rsa.decrypt(encrypted);
//        System.out.println("Encrypted Bytes: " + bytesToString(encrypted));
//        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
//        System.out.println("Decrypted String: " + new String(decrypted));
//    }

    private static String bytesToString(byte[] encrypted) {
        String test = "";
        for (byte b : encrypted) {
            test += Byte.toString(b);
        }
        return test;
    }

    public static BigInteger nextRandomBigInteger(BigInteger n) {
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while( result.compareTo(n) >= 0 ) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
    }

    // Encrypt message
    public static byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    // Decrypt message
    public static byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }

    @RequestMapping(value="/getE", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getE(){
        return e.toString();
    }

    @RequestMapping(value="/getN", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getN(){
        return N.toString();
    }

    @RequestMapping(value="/getD", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getD(){
        return d.toString();
    }

    @RequestMapping(value = "/sendVote", method = RequestMethod.POST, produces = "application/json")
    public String currentUserName(Authentication authentication, @RequestBody String encodedVote) {
        encodedVote = encodedVote.replaceAll("\\[", "");
        encodedVote = encodedVote.replaceAll("]", "");
        encodedVote = encodedVote.replaceAll(" ", "");
        String[] allValues = encodedVote.split(",");
        byte[] encodedArray = new byte[allValues.length];

        for(int i = 0; i < allValues.length; i++) {
            encodedArray[i] = Byte.valueOf(allValues[i]);
        }

        log.trace("Authentication: ", authentication.getName());
        log.trace("Encoded vote: ", encodedVote);
        log.trace("Decrypted vote: ", decrypt(encodedArray));
        return "Successful vote";
    }

}