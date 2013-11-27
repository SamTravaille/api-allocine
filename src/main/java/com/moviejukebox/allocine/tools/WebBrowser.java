/*
 *      Copyright (c) 2004-2013 YAMJ Members
 *      http://code.google.com/p/moviejukebox/people/list
 *
 *      This file is part of the Yet Another Movie Jukebox (YAMJ).
 *
 *      The YAMJ is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      YAMJ is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with the YAMJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 *      Web: http://code.google.com/p/moviejukebox/
 *
 */
package com.moviejukebox.allocine.tools;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.codec.binary.Base64;

public final class WebBrowser {

    private static String proxyHost = null;
    private static int proxyPort = 0;
    private static String proxyEncodedPassword = null;
//    private static String userAgent = "Mozilla/5.25 Netscape/5.0 (Windows; I; Win95)";
//    private static String userAgent = "Dalvik/1.6.0 (Linux; U; Android 4.2.2; Nexus 4 Build/JDQ39E)";
    private static String userAgent = "Dalvik/1.6.0 (Linux; U; Android 4.0.3; GT-P3100 Build/IML74K)";

    private WebBrowser() {
        throw new UnsupportedOperationException("Class cannot be initialised");
    }

    /**
     * Open a connection using proxy parameters if they exist.
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static URLConnection openProxiedConnection(URL url) throws IOException {
        if (proxyHost != null) {
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("proxyHost", proxyHost);
            System.getProperties().put("proxyPort", proxyPort);
        }

        URLConnection connection = url.openConnection();
        connection.addRequestProperty("User-Agent", userAgent);

        if (proxyEncodedPassword != null) {
            connection.setRequestProperty("Proxy-Authorization", proxyEncodedPassword);
        }

        return connection;
    }

    /**
     * Set the proxy host name
     *
     * @param proxyHost
     */
    public static void setProxyHost(String proxyHost) {
        WebBrowser.proxyHost = proxyHost;
    }

    /**
     * Set the proxy port
     *
     * @param proxyPort
     */
    public static void setProxyPort(int proxyPort) {
        WebBrowser.proxyPort = proxyPort;
    }

    /**
     * Set the user agent to use
     *
     * @param userAgent
     */
    public static void setUserAgent(String userAgent) {
        WebBrowser.userAgent = userAgent;
    }

    /**
     * @param proxyUsername
     * @param proxyPassword
     */
    public static void setProxyPassword(String proxyUsername, String proxyPassword) {
        if (proxyUsername != null && proxyPassword != null) {
            proxyEncodedPassword = proxyUsername + ":" + proxyPassword;
            proxyEncodedPassword = "Basic " + new String(Base64.encodeBase64((proxyUsername + ":" + proxyPassword).getBytes()));
        }
    }
}
