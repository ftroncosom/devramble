package com.devramble.http.auth;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class DigestAuthFilter implements Filter {

    private String authMethod = "auth";
    private String userName = "user";
    private String password = "pass";
    private String realm = "devramble.com";

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        String authHeader = request.getHeader("Authorization");

        if (StringUtils.isBlank(authHeader)) {
            response.addHeader("WWW-Authenticate", getAuthenticateHeader());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } else if (authHeader.startsWith("Digest")) {
            // parse the values of the Authentication header into a hashmap
            HashMap<String, String> headerValues = parseHeader(authHeader);

            String method = request.getMethod();
            String nonce = headerValues.get("nonce");
            String ha1 = DigestUtils.md5Hex(userName + ":" + realm + ":" + password);
            String qop = headerValues.get("qop");
            String ha2;
            String reqURI = headerValues.get("uri");

            ha2 = DigestUtils.md5Hex(method + ":" + reqURI);

            String serverResponse;

            if (StringUtils.isBlank(qop)) {
                serverResponse = DigestUtils.md5Hex(ha1 + ":" + nonce + ":" + ha2);

            } else {
                String domain = headerValues.get("realm");

                String nonceCount = headerValues.get("nc");
                String clientNonce = headerValues.get("cnonce");

                serverResponse = DigestUtils.md5Hex(ha1 + ":" + nonce + ":"
                        + nonceCount + ":" + clientNonce + ":" + qop + ":" + ha2);

            }
            String clientResponse = headerValues.get("response");

            if (!serverResponse.equals(clientResponse)) {
                response.addHeader("WWW-Authenticate", getAuthenticateHeader());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            response.addHeader("WWW-Authenticate", getAuthenticateHeader());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy");
    }

    private String calculateNonce() {
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");
        String fmtDate = f.format(d);
        Random rand = new Random(100000);
        Integer randomInt = rand.nextInt();
        return DigestUtils.md5Hex(fmtDate + randomInt.toString());
    }

    private String getAuthenticateHeader() {
        String header = "";
        String nonce = calculateNonce();

        header += "Digest realm=\"" + realm + "\",";
        if (!StringUtils.isBlank(authMethod)) {
            header += "qop=" + authMethod + ",";
        }
        header += "nonce=\"" + nonce + "\",";
        header += "opaque=\"" + getOpaque(realm, nonce) + "\"";

        return header;
    }

    private String getOpaque(String domain, String nonce) {
        return DigestUtils.md5Hex(domain + nonce);
    }

    private HashMap<String, String> parseHeader(String headerString) {
        // seperte out the part of the string which tells you which Auth scheme is it
        String headerStringWithoutScheme = headerString.substring(headerString.indexOf(" ") + 1).trim();
        HashMap<String, String> values = new HashMap<String, String>();
        String keyValueArray[] = headerStringWithoutScheme.split(",");
        for (String keyval : keyValueArray) {
            if (keyval.contains("=")) {
                String key = keyval.substring(0, keyval.indexOf("="));
                String value = keyval.substring(keyval.indexOf("=") + 1);
                values.put(key.trim(), value.replaceAll("\"", "").trim());
            }
        }
        return values;
    }

}
