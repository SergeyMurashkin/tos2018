package net.thumbtack.school.concert;


import net.thumbtack.school.concert.dto.request.*;
import net.thumbtack.school.concert.dto.response.*;
import net.thumbtack.school.concert.model.Rating;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;
import net.thumbtack.school.concert.model.User;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestConcert extends TestBase {

    @Ignore
    @Test
    public void testPositiveUserServiceRequests() {
        String jsonRequest1 = gson.toJson(new RegisterUserDtoRequest(
                "Sergei", "Murashkin", "workbox_55@mail.ru", "123456789"));
        String jsonResponse1 = server.registerUser(jsonRequest1);
        RegisterUserDtoResponse response1 = gson.fromJson(jsonResponse1, RegisterUserDtoResponse.class);
        assertNull(response1.getError());
        assertEquals(36,response1.getToken().length());

        String jsonRequest2 = gson.toJson(new LogoutDtoRequest(response1.getToken()));
        String jsonResponse2 = server.logoutUser(jsonRequest2);
        LogoutDtoResponse response2 = gson.fromJson(jsonResponse2, LogoutDtoResponse.class);
        assertNull(response2.getError());
        assertEquals("Successful logout.",response2.getResponse());

        String jsonRequest3 = gson.toJson(new LoginDtoRequest("workbox_55@mail.ru", "123456789"));
        String jsonResponse3 = server.loginUser(jsonRequest3);
        LoginDtoResponse response3 = gson.fromJson(jsonResponse3, LoginDtoResponse.class);
        assertNull(response3.getError());
        assertEquals(36,response3.getToken().length());
    }

  //  @Ignore
    @Test
    public void testPositiveSongServiceRequests() {
        
        String jsonRequest1 = gson.toJson(new RegisterUserDtoRequest(
                "Sergei", "Murashkin", "workbox_55@mail.ru", "123456789"));
        String jsonResponse1 = server.registerUser(jsonRequest1);
        RegisterUserDtoResponse response1 = gson.fromJson(jsonResponse1, RegisterUserDtoResponse.class);
        assertNull(response1.getError());
        assertEquals(36,response1.getToken().length());

        List<String> composers = new ArrayList<>();
        composers.add("Sia1");
        composers.add("Sia2");
        List<String> authors = new ArrayList<>();
        authors.add("Sia3");
        authors.add("Sia4");

        String jsonRequest2 = gson.toJson(new SuggestSongDtoRequest(response1.getToken(),
                "Everyday Is Christmas",
                composers,
                authors,
                "Sia",
                150));
        String jsonResponse2 = server.suggestSong(jsonRequest2);
        SuggestSongDtoResponse response2 = gson.fromJson(jsonResponse2, SuggestSongDtoResponse.class);
        assertNull(response2.getError());
        String[] resp1 = response2.getResponse().split(" ");
        int songId = Integer.parseInt(resp1[2]);
        assertEquals("Song № " + songId + " added. Song rated by 5, automatically.",response2.getResponse());

        int rating = 4;
        String jsonRequest3 = gson.toJson(new AddSongRatingDtoRequest(response1.getToken(), songId, rating));
        String jsonResponse3 = server.addRating(jsonRequest3);
        AddSongRatingDtoResponse response3 = gson.fromJson(jsonResponse3, AddSongRatingDtoResponse.class);
        assertNull(response3.getResponse());
        assertEquals(RequestErrorCode.RATING_BAN.getErrorString(),response3.getError());

        String jsonRequest4 = gson.toJson(new RegisterUserDtoRequest(
                "Sergei", "Murashkin", "workbox_555@mail.ru", "123456789"));
        String jsonResponse4 = server.registerUser(jsonRequest4);
        RegisterUserDtoResponse response4 = gson.fromJson(jsonResponse4, RegisterUserDtoResponse.class);
        assertNull(response4.getError());
        assertEquals(36,response4.getToken().length());

        String jsonRequest5 = gson.toJson(new AddSongRatingDtoRequest(response4.getToken(), songId, rating));
        String jsonResponse5 = server.addRating(jsonRequest5);
        AddSongRatingDtoResponse response5 = gson.fromJson(jsonResponse5, AddSongRatingDtoResponse.class);
        assertNull(response5.getError());
        assertEquals("Rating " + rating + " added.",response5.getResponse());

        String commentText = "The song " + songId + " is cool.";
        String jsonRequest6 = gson.toJson(new AddCommentDtoRequest(response1.getToken(), songId, commentText));
        String jsonResponse6 = server.addComment(jsonRequest6);
        AddCommentDtoResponse response6 = gson.fromJson(jsonResponse6, AddCommentDtoResponse.class);
        String[] resp2 = response6.getResponse().split(" ");
        int commentId1 = Integer.parseInt(resp2[2]);
        assertNull(response6.getError());
        assertEquals("Comment № " + commentId1 + " added.",response6.getResponse());

        String commentText2 = "The song № " + songId + " is cool.";
        String jsonRequest7 = gson.toJson(new AddCommentDtoRequest(response4.getToken(), songId, commentText2));
        String jsonResponse7 = server.addComment(jsonRequest7);
        AddCommentDtoResponse response7 = gson.fromJson(jsonResponse7, AddCommentDtoResponse.class);
        String[] resp3 = response7.getResponse().split(" ");
        int commentId2 = Integer.parseInt(resp3[2]);
        assertNull(response7.getError());
        assertEquals("Comment № " + commentId2 + " added.",response7.getResponse());

        String jsonRequest8 = gson.toJson(new RemoveRatingSongDtoRequest(response1.getToken(), songId));
        String jsonResponse8 = server.removeRating(jsonRequest8);
        RemoveRatingSongDtoResponse response8 = gson.fromJson(jsonResponse8, RemoveRatingSongDtoResponse.class);
        assertNull(response8.getError());
        assertEquals("Rating removed. Community is a new author of the song.",response8.getResponse());

        String commentText3 = "The song № " + songId + " isn't cool. It's best.";
        String jsonRequest9 = gson.toJson(new ChangeCommentDtoRequest(response4.getToken(), commentId2, commentText3));
        String jsonResponse9 = server.changeComment(jsonRequest9);
        ChangeCommentDtoResponse response9 = gson.fromJson(jsonResponse9, ChangeCommentDtoResponse.class);
        assertNull(response9.getError());
        assertEquals("Comment № " + commentId2 + " changed.",response9.getResponse());

        String jsonRequest10 = gson.toJson(new AgreeWithCommentDtoRequest(response1.getToken(), commentId2));
        String jsonResponse10 = server.agreeWithComment(jsonRequest10);
        AgreeWithCommentDtoResponse response10 = gson.fromJson(jsonResponse10, AgreeWithCommentDtoResponse.class);
        assertNull(response10.getError());
        assertEquals("Attitude changing: you are agreed.",response10.getResponse());

        Song song = new Song( "Everyday Is Christmas",
                composers,
                authors,
                "Sia",
                150);

        String jsonRequest11 = gson.toJson(new GetConcertSongsDtoRequest(response1.getToken()));
        String jsonResponse11 = server.getConcertSongs(jsonRequest11);
        GetConcertSongsDtoResponse response11 = gson.fromJson(jsonResponse11, GetConcertSongsDtoResponse.class);
        assertNull(response11.getError());
        List<Song> concertSongs = new ArrayList<>();
        concertSongs.add(song);
        assertEquals(concertSongs,response11.getConcertSongs());
        System.out.println(response11.getConcertSongs());

        Set<String> neededComposers = new HashSet<>();
        neededComposers.add("Sia1");
        neededComposers.add("Sia2");
        String jsonRequest12 = gson.toJson(new GetComposerSongsDtoRequest(response1.getToken(), neededComposers));
        String jsonResponse12 = server.getComposerSongs(jsonRequest12);
        GetComposerSongsDtoResponse response12 = gson.fromJson(jsonResponse12, GetComposerSongsDtoResponse.class);
        assertNull(response12.getError());
        List<Song> composerSongs = new ArrayList<>();
        composerSongs.add(song);
        assertEquals(composerSongs,response12.getComposerSongs());

        Set<String> neededAuthors = new HashSet<>();
        neededAuthors.add("Sia3");
        neededAuthors.add("Sia4");
        String jsonRequest13 = gson.toJson(new GetAuthorSongsDtoRequest(response1.getToken(), neededAuthors));
        String jsonResponse13 = server.getAuthorSongs(jsonRequest13);
        GetAuthorSongsDtoResponse response13 = gson.fromJson(jsonResponse13, GetAuthorSongsDtoResponse.class);
        assertNull(response13.getError());
        List<Song> authorSongs = new ArrayList<>();
        authorSongs.add(song);
        assertEquals(authorSongs,response13.getAuthorSongs());

        String jsonRequest14 = gson.toJson(new GetSingerSongsDtoRequest(response1.getToken(), "Sia"));
        String jsonResponse14 = server.getSingerSongs(jsonRequest14);
        GetSingerSongsDtoResponse response14 = gson.fromJson(jsonResponse14, GetSingerSongsDtoResponse.class);
        assertNull(response14.getError());
        List<Song> singerSongs = new ArrayList<>();
        singerSongs.add(song);
        assertEquals(singerSongs,response14.getSingerSongs());

        String jsonRequest15 = gson.toJson(new GetTrialConcertDtoRequest(response1.getToken()));
        String jsonResponse15 = server.getTrialConcert(jsonRequest15);
        GetTrialConcertDtoResponse response15 = gson.fromJson(jsonResponse15, GetTrialConcertDtoResponse.class);
        assertNull(response15.getError());
       /* List<TrialConcertSong> trialConcertSongs = new ArrayList<>();
        TrialConcertSong trialConcertSong = new TrialConcertSong();
        trialConcertSongs.add(trialConcertSong);
        assertEquals(trialConcertSongs,response15.getTrialConcertSongs());*/
        System.out.println(response15.getTrialConcertSongs());


    }



}
