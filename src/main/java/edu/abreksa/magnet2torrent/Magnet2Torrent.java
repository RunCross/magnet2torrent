package edu.abreksa.magnet2torrent;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrew on 12/7/2014.
 */
public class Magnet2Torrent {
    public static final String torrageUrl = "http://torrage.com/torrent/%s.torrent";
    public static final String regex = "([A-Z0-9]{40})";

    public static void magnet2torrent(String magnet, String torrent) throws InvalidMagnetLinkException, IOException {
        System.out.println("[*] Fetching torrent file for magent link...");
        String magnet1 = magnet.toUpperCase();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(magnet1);
        if (!matcher.find()) {
            throw new InvalidMagnetLinkException("The magent link \"" + magnet + "\" isn't valid.");
        }
        String hash = matcher.group(1);
        System.out.println("[*] Parsed hash: \"" + hash + "\"");
        File file;
        if (torrent == null) {
            file = new File(System.getProperty("user.dir") + File.separator + hash + ".torrent");
        } else {
            file = new File(torrent);
        }
        if (file.exists()) {
            throw new IOException("The file \"" + file.getAbsolutePath() + "\" already exists!");
        }
        URL url = new URL(String.format(torrageUrl, hash));
        System.out.println("[*] Downloading... [URL: \"" + url.toString() + "\"]");
        FileUtils.copyURLToFile(url, file);
        System.out.println("[*] Done [Saved to \"" + file.getName() + "\"]");
    }

    public static class InvalidMagnetLinkException extends Exception {
        public InvalidMagnetLinkException() {
        }

        public InvalidMagnetLinkException(String message) {
            super(message);
        }

        public InvalidMagnetLinkException(String message, Throwable cause) {
            super(message, cause);
        }

        public InvalidMagnetLinkException(Throwable cause) {
            super(cause);
        }

        public InvalidMagnetLinkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
