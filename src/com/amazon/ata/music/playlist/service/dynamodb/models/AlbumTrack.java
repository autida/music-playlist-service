package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Represents a record in the album_tracks table.
 */
@DynamoDBTable(tableName = "album_tracks")
public class AlbumTrack {
    private String asin;
    private Integer trackNumber;
    private String albumName;
    private String songTitle;
    @DynamoDBHashKey(attributeName = "asin")
    public String getAsin() {
        return asin;
    }

    public AlbumTrack setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    @DynamoDBRangeKey(attributeName = "track_number")
    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer track_number) {
        this.trackNumber = track_number;
    }
    @DynamoDBAttribute(attributeName = "album_name")
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String album_name) {
        this.albumName = album_name;
    }
    @DynamoDBAttribute(attributeName = "song_title")
    public String getSongTitle() {
        return songTitle;
    }
    public void setSongTitle(String song_title) {
        this.songTitle = song_title;
    }
}
