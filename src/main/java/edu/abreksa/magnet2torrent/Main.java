package edu.abreksa.magnet2torrent;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.PrintStream;

public class Main {

    public static Config config = new Config();
    public static CmdLineParser cmdLineParser;

    public static void main(String[] args) {
        cmdLineParser = new CmdLineParser(config);
        try {
            cmdLineParser.parseArgument(args);
            if (config.hash == null & config.magentLink == null) {
                usage(System.out);
                System.exit(1);
            }
        } catch (CmdLineException e) {
            System.out.println(e.getMessage());
            usage(System.out);
            System.exit(1);
        }
        try {
            if (config.hash != null) {
                Magnet2Torrent.magnet2torrent(config.hash, config.torrent);
            } else {
                Magnet2Torrent.magnet2torrent(config.magentLink, config.torrent);
            }
        } catch (Exception e) {
            System.out.println("[*] " + e.getMessage());
        }
    }

    public static void usage(PrintStream stream) {
        stream.println("magnet2torrent -m [MAGNET] -t [TORRENT]");
        cmdLineParser.printUsage(stream);
    }
}