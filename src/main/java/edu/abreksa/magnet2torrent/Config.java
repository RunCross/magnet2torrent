package edu.abreksa.magnet2torrent;

import org.kohsuke.args4j.Option;

/**
 * Created by Andrew on 12/7/2014.
 */
public class Config {
    @Option(name = "--torrent", aliases = "-t", usage = "The torrent file to save to")
    public String torrent = null;
    @Option(name = "--magnet-link", aliases = "-m", usage = "The magnet link", forbids = {"--hash"})
    public String magentLink = null;
    @Option(name = "--hash", aliases = "-h", usage = "The torrent hash", forbids = {"--magnet-link"})
    public String hash = null;
}
