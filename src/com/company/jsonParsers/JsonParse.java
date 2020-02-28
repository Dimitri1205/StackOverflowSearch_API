package com.company.jsonParsers;

import com.company.beans.ResultData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface JsonParse { List<ResultData> parseJson (InputStream inputStream) throws IOException;}
