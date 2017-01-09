package com.example.atm.utils;

import com.example.atm.bean.SiteItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SiteListSortUtil {

	public static List<SiteItem> sortList(List<SiteItem> sitelist) {

		List<SiteItem> sortedList = new ArrayList<SiteItem>();
		
		List<SiteItem> criticalList = new ArrayList<SiteItem>();
		List<SiteItem> majorList = new ArrayList<SiteItem>();
		List<SiteItem> minorList = new ArrayList<SiteItem>();
		List<SiteItem> noneList = new ArrayList<SiteItem>();
		String serverity = null;
		for (SiteItem siteItem : sitelist) {
			serverity = siteItem.getSeverity();
			if (serverity.equalsIgnoreCase("Critical")) {
				criticalList.add(siteItem);

			} else if (serverity.equalsIgnoreCase("Major")) {
				majorList.add(siteItem);
			} else if (serverity.equalsIgnoreCase("Minor")) {
				minorList.add(siteItem);
			} else {
				noneList.add(siteItem);
			}

		}

		sortListByAlarmNum(criticalList);
		sortListByAlarmNum(majorList);
		sortListByAlarmNum(minorList);
		sortListByAlarmNum(noneList);
		sortedList.addAll(criticalList);
		sortedList.addAll(majorList);
		sortedList.addAll(minorList);
		sortedList.addAll(noneList);

		return sortedList;

	}

	public static void sortListByAlarmNum(List<SiteItem> sitelist) {

		Collections.sort(sitelist);

	}
}
