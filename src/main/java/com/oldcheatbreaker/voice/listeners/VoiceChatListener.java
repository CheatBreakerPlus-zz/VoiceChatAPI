package com.oldcheatbreaker.voice.listeners;

import com.oldcheatbreaker.api.CheatBreakerAPI;
import com.oldcheatbreaker.api.voice.VoiceChannel;
import com.oldcheatbreaker.voice.VoiceChatAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VoiceChatListener implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (VoiceChatAPI.getInstance().getConfig().getBoolean("channel-on-join")) {
            VoiceChatAPI.getInstance().getVoiceChannel(VoiceChatAPI.getInstance().getConfig().getString("public-voice-name")).addPlayer(player);
            CheatBreakerAPI.getInstance().getVoiceChatHandler().setActiveChannel(player, VoiceChatAPI.getInstance().getVoiceChannel(VoiceChatAPI.getInstance().getConfig().getString("public-voice-name")));
        }
    }

    @EventHandler
    public void leave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (VoiceChatAPI.getInstance().getConfig().getBoolean("channel-on-join")) {
            VoiceChatAPI.getInstance().getVoiceChannel(VoiceChatAPI.getInstance().getConfig().getString("public-voice-name")).removePlayer(player);
            CheatBreakerAPI.getInstance().getVoiceChatHandler().setActiveChannel(player, (VoiceChannel) null);
        }
    }
}
