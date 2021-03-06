package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;



public class TestsByExersizes extends CoreTestCase {

    @Test
    public void testSearchFieldText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        String actual_input_text = SearchPageObject.getSearchInputText();

        assertEquals(
                "Search input text is incottect",
                "Search Wikipedia",
                actual_input_text
        );
    }

    @Test
    public void testSearchMultipleArticesAndClear() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();
        int search_result = SearchPageObject.getTotalAmountOfArticlesInSearch();
        assertTrue("found only one article or less",search_result>1);
        SearchPageObject.clearSearchInput();
        int cleared_search_result = SearchPageObject.getTotalAmountOfArticlesInSearch();
        assertTrue("searsh result was not cleared",cleared_search_result==0);
    }

    @Test
    public void testConfirmMultipleArticlesByKeyword() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();

        int elements_overall_amount = SearchPageObject.getTotalAmountOfArticlesInSearch();
        int elements_with_keyword_amount = SearchPageObject.getTotalAmountOfArticlesInSearchBysubstring("Java");
        assertTrue("not every result contains corresponding keyword", elements_with_keyword_amount == elements_overall_amount);
    }

    @Test
    public void testSaveArticlesToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        SavedArticlesPageObject SavedArticlesPageObject = new SavedArticlesPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        String search_querry1 = "java";
        String search_article1_title = "Java (programming language)";
        String search_querry2 = "appium";
        String search_article2_title = "Appium";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_querry1);
        SearchPageObject.clickByArticleWithSubstring(search_article1_title);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToSavedList();
        ArticlePageObject.goBackFromArticle();

        SearchPageObject.typeSearchLine(search_querry2);
        SearchPageObject.clickByArticleWithSubstring(search_article2_title);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToSavedList();
        ArticlePageObject.goBackFromArticle();

        NavigationUI.goBackFromSearch();
        NavigationUI.goToSavedArticles();
        SavedArticlesPageObject.openSavedArticlesList();

        SavedArticlesPageObject.deleteArticleByTitleWithSwipe(search_article1_title);
        SavedArticlesPageObject.openArticleByTitle(search_article2_title);
        ArticlePageObject.waitForTitleElement();

        String result_article2_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "titles does not match",
                search_article2_title,
                result_article2_title
        );
    }

    @Test
    public void testAssertArticleHasTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.assertArticleHasTitle();
    }

    @Test
    public void testConfirmFirstThreeResultsOfSearch()
    {
        String title_result1 = "java";
        String description_result1 = "Island in Southeast Asia";
        String title_result2 = "JavaScript";
        String description_result2 = "High-level programming language";
        String title_result3 = "Java (programming language)";
        String description_result3 = "Object-oriented programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();
        SearchPageObject.waitForElementByTitleAndDescription(title_result1, description_result1);
        SearchPageObject.waitForElementByTitleAndDescription(title_result2, description_result2);
        SearchPageObject.waitForElementByTitleAndDescription(title_result3, description_result3);
    }
}

