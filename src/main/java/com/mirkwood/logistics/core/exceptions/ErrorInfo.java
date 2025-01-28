package com.mirkwood.logistics.core.exceptions;

import java.util.Date;

public record ErrorInfo(String url, String ex, String responseCode, Date date) {

}