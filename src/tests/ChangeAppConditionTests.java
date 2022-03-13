package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String title_after_rotatoin =  ArticlePageObject.getArticleTitle();
        assertEquals(
                "title changed after rotatoin",
                title_before_rotation,
                title_after_rotatoin
        );
        this.rotateScreenPortrait();
        String title_after_second_rotatoin =ArticlePageObject.getArticleTitle();
        assertEquals(
                "title changed after rotatoin",
                title_before_rotation,
                title_after_second_rotatoin
        );
    }



    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        this.backgroundApp(3);
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }
}
