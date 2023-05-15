package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class AlbumTrackDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    private AlbumTrackDao albumTrackDao;
    AlbumTrack albumTrack;

    @BeforeEach
    public void setup() {
       albumTrack = new AlbumTrack();
       albumTrack.setAlbumName("This is me.");
       albumTrack.setTrackNumber(1);
       albumTrack.setAsin("123");
    }

    @Test
    public void getAlbumTrack_returnAlbumTrack() {
        String asin = "123";
        Integer trackNumber = 1;
        
//        when(dynamoDBMapper.load,asin, trackNumber).thenReturn(albumTrack);
    }

}
