package net.thumbtack.school.concert;

import com.google.gson.Gson;
import net.thumbtack.school.concert.dto.request.*;
import net.thumbtack.school.concert.dto.response.*;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.server.Server;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class TestConcert {


    @Test
    public void testRegisterUser() {
        Server server = new Server();
        RegisterUserDtoRequest request1 = new RegisterUserDtoRequest(
                "Sergei", "Murashkin", "himik@mail.ru", "123456789");
        Gson gson = new Gson();
        String jsonRequest1 = gson.toJson(request1);
        String jsonResponse1 = server.registerUser(jsonRequest1);
        RegisterUserDtoResponse response1 = gson.fromJson(jsonResponse1, RegisterUserDtoResponse.class);
        String token1 = response1.getToken();
        assertTrue(DataBase.getDatabase().isUserLogged(token1));
        assertEquals(1, DataBase.getDatabase().countRegisteredUsers());

        RegisterUserDtoRequest request2 = new RegisterUserDtoRequest("Sergei", "Murashkin", "himik@mail.ru", "123456789");
        String jsonRequest2 = gson.toJson(request2);
        String jsonResponse2 = server.registerUser(jsonRequest2);
        RegisterUserDtoResponse response2 = gson.fromJson(jsonResponse2, RegisterUserDtoResponse.class);
        assertEquals("error: login exists", response2.getError());
        assertEquals(1, DataBase.getDatabase().countRegisteredUsers());

        RegisterUserDtoRequest request3 = new RegisterUserDtoRequest("Serg", "Murash", "himik2@mail.ru", "987654321");
        String jsonRequest3 = gson.toJson(request3);
        String jsonResponse3 = server.registerUser(jsonRequest3);
        RegisterUserDtoResponse response3 = gson.fromJson(jsonResponse3, RegisterUserDtoResponse.class);
        assertTrue(DataBase.getDatabase().isUserLogged(response3.getToken()));
        assertEquals("himik2@mail.ru", DataBase.getDatabase().getLoggedUser(response3.getToken()));
        assertEquals(2, DataBase.getDatabase().countRegisteredUsers());
        server.stopServer("serverDB.txt");
        server.startServer("serverDB.txt");
        assertEquals(2, DataBase.getDatabase().countRegisteredUsers());
        assertEquals(2, DataBase.getDatabase().countLoggedUsers());

        LogoutDtoRequest logoutRequest = new LogoutDtoRequest(response3.getToken());
        String jsonLogoutRequest = new Gson().toJson(logoutRequest, LogoutDtoRequest.class);
        String jsonLogoutResponse = server.logOut(jsonLogoutRequest);
        LogoutDtoResponse logoutDtoResponse = new LogoutDtoResponse().createResponse(jsonLogoutResponse);
        assertFalse(DataBase.getDatabase().isUserLogged(logoutRequest.getToken()));
        assertEquals("logout done", logoutDtoResponse.getResponse());


        LoginDtoRequest loginRequest = new LoginDtoRequest("himik2@mail.ru", "987654321");
        String login = gson.toJson(loginRequest, LoginDtoRequest.class);
        String jsonLoginResponse = server.logIn(login);
        LoginDtoResponse loginResponse = new Gson().fromJson(jsonLoginResponse, LoginDtoResponse.class);
        String token2 = loginResponse.getToken();
        assertTrue(DataBase.getDatabase().isUserLogged(token2));

        SuggestSongDtoRequest sugSongRequest = new SuggestSongDtoRequest(token2, "Believer",
                new HashSet<>(), new HashSet<>(), "Imagine dragons", 200);
        sugSongRequest.getComposer().add("Daniel Reynolds");
        sugSongRequest.getAuthor().add("Daniel Reynolds");
        Song song = new Song(sugSongRequest.getTitle(),
                sugSongRequest.getComposer(),
                sugSongRequest.getAuthor(),
                sugSongRequest.getSinger(),
                sugSongRequest.getDuration());
        String jsonSugRequest = new Gson().toJson(sugSongRequest, SuggestSongDtoRequest.class);
        SuggestSongDtoResponse sugResponse = new SuggestSongDtoResponse().
                createResponse(server.suggestSong(jsonSugRequest));
        assertEquals("song added", sugResponse.getResponse());
        assertNull(sugResponse.getError());
        assertTrue(DataBase.getDatabase().isYourSuggestedSong(song, token2));
        assertEquals(1, DataBase.getDatabase().countSuggestedSong());

        SuggestSongDtoRequest sugSongRequest2 = new SuggestSongDtoRequest(token2, "Believer",
                new HashSet<>(), new HashSet<>(), "Imagine dragons", 150);
        sugSongRequest2.getComposer().add("Reynolds");
        sugSongRequest2.getAuthor().add("Reynolds");
        Song song2 = new Song(sugSongRequest2.getTitle(),
                sugSongRequest2.getComposer(),
                sugSongRequest2.getAuthor(),
                sugSongRequest2.getSinger(),
                sugSongRequest2.getDuration());
        String jsonSugRequest2 = new Gson().toJson(sugSongRequest2, SuggestSongDtoRequest.class);
        SuggestSongDtoResponse sugResponse2 = new SuggestSongDtoResponse().
                createResponse(server.suggestSong(jsonSugRequest2));
        assertEquals("error: song already added", sugResponse2.getError());
        assertNull(sugResponse2.getResponse());
        assertEquals(1, DataBase.getDatabase().countSuggestedSong());
        assertEquals(song, song2);

        SuggestSongDtoRequest sugSongRequest3 = new SuggestSongDtoRequest(token2, "Natural",
                new HashSet<>(), new HashSet<>(), "Imagine dragons", 180);
        sugSongRequest3.getComposer().add("Daniel");
        sugSongRequest3.getAuthor().add("Reynolds");
        Song song3 = new Song(sugSongRequest3.getTitle(),
                sugSongRequest3.getComposer(),
                sugSongRequest3.getAuthor(),
                sugSongRequest3.getSinger(),
                sugSongRequest3.getDuration());
        String jsonSugRequest3 = new Gson().toJson(sugSongRequest3, SuggestSongDtoRequest.class);
        SuggestSongDtoResponse sugResponse3 = new SuggestSongDtoResponse().
                createResponse(server.suggestSong(jsonSugRequest3));
        assertEquals("song added", sugResponse3.getResponse());
        assertNull(sugResponse3.getError());
        assertEquals(2, DataBase.getDatabase().countSuggestedSong());

        SuggestSongDtoRequest sugSongRequest4 = new SuggestSongDtoRequest(token2, "Everyday Is Christmas",
                new HashSet<>(), new HashSet<>(), "Sia", 3600);
        sugSongRequest4.getComposer().add("Greg Kurstin");
        sugSongRequest4.getAuthor().add("Greg Kurstin");
        Song song4 = new Song(sugSongRequest4.getTitle(),
                sugSongRequest4.getComposer(),
                sugSongRequest4.getAuthor(),
                sugSongRequest4.getSinger(),
                sugSongRequest4.getDuration());
        String jsonSugRequest4 = new Gson().toJson(sugSongRequest4, SuggestSongDtoRequest.class);
        SuggestSongDtoResponse sugResponse4 = new SuggestSongDtoResponse().
                createResponse(server.suggestSong(jsonSugRequest4));
        assertEquals("song added", sugResponse4.getResponse());
        assertNull(sugResponse4.getError());
        assertEquals(3, DataBase.getDatabase().countSuggestedSong());

        AddRatingSongDtoRequest addRatingRequest = new AddRatingSongDtoRequest(token2, song, 3);
        String jsonAddRatingRequest = new Gson().toJson(addRatingRequest, AddRatingSongDtoRequest.class);
        AddRatingSongDtoResponse addRatingResponse = new AddRatingSongDtoResponse().
                createResponse(server.addRating(jsonAddRatingRequest));
        assertEquals("error: you are the author suggestion", addRatingResponse.getError());

        AddRatingSongDtoRequest addRatingRequest2 = new AddRatingSongDtoRequest(token1, song, 3);
        String jsonAddRatingRequest2 = new Gson().toJson(addRatingRequest2, AddRatingSongDtoRequest.class);
        AddRatingSongDtoResponse addRatingResponse2 = new AddRatingSongDtoResponse().
                createResponse(server.addRating(jsonAddRatingRequest2));
        assertEquals("rating " + addRatingRequest2.getRating() + " added", addRatingResponse2.getResponse());
        assertEquals("himik2@mail.ru", DataBase.getDatabase().getAuthorSuggestedSong(song));

        RemoveRatingSongDtoRequest remRatingRequest = new RemoveRatingSongDtoRequest(token2, song);
        String jsonRemRatingRequest = new Gson().toJson(remRatingRequest, RemoveRatingSongDtoRequest.class);
        RemoveRatingSongDtoResponse remRatingResponse = new RemoveRatingSongDtoResponse().
                createResponse(server.removeRating(jsonRemRatingRequest));
        assertEquals("rating removed and community is new author of suggestion", remRatingResponse.getResponse());
        assertEquals("userCommunity@gmail.com", DataBase.getDatabase().getAuthorSuggestedSong(song));


        String userComment1 = "123qwe";
        AddCommentDtoRequest addComment = new AddCommentDtoRequest(token2, song, userComment1);
        String jsonAddComment = new Gson().toJson(addComment, AddCommentDtoRequest.class);
        String jsonAddCommentResponse = server.addComment(jsonAddComment);
        AddCommentDtoResponse addCommentResponse = new Gson().fromJson(jsonAddCommentResponse, AddCommentDtoResponse.class);
        assertEquals("first comment added", addCommentResponse.getResponse());
        System.out.println(DataBase.getDatabase().getCommentsList(song));

        AddCommentDtoRequest addComment2 = new AddCommentDtoRequest(token2, song, userComment1);
        String jsonAddComment2 = new Gson().toJson(addComment2, AddCommentDtoRequest.class);
        String jsonAddCommentResponse2 = server.addComment(jsonAddComment2);
        AddCommentDtoResponse addCommentResponse2 = new Gson().fromJson(jsonAddCommentResponse2, AddCommentDtoResponse.class);
        assertEquals("comment added", addCommentResponse2.getResponse());
        System.out.println(DataBase.getDatabase().getCommentsList(song));

        ChangeCommentDtoRequest changeCommentRequest = new ChangeCommentDtoRequest(token2, song, 1, "qwe123");
        String jsonChangeCommentRequest = new Gson().toJson(changeCommentRequest, ChangeCommentDtoRequest.class);
        String jsonChangeCommentResponse = server.changeComment(jsonChangeCommentRequest);
        ChangeCommentDtoResponse changeCommentResponse = new ChangeCommentDtoResponse().createResponse(jsonChangeCommentResponse);
        assertEquals("comment changed", changeCommentResponse.getResponse());
        System.out.println(DataBase.getDatabase().getCommentsList(song));

        AgreeWithCommentDtoRequest agreeCommentRequest = new AgreeWithCommentDtoRequest(token2, song, 1);
        String jsonAgreeCommentRequest = new Gson().toJson(agreeCommentRequest, AgreeWithCommentDtoRequest.class);
        String jsonAgreeCommentResponse = server.agreeWithComment(jsonAgreeCommentRequest);
        AgreeWithCommentDtoResponse agreeCommentResponse = new Gson().fromJson(jsonAgreeCommentResponse, AgreeWithCommentDtoResponse.class);
        assertEquals("change of attitude: you are agreed", agreeCommentResponse.getResponse());
        System.out.println(DataBase.getDatabase().getCommentsList(song));

        AgreeWithCommentDtoRequest agreeCommentRequest2 = new AgreeWithCommentDtoRequest(token2, song, 1);
        String jsonAgreeCommentRequest2 = new Gson().toJson(agreeCommentRequest2, AgreeWithCommentDtoRequest.class);
        String jsonAgreeCommentResponse2 = server.agreeWithComment(jsonAgreeCommentRequest2);
        AgreeWithCommentDtoResponse agreeCommentResponse2 = new Gson().fromJson(jsonAgreeCommentResponse2, AgreeWithCommentDtoResponse.class);
        assertEquals("change of attitude: you are not agreed", agreeCommentResponse2.getResponse());
        System.out.println(DataBase.getDatabase().getCommentsList(song));


        GetConcertSongsDtoRequest concertSongs = new GetConcertSongsDtoRequest(token2);
        String jsonConcertSongs = new Gson().toJson(concertSongs, GetConcertSongsDtoRequest.class);
        String jsonConcertSongsResponse = server.getConcertSongs(jsonConcertSongs);
        GetConcertSongsDtoResponse concertSongsResponse =
                new Gson().fromJson(jsonConcertSongsResponse, GetConcertSongsDtoResponse.class);
        assertTrue(concertSongsResponse.getAllConcertSongs().contains(song));
        System.out.println(concertSongsResponse.getAllConcertSongs());

        HashSet<String> composers = new HashSet<>();
        composers.add("Daniel Reynolds");
        GetComposerSongsDtoRequest composerSongsRequest = new GetComposerSongsDtoRequest(token2, composers);
        String jsonComposerSongsRequest = new Gson().toJson(composerSongsRequest, GetComposerSongsDtoRequest.class);
        String jsonComposerSongsResponse = server.getComposerSongs(jsonComposerSongsRequest);
        GetComposerSongsDtoResponse composerSongsResponse = new Gson().fromJson(jsonComposerSongsResponse, GetComposerSongsDtoResponse.class);
        System.out.println(composerSongsResponse.getComposerSongs());

        HashSet<String> authors = new HashSet<>();
        authors.add("Daniel Reynolds");
        GetAuthorSongsDtoRequest authorSongsRequest = new GetAuthorSongsDtoRequest(token2, authors);
        String jsonAuthorSongsRequest = new Gson().toJson(authorSongsRequest, GetAuthorSongsDtoRequest.class);
        String jsonAuthorSongsResponse = server.getAuthorSongs(jsonAuthorSongsRequest);
        GetAuthorSongsDtoResponse authorSongsResponse = new Gson().fromJson(jsonAuthorSongsResponse, GetAuthorSongsDtoResponse.class);
        System.out.println(authorSongsResponse.getAuthorSongs());

        String singer = "Imagine dragons";
        GetSingerSongsDtoRequest singerSongsRequest = new GetSingerSongsDtoRequest(token2, singer);
        String jsonSingerSongsRequest = new Gson().toJson(singerSongsRequest, GetSingerSongsDtoRequest.class);
        String jsonSingerSongsResponse = server.getSingerSongs(jsonSingerSongsRequest);
        GetSingerSongsDtoResponse singerSongsResponse = new Gson().fromJson(jsonSingerSongsResponse, GetSingerSongsDtoResponse.class);
        System.out.println(singerSongsResponse.getSingerSongs());


        GetTrialConcertDtoRequest getTrialConcertRequest = new GetTrialConcertDtoRequest(token2);
        String jsonGetTrialConcertRequest = new Gson().toJson(getTrialConcertRequest, GetTrialConcertDtoRequest.class);
        String jsonGetTrialConcertResponse = server.getTrialConcert(jsonGetTrialConcertRequest);
        GetTrialConcertDtoResponse getTrialConcertResponse = new Gson().fromJson(jsonGetTrialConcertResponse, GetTrialConcertDtoResponse.class);
        System.out.println(getTrialConcertResponse.getTrialConcertSongs());


    }

    @Test
    public void testIncorrectRegisterUser() {
        Server server = new Server();
        int before = DataBase.getDatabase().countRegisteredUsers();
        RegisterUserDtoRequest request = new RegisterUserDtoRequest(
                "Sergei", "Murashkin", "himik@mail.ru", "12345");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);
        String jsonResponse = server.registerUser(jsonRequest);
        RegisterUserDtoResponse response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error: short password", response.getError());
        assertEquals(before, DataBase.getDatabase().countRegisteredUsers());

        before = DataBase.getDatabase().countRegisteredUsers();
        request = new RegisterUserDtoRequest(
                "", "Murashkin", "himik@mail.ru", "123456789");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error: empty first name", response.getError());
        assertEquals(before, DataBase.getDatabase().countRegisteredUsers());

        before = DataBase.getDatabase().countRegisteredUsers();
        request = new RegisterUserDtoRequest(
                "Sergei", "      ", "himik@mail.ru", "123456789");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error: empty last name", response.getError());
        assertEquals(before, DataBase.getDatabase().countRegisteredUsers());

    }

}
