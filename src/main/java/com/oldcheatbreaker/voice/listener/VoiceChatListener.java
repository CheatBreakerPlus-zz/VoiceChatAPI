package com.oldcheatbreaker.voice.listener;

import com.oldcheatbreaker.api.CheatBreakerAPI;
import com.oldcheatbreaker.api.voice.VoiceChannel;
import com.oldcheatbreaker.voice.VoiceChatAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class VoiceChatListener implements Listener {

    private final VoiceChatAPI voiceChatPlugin = VoiceChatAPI.getInstance();
    private final CheatBreakerAPI plugin = CheatBreakerAPI.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final FileConfiguration config = this.voiceChatPlugin.getConfig();

        if (config.getBoolean("channel-on-join")) {
            final VoiceChannel channel = this.voiceChatPlugin.getVoiceChannel(config.getString("public-voice-name"));
            channel.addPlayer(player);
            this.plugin.getVoiceChatHandler().setActiveChannel(player, channel);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final String publicVoiceName = this.voiceChatPlugin.getConfig().getString("public-voice-name");

        if (this.voiceChatPlugin.getConfig().getBoolean("channel-on-join")) {
            this.voiceChatPlugin.getVoiceChannel(publicVoiceName).removePlayer(player);
            this.plugin.getVoiceChatHandler().setActiveChannel(player, (VoiceChannel) null);
        }
    }
}
