package com.shoma.mypocketcare.DataIncomeExpense;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 9/29/2016.
 */

public class MyPocketContract {
    public MyPocketContract() {
    }

    public static final class IncomeEntry implements BaseColumns {
        public static final String TABLE_NAME = "income_details";
        public static final String COLUMN_INCOME_ID = "_id";
        public static final String COLUMN_INCOME_CATEGORY = "category";
        public static final String COLUMN_INCOME_DESCRIPTION="description";
        public static final String COLUMN_INCOME_DATE= "date";
        public static final String COLUMN_INCOME_AMOUNT = "amount";

    }
}
