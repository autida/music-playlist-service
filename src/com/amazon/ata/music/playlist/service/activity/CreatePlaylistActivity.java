package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazon.ata.music.playlist.service.util.MusicPlaylistServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.collections.Sets;

import javax.inject.Inject;
import java.util.*;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 *
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreatePlaylistActivity implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;
    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlists table.
     */
    @Inject
    public CreatePlaylistActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }
    /**
     * This method handles the incoming request by persisting a new playlist
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createPlaylistRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    @Override
    public CreatePlaylistResult handleRequest(final CreatePlaylistRequest createPlaylistRequest, Context context) {
        log.info("Received CreatePlaylistRequest {}", createPlaylistRequest);

        if(!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getName()) ||
                !MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getCustomerId())) {
            throw new InvalidAttributeValueException();
        }
        //NOTE: DynamoDB already supports empty list
        Set<String> tag = null;
        if(createPlaylistRequest.getTags() != null) {
            tag = Sets.newHashSet(createPlaylistRequest.getTags());
        }

        Playlist playlist = new Playlist();
        playlist.setName(createPlaylistRequest.getName());
        playlist.setId(MusicPlaylistServiceUtils.generatePlaylistId());
        playlist.setTags(tag);
        playlist.setCustomerId(createPlaylistRequest.getCustomerId());
        playlist.setSongList(new LinkedList<>());

        return CreatePlaylistResult.builder()
                .withPlaylist(new ModelConverter().toPlaylistModel(playlistDao.savePlaylist(playlist)))
                .build();
    }
}
