package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SavedArticlesPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SaveArticleTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        SavedArticlesPageObject SavedArticlesPageObject = new SavedArticlesPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.addArticleToSavedList();
        ArticlePageObject.goBackFromArticle();
        NavigationUI.goBackFromSearch();
        NavigationUI.goToSavedArticles();
        SavedArticlesPageObject.openSavedArticlesList();
        SavedArticlesPageObject.deleteArticleByTitleWithSwipe(article_title);
    }
}
