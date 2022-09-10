package ru.buttonone.chapter.lotr;

import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.buttonone.chapter.Chapter;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("С API Властелина колец должны")
public class LotrChapterTest {

    public static final String LOTR_API_BOOK_URL = "https://the-one-api.dev/v2/book";
    public static final String BOOK_ID = "5cf5805fb53e011a64671582";
    public static final String ID_CHAPTER = "/{id}/chapter";

    @DisplayName(" корректно получать chapter's")
    @Test
    public void shouldHaveCorrectGetChaptersTest(){
        ValidatableResponse validatableResponse = given()
                .baseUri(LOTR_API_BOOK_URL)
                .pathParam("id", BOOK_ID)
                .when()
                .get(ID_CHAPTER)
                .then()
                .statusCode(200);

        List<Chapter> chapterList = validatableResponse
                .extract()
                .body()
                .jsonPath()
                .getList("docs", Chapter.class);

//        System.out.println(chapterList);

        assertThat(chapterList, Matchers.contains(
                new Chapter("A Long-expected Party"),           //0
                new Chapter("The Shadow of the Past"),          //1
                new Chapter("Three is Company"),                //2
                new Chapter("A Short Cut to Mushrooms"),        //3
                new Chapter("A Conspiracy Unmasked"),           //4
                new Chapter("The Old Forest"),                  //5
                new Chapter("In the House of Tom Bombadil"),    //6
                new Chapter("Fog on the Barrow-Downs"),         //7
                new Chapter("At the Sign of The Prancing Pony"),//8
                new Chapter("Strider"),                         //9
                new Chapter("A Knife in the Dark"),             //10
                new Chapter("Flight to the Ford"),              //11
                new Chapter("Many Meetings"),                   //12
                new Chapter("The Council of Elrond"),           //13
                new Chapter("The Ring Goes South"),             //14
                new Chapter("A Journey in the Dark"),           //15
                new Chapter("The Bridge of Khazad-dûm"),        //16
                new Chapter("Lothlórien"),                      //17
                new Chapter("The Mirror of Galadriel"),         //18
                new Chapter("Farewell to Lórien"),              //19
                new Chapter("The Great River"),                 //20
                new Chapter("The Breaking of the Fellowship")));//21
    }
}
