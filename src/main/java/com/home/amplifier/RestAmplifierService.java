package com.home.amplifier;

import static com.home.MediaPlayerCommand.SELECT_SOURCE;
import static com.home.MediaPlayerCommand.VOLUME_DOWN;
import static com.home.MediaPlayerCommand.VOLUME_UP;
import static org.apache.http.HttpHeaders.AUTHORIZATION;

import com.home.MediaPlayerCommand;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import javax.inject.Inject;
import javax.inject.Named;

@Log4j2
public class RestAmplifierService implements AmplifierService {

    private static final String ENTITY_ID = "entity_id";
    private static final String MEDIA_PLAYER_AMPLIFIER = "media_player.amplifier";
    private static final String SOURCE = "source";
    private static final JSONObject BASE_JSON_ENTITY = new JSONObject(Collections.singletonMap(ENTITY_ID, MEDIA_PLAYER_AMPLIFIER));

    private String homeAssistantToken;
    private String homeAssistantUrl;
    private ExecutorService executorService = Executors.newFixedThreadPool(6);

    @Inject
    public RestAmplifierService(
            @Named("home_assistant_token")
                    String homeAssistantToken,
            @Named("home_assistant_url")
                    String homeAssistantUrl) {
        this.homeAssistantToken = homeAssistantToken;
        this.homeAssistantUrl = homeAssistantUrl;
    }

    @Override
    public void volumeUp() {
        controlMediaPlayer(VOLUME_UP, BASE_JSON_ENTITY);
    }

    @Override
    public void volumeDown() {
        controlMediaPlayer(VOLUME_DOWN, BASE_JSON_ENTITY);
    }

    @Override
    public void mute() {
        IntStream.range(0, 15).forEach(item -> volumeDown());
    }

    @Override
    public void setSource(AmplifierSource source) {
        JSONObject body = new JSONObject();
        body.put(ENTITY_ID, MEDIA_PLAYER_AMPLIFIER);
        body.put(SOURCE, source.getHumanReadableTitle());

        controlMediaPlayer(SELECT_SOURCE, body);
    }

    private void controlMediaPlayer(MediaPlayerCommand command, JSONObject body) {
        HttpPost httpPost = new HttpPost(buildCallServiceUrl("media_player", command));
        httpPost.setHeader(AUTHORIZATION, "Bearer " + homeAssistantToken);
        httpPost.setEntity(
                new ByteArrayEntity(body.toString().getBytes()));

        executorService.submit(() -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                httpClient.execute(httpPost);
            } catch (IOException e) {
                log.error("Can't send command {} to home assistant", command.getValue(), e);
            }
        });
    }

    private String buildCallServiceUrl(String serviceId, MediaPlayerCommand command) {
        return String.join("/", homeAssistantUrl, "api/services", serviceId, command.getValue());
    }
}
