package com.oak.constants.incidents;

public class IncidentStatisticsConstants {
	public static final String INCI_COUNT_FOR_EACH_STATE = "IC4ES";
	public static final String INCI_TYPE_COUNT_FOR_EACH_STATE = "ITC4ES";
	public static final String INCI_CAT_COUNT_FOR_EACH_STATE = "ICC4ES";
	public static final String INCI_COUNT_FOR_GOVT_EACH_STATE = "ICG4ES";
	public static final String INCI_TYPE_COUNT_FOR_GOVT_EACH_STATE = "ITCG4ES";
	public static final String INCI_CAT_COUNT_FOR_GOVT_EACH_STATE = "ICCG4ES";
	public static final String NO_VALUE = "NV";
	
	public static String getKeyByType(String type) {

		if (type.equals("CRIME")) {
			return "Crime Type";
		}

		if (type.equals("FAKE PRODUCTS")) {
			return "Fake Product Type";
		}

		if (type.equals("ROADS")) {
			return "Road Incident Type";
		}

		if (type.equals("HOSPITALS")) {
			return "Hospital Report Type";
		}

		if (type.equals("ACCIDENTS")) {
			return "Accident Type";
		}

		if (type.equals("SCHOOL")) {
			return "School Report Type";
		}

		if (type.equals("POWER")) {
			return "Power Report Type";
		}

		if (type.equals("POTABLE WATER")) {
			return "Water Outage Type";
		}

		if (type.equals("PETROL PRICE")) {
			return "Report Type";
		}

		if (type.equals("AIRPORTS")) {
			return "Airport Report Type";
		}

		if (type.equals("TRANSPORT")) {
			return "Transport Report Type";
		}

		return null;
	}
}
