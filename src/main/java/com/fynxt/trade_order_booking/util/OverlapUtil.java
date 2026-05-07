package com.fynxt.trade_order_booking.util;

import java.util.*;

public class OverlapUtil {

    public static final Map<String, List<String>> BASKETS = Map.of(
            "TECH_HEAVY",
            List.of("AAPL", "MSFT", "GOOGL", "TSLA", "NVDA"),

            "FINANCE_HEAVY",
            List.of("JPM", "GS", "BAC", "MS", "WFC"),

            "BALANCED",
            List.of("AAPL", "JPM", "XOM", "JNJ", "TSLA")
    );

    public static double calculateOverlap(
            Set<String> portfolioStocks,
            List<String> basketStocks
    ) {

        Set<String> common = new HashSet<>(portfolioStocks);

        common.retainAll(basketStocks);

        return (2.0 * common.size())
                / (portfolioStocks.size() + basketStocks.size())
                * 100;
    }
}
