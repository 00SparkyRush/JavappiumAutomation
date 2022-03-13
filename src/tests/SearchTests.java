package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitFroCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitFroCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String search_querry = "Linkin park discography";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_querry);

        int amount_of_search_elements = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Too few results were found",
                amount_of_search_elements > 0
        );
    }

    @Test
    public void testAmountofEmtySearch() {
        String search_querry = "fsfghgfhrg";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_querry);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResult();
    }
}
