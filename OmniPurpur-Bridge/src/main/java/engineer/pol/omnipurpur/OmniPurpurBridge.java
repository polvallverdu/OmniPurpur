package engineer.pol.omnipurpur;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(id = "omnipurpurbridge", name = "OmniPurpurBridge", version = "1.0-SNAPSHOT",
        url = "https://pol.engineer", description = "Bridge for OmniPurpur servers", authors = {"Pol"})
public class OmniPurpurBridge {

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;

    @Inject
    public OmniPurpurBridge(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;

        logger.info("Hello there! I made my first plugin with Velocity.");
    }

}
