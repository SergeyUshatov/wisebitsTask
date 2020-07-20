package helpers;

import pageObjects.bazaraki.BazarakiSearchPage;

public class SearchBuilder {

    private BazarakiSearchPage bazarakiSearchPage;

    public SearchBuilder() {
       bazarakiSearchPage = new BazarakiSearchPage();
    }

    public SearchBuilder selectCategory(String category) {
        bazarakiSearchPage.getCategoryElement(category);
        return this;
    }

    public SearchBuilder selectSubCategory(String subCategory) {
        bazarakiSearchPage.getSubCategoryElement(subCategory);
        return this;
    }

    public SearchBuilder selectMinPrice(int minPrice) {
        bazarakiSearchPage.selectFillMinPrice(minPrice);
        return this;
    }

    public SearchBuilder selectMaxPrice(int maxPrice) {
        bazarakiSearchPage.selectFillMaxPrice(maxPrice);
        return this;
    }

    public SearchBuilder selectDistrict(String district) {
        bazarakiSearchPage.clickOnCities();
        bazarakiSearchPage.selectDistrict(district);
        bazarakiSearchPage.clickOnSubmit();
        return this;
    }

    public void search() {
        bazarakiSearchPage.clickOnSearch();
    }

    public SearchBuilder withQuery(String query) {
        bazarakiSearchPage.fillQueryField(query);
        return this;
    }

    public SearchBuilder openFilters() {
        bazarakiSearchPage.clickOnFilters();
        return this;
    }
}
