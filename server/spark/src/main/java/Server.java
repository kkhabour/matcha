

import static spark.Spark.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.interfaces.DecodedJWT;

import com.auth0.jwt.*;


import utils.Routes;



public class Server {

    private static final String JWT_SECRET = "#$%#$^@@@#$@#$KWFJSDFIHIWEHJKFS#R SOFKSDF@654";
    private static final String JWT_PAYLOAD = "{id: 5, username: kkhabour}";
    
    
    public static void main(String[] arg){


        
        port(80);

        
        String token = createJWTToken();

        get("/", (request ,response) -> token);

        
        get("/verify", (request ,response) -> JWTVerify(token));
        

        String tok = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrYXJpbSIsImlkIjo1LCJ1c2VybmFtZSI6ImtraGFib3VyIn0.NEA6GB5ZdvLli2fvLt01J2DDP7wpKDLotUaT_a1F9Cg";

        
        get("/test", (request ,response) -> JWTVerify(tok));



    }


    private static String createJWTToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("#$@@!@!#$RRES@137");
            String token = JWT.create()
                .withClaim("id", 5)
                .withClaim("username", "kkhabour")
                .withIssuer("auth0")
                .sign(algorithm);

                return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            return "error";
        }
    }


    private static String JWTVerify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("#$@@!@!#$RRES@137");
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

        
            return jwt.getIssuer();
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            return "decode error";
        }
        
    }



}
