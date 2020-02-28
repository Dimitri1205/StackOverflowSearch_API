package com.company;
import com.company.enums.*;
import com.company.beans.ResultData;
import com.company.jsonParsers.JsonParserJackson;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.List;
import java.util.zip.GZIPInputStream;


public class StackOverflowQuery {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/search?";
    public static final String PARAM_SITE = "site=";
    public static final String PARAM_PAGE_SIZE = "&pagesize=";
    public static final String PARAM_ORDER = "&order=";
    public static final String PARAM_SORT = "&sort";
    public static final String PARAM_TAG = "&tagged=";
    public static final String PARAM_FILTER = "&filter=";
    public static final String PARAM_SEARCH_ITEM = "&intitle=";

    private String site = "stackoverflow";
    private int pageSize = 5;
    private SortOrder sortOrder = SortOrder.ASCENDING;
    private SortBy sortBy = SortBy.RELEVANCE;
    private String tag = "java";
    private String searchTerm = "";

    public void setSite(String site) { this.site = site; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    public void setSortOrder(SortOrder sortOrder) { this.sortOrder = sortOrder; }
    public void setSortBy(SortBy sortBy) { this.sortBy = sortBy; }
    public void setTag(String tag) { this.tag = tag; }
//    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm.replace(" ", "%20"); }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }

    public List<ResultData> execute() throws IOException {
        URL url = buildUrl();

        //parsing info from gzip encoding (Stack Overflow uses this compression for sending the text back)
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();

        if (connection.getContentEncoding().equals("gzip")) {
            inputStream = new GZIPInputStream(inputStream);
        }

        List<ResultData> result;
        JsonParserJackson parser = new JsonParserJackson();
        result = parser.parseJson(inputStream);

        return result;
    }

    private URL buildUrl() throws MalformedURLException {
        StringBuilder urlString = new StringBuilder();
        urlString.append(BASE_URL);
        urlString.append(PARAM_SITE).append(this.site);
        urlString.append(PARAM_PAGE_SIZE).append(this.pageSize);
        urlString.append(PARAM_ORDER).append(this.sortOrder);
        urlString.append(PARAM_SORT).append(this.sortBy);
        urlString.append(PARAM_TAG).append(this.tag);
        urlString.append(PARAM_SEARCH_ITEM).append(this.searchTerm);

        URL url = new URL(urlString.toString());

        try {
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            url = uri.toURL();
        } catch (URISyntaxException e) { e.printStackTrace(); }

        return url;
    }

}
